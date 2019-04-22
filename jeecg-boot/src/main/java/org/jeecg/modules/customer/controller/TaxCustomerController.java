package org.jeecg.modules.customer.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.modules.demo.test.entity.JeecgOrderMain;
import org.jeecg.modules.demo.test.vo.JeecgOrderMainPage;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.customer.entity.TaxCustomerAuthor;
import org.jeecg.modules.customer.entity.TaxCustomerAuthorInfo;
import org.jeecg.modules.customer.entity.TaxCustomer;
import org.jeecg.modules.customer.vo.TaxCustomerPage;
import org.jeecg.modules.customer.service.ITaxCustomerService;
import org.jeecg.modules.customer.service.ITaxCustomerAuthorService;
import org.jeecg.modules.customer.service.ITaxCustomerAuthorInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Title: Controller
 * @Description: 企业信息
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@RestController
@RequestMapping("/customer/taxCustomer")
@Slf4j
public class TaxCustomerController {
	@Autowired
	private ITaxCustomerService taxCustomerService;
	@Autowired
	private ITaxCustomerAuthorService taxCustomerAuthorService;
	@Autowired
	private ITaxCustomerAuthorInfoService taxCustomerAuthorInfoService;
	 @Autowired
	 private ISysUserService sysUserService;
	/**
	  * 分页列表查询
	 * @param taxCustomer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<TaxCustomer>> queryPageList(TaxCustomer taxCustomer,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TaxCustomer>> result = new Result<IPage<TaxCustomer>>();
		QueryWrapper<TaxCustomer> queryWrapper = QueryGenerator.initQueryWrapper(taxCustomer, req.getParameterMap());
		Page<TaxCustomer> page = new Page<TaxCustomer>(pageNo, pageSize);
		IPage<TaxCustomer> pageList = taxCustomerService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param taxCustomerPage
	 * @return
	 */
	/*
	@PostMapping(value = "/add")
	public Result<TaxCustomer> add(@RequestBody TaxCustomerPage taxCustomerPage) {
		Result<TaxCustomer> result = new Result<TaxCustomer>();
		try {
			TaxCustomer taxCustomer = new TaxCustomer();
			BeanUtils.copyProperties(taxCustomerPage, taxCustomer);
			
			taxCustomerService.saveMain(taxCustomer, taxCustomerPage.getTaxCustomerAuthorList(),taxCustomerPage.getTaxCustomerAuthorInfoList());
			result.success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			result.error500("操作失败");
		}
		return result;
	}*/
	 @PostMapping(value = "/add")
	 public Result<TaxCustomer> add(@RequestBody TaxCustomerPage TaxCustomerPage) {
		 Result<TaxCustomer> result = new Result<TaxCustomer>();
		 try {
			 TaxCustomer taxcustomer = new TaxCustomer();
			 BeanUtils.copyProperties(TaxCustomerPage, taxcustomer);
			 taxCustomerService.save(taxcustomer);
			 saveSysUser(taxcustomer);

			 result.success("添加成功！");
		 } catch (Exception e) {
			 e.printStackTrace();
			 log.info(e.getMessage());
			 result.error500("操作失败");
		 }
		 return result;
	 }

	 /**
	  * 保存信息到用户表
	  * @param taxcustomer
	  */
	 private void saveSysUser(TaxCustomer taxcustomer) {
		 SysUser user = new SysUser();
		 user.setStatus(1);//1 正常 0 冻结
		 user.setDelFlag("0");//是否删除
		 user.setUsername(taxcustomer.getCustTaxCode());
		 user.setRealname(taxcustomer.getCustTaxName());
		 user.setCreateTime(new Date());//设置创建时间
		 user.setPassword(taxcustomer.getPassword());

		 String salt = oConvertUtils.randomGen(8);
		 user.setSalt(salt);
		 String passwordEncode = PasswordUtil.encrypt(user.getUsername(), user.getPassword(), salt);
		 user.setPassword(passwordEncode);
		 sysUserService.addUserWithRole(user, "f6817f48af4fb3af11b9e8bf182f618b");
	 }


	 /**
	  *  编辑
	 * @param taxCustomerPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<TaxCustomer> edit(@RequestBody TaxCustomerPage taxCustomerPage) {
		Result<TaxCustomer> result = new Result<TaxCustomer>();
		TaxCustomer taxCustomer = new TaxCustomer();
		BeanUtils.copyProperties(taxCustomerPage, taxCustomer);
		TaxCustomer taxCustomerEntity = taxCustomerService.getById(taxCustomer.getCustomerId());
		if(taxCustomerEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = taxCustomerService.updateById(taxCustomer);
			//taxCustomerService.updateMain(taxCustomer, taxCustomerPage.getTaxCustomerAuthorList(),taxCustomerPage.getTaxCustomerAuthorInfoList());
			result.success("修改成功!");
		}
		changeSysUser(taxCustomer);

		return result;
	}

	 /**
	  * 修改用户
	  * @param taxCustomer
	  */
	 private void changeSysUser(TaxCustomer taxCustomer) {
		 String password = taxCustomer.getPassword();
		 SysUser sysUser = this.sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, taxCustomer.getCustTaxCode()));
		 if (sysUser == null) {
			 log.error("未找到对应实体");
			 saveSysUser(taxCustomer);
		 } else {
			 sysUser.setUsername(taxCustomer.getCustTaxCode());
			 sysUser.setRealname(taxCustomer.getCustTaxName());
			 sysUser.setPassword(taxCustomer.getPassword());
			 String salt = oConvertUtils.randomGen(8);
			 sysUser.setSalt(salt);
			 String passwordEncode = PasswordUtil.encrypt(sysUser.getUsername(), password, salt);
			 sysUser.setPassword(passwordEncode);
			 this.sysUserService.updateById(sysUser);
			 log.info("用户修改完成！");
		 }
	 }

	 /**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<TaxCustomer> delete(@RequestParam(name="id",required=true) String id) {
		Result<TaxCustomer> result = new Result<TaxCustomer>();
		TaxCustomer taxCustomer = taxCustomerService.getById(id);
		if(taxCustomer==null) {
			result.error500("未找到对应实体");
		}else {
			taxCustomerService.delMain(id);
			result.success("删除成功!");
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<TaxCustomer> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TaxCustomer> result = new Result<TaxCustomer>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.taxCustomerService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<TaxCustomer> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TaxCustomer> result = new Result<TaxCustomer>();
		TaxCustomer taxCustomer = taxCustomerService.getById(id);
		if(taxCustomer==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(taxCustomer);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryTaxCustomerAuthorByMainId")
	public Result<List<TaxCustomerAuthor>> queryTaxCustomerAuthorListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
		Result<List<TaxCustomerAuthor>> result = new Result<List<TaxCustomerAuthor>>();
		List<TaxCustomerAuthor> taxCustomerAuthorList = new ArrayList<>();
		if(StringUtils.isNotBlank(mainId)){
			taxCustomerAuthorList = taxCustomerAuthorService.selectByMainId(mainId);
		}
		result.setResult(taxCustomerAuthorList);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryTaxCustomerAuthorInfoByMainId")
	public Result<List<TaxCustomerAuthorInfo>> queryTaxCustomerAuthorInfoListByMainId(@RequestParam(name = "mainId", required = false) String mainId) {
		Result<List<TaxCustomerAuthorInfo>> result = new Result<List<TaxCustomerAuthorInfo>>();
		List<TaxCustomerAuthorInfo> taxCustomerAuthorInfoList = new ArrayList<>();
		if(StringUtils.isNotBlank(mainId)){
			taxCustomerAuthorInfoList =  taxCustomerAuthorInfoService.selectByMainId(mainId);
		}
		result.setResult(taxCustomerAuthorInfoList);
		result.setSuccess(true);
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<TaxCustomer> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TaxCustomer taxCustomer = JSON.parseObject(deString, TaxCustomer.class);
              queryWrapper = QueryGenerator.initQueryWrapper(taxCustomer, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TaxCustomerPage> pageList = new ArrayList<TaxCustomerPage>();
      List<TaxCustomer> taxCustomerList = taxCustomerService.list(queryWrapper);
      for (TaxCustomer taxCustomer : taxCustomerList) {
          TaxCustomerPage vo = new TaxCustomerPage();
          BeanUtils.copyProperties(taxCustomer, vo);
          List<TaxCustomerAuthor> taxCustomerAuthorList = taxCustomerAuthorService.selectByMainId(taxCustomer.getCustomerId());
          vo.setTaxCustomerAuthorList(taxCustomerAuthorList);
          List<TaxCustomerAuthorInfo> taxCustomerAuthorInfoList = taxCustomerAuthorInfoService.selectByMainId(taxCustomer.getCustomerId());
          vo.setTaxCustomerAuthorInfoList(taxCustomerAuthorInfoList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "企业信息列表");
      mv.addObject(NormalExcelConstants.CLASS, TaxCustomerPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("企业信息列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<TaxCustomerPage> list = ExcelImportUtil.importExcel(file.getInputStream(), TaxCustomerPage.class, params);
              for (TaxCustomerPage page : list) {
                  TaxCustomer po = new TaxCustomer();
                  BeanUtils.copyProperties(page, po);
                  taxCustomerService.saveMain(po, page.getTaxCustomerAuthorList(),page.getTaxCustomerAuthorInfoList());
              }
              return Result.ok("文件导入成功！数据行数：" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage());
              return Result.error("文件导入失败！");
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }

}