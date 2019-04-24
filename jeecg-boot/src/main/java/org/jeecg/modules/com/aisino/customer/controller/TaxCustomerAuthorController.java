package org.jeecg.modules.com.aisino.customer.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.com.aisino.customer.entity.TaxCustomerAuthor;
import org.jeecg.modules.com.aisino.customer.service.ITaxCustomerAuthorService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Title: Controller
 * @Description: 企业信息
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@RestController
@RequestMapping("/customer/taxCustomerAuthor")
@Slf4j
public class TaxCustomerAuthorController {
	 @Autowired
	 private ITaxCustomerAuthorService taxCustomerAuthorService;
	
	/**
	  * 分页列表查询
	 * @param taxCustomerAuthor
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<TaxCustomerAuthor>> queryPageList(TaxCustomerAuthor taxCustomerAuthor,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		Result<IPage<TaxCustomerAuthor>> result = new Result<IPage<TaxCustomerAuthor>>();
		QueryWrapper<TaxCustomerAuthor> queryWrapper = QueryGenerator.initQueryWrapper(taxCustomerAuthor, req.getParameterMap());
		Page<TaxCustomerAuthor> page = new Page<TaxCustomerAuthor>(pageNo, pageSize);
		IPage<TaxCustomerAuthor> pageList = taxCustomerAuthorService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param taxCustomerAuthor
	 * @return
	 */
	@PostMapping(value = "/add")
	@RequiresPermissions(value={"customerAuthor:add"})
	public Result<TaxCustomerAuthor> add(@RequestBody TaxCustomerAuthor taxCustomerAuthor) {
		Result<TaxCustomerAuthor> result = new Result<TaxCustomerAuthor>();
		try {
			taxCustomerAuthorService.save(taxCustomerAuthor);
			result.success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage());
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param taxCustomerAuthor
	 * @return
	 */
	@PutMapping(value = "/edit")
	@RequiresPermissions(value={"customerAuthor:edit","customerAuthor:detail"})
	public Result<TaxCustomerAuthor> edit(@RequestBody TaxCustomerAuthor taxCustomerAuthor) {
		Result<TaxCustomerAuthor> result = new Result<TaxCustomerAuthor>();
		TaxCustomerAuthor taxCustomerAuthorEntity = taxCustomerAuthorService.getById(taxCustomerAuthor.getId());
		if(taxCustomerAuthorEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = taxCustomerAuthorService.updateById(taxCustomerAuthor);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	@RequiresPermissions(value={"customerAuthorInfo:delete"})
	public Result<TaxCustomerAuthor> delete(@RequestParam(name="id",required=true) String id) {
		Result<TaxCustomerAuthor> result = new Result<TaxCustomerAuthor>();
		TaxCustomerAuthor taxCustomerAuthor = taxCustomerAuthorService.getById(id);
		if(taxCustomerAuthor==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = taxCustomerAuthorService.removeById(id);
			if(ok) {
				result.success("删除成功!");
			}
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	@RequiresPermissions(value={"customerAuthorInfo:delete"})
	public Result<TaxCustomerAuthor> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TaxCustomerAuthor> result = new Result<TaxCustomerAuthor>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.taxCustomerAuthorService.removeByIds(Arrays.asList(ids.split(",")));
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
	public Result<TaxCustomerAuthor> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TaxCustomerAuthor> result = new Result<TaxCustomerAuthor>();
		TaxCustomerAuthor taxCustomerAuthor = taxCustomerAuthorService.getById(id);
		if(taxCustomerAuthor==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(taxCustomerAuthor);
			result.setSuccess(true);
		}
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
      QueryWrapper<TaxCustomerAuthor> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TaxCustomerAuthor taxCustomerAuthor = JSON.parseObject(deString, TaxCustomerAuthor.class);
              queryWrapper = QueryGenerator.initQueryWrapper(taxCustomerAuthor, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TaxCustomerAuthor> pageList = taxCustomerAuthorService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "企业信息列表");
      mv.addObject(NormalExcelConstants.CLASS, TaxCustomerAuthor.class);
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
              List<TaxCustomerAuthor> listTaxCustomerAuthors = ExcelImportUtil.importExcel(file.getInputStream(), TaxCustomerAuthor.class, params);
              for (TaxCustomerAuthor taxCustomerAuthorExcel : listTaxCustomerAuthors) {
                  taxCustomerAuthorService.save(taxCustomerAuthorExcel);
              }
              return Result.ok("文件导入成功！数据行数：" + listTaxCustomerAuthors.size());
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
