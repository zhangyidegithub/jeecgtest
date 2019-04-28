package org.jeecg.modules.com.aisino.taxInvoice.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.ApiOperation;
import org.jeecg.modules.com.aisino.taxInvoice.entity.Report;
import org.jeecg.modules.com.aisino.taxInvoice.entity.TaxInvoice;
import org.jeecg.modules.com.aisino.taxInvoice.entity.TaxInvoiceGoods;
import org.jeecg.modules.com.aisino.taxInvoice.service.ITaxInvoiceGoodsService;
import org.jeecg.modules.com.aisino.taxInvoice.service.ITaxInvoiceService;
import org.jeecg.modules.com.aisino.taxInvoice.vo.TaxInvoicePage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
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
 * @Description: 红字发票申请单
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
@RestController
@RequestMapping("/com/aisino/taxInvoice")
@Slf4j
public class TaxInvoiceController {
	@Autowired
	private ITaxInvoiceService taxInvoiceService;
	@Autowired
	private ITaxInvoiceGoodsService taxInvoiceGoodsService;
	
	/**
	  * 分页列表查询
	 * @param taxInvoice
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<IPage<TaxInvoice>> queryPageList(TaxInvoice taxInvoice,
												   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
												   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
												   HttpServletRequest req) {
		Result<IPage<TaxInvoice>> result = new Result<IPage<TaxInvoice>>();
		QueryWrapper<TaxInvoice> queryWrapper = QueryGenerator.initQueryWrapper(taxInvoice, req.getParameterMap());
		Page<TaxInvoice> page = new Page<TaxInvoice>(pageNo, pageSize);
		IPage<TaxInvoice> pageList = taxInvoiceService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	
	/**
	  *   添加
	 * @param taxInvoicePage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<TaxInvoice> add(@RequestBody TaxInvoicePage taxInvoicePage) {
		Result<TaxInvoice> result = new Result<TaxInvoice>();
		try {
			TaxInvoice taxInvoice = new TaxInvoice();
			BeanUtils.copyProperties(taxInvoicePage, taxInvoice);
			
			taxInvoiceService.saveMain(taxInvoice, taxInvoicePage.getTaxInvoiceGoodsList());
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
	 * @param taxInvoicePage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<TaxInvoice> edit(@RequestBody TaxInvoicePage taxInvoicePage) {
		Result<TaxInvoice> result = new Result<TaxInvoice>();
		TaxInvoice taxInvoice = new TaxInvoice();
		BeanUtils.copyProperties(taxInvoicePage, taxInvoice);
		TaxInvoice taxInvoiceEntity = taxInvoiceService.getById(taxInvoice.getId());
		if(taxInvoiceEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = taxInvoiceService.updateById(taxInvoice);
			taxInvoiceService.updateMain(taxInvoice, taxInvoicePage.getTaxInvoiceGoodsList());
			result.success("修改成功!");
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<TaxInvoice> delete(@RequestParam(name="id",required=true) String id) {
		Result<TaxInvoice> result = new Result<TaxInvoice>();
		TaxInvoice taxInvoice = taxInvoiceService.getById(id);
		if(taxInvoice==null) {
			result.error500("未找到对应实体");
		}else {
			taxInvoiceService.delMain(id);
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
	public Result<TaxInvoice> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<TaxInvoice> result = new Result<TaxInvoice>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.taxInvoiceService.delBatchMain(Arrays.asList(ids.split(",")));
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
	public Result<TaxInvoice> queryById(@RequestParam(name="id",required=true) String id) {
		Result<TaxInvoice> result = new Result<TaxInvoice>();
		TaxInvoice taxInvoice = taxInvoiceService.getById(id);
		if(taxInvoice==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(taxInvoice);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过用户名和年份查询统计报表
	 * @param Report 请求参数
	 * @return
	 */
	@ApiOperation(value = "获取report数据列表", notes = "获取所有report数据列表", produces = "application/json")
	@PostMapping(value = "/report")
	public Result<List<Report>> queryTaxInvoicereport(@RequestBody Report report) {
		Result<List<Report>> result = new Result<List<Report>>();
		List<Report> reportList = taxInvoiceService.findReport(report);
		result.setResult(reportList);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryTaxInvoiceGoodsByMainId")
	public Result<List<TaxInvoiceGoods>> queryTaxInvoiceGoodsListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<TaxInvoiceGoods>> result = new Result<List<TaxInvoiceGoods>>();
		List<TaxInvoiceGoods> taxInvoiceGoodsList = taxInvoiceGoodsService.selectByMainId(id);
		result.setResult(taxInvoiceGoodsList);
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
      QueryWrapper<TaxInvoice> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              TaxInvoice taxInvoice = JSON.parseObject(deString, TaxInvoice.class);
              queryWrapper = QueryGenerator.initQueryWrapper(taxInvoice, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<TaxInvoicePage> pageList = new ArrayList<TaxInvoicePage>();
      List<TaxInvoice> taxInvoiceList = taxInvoiceService.list(queryWrapper);
      for (TaxInvoice taxInvoice : taxInvoiceList) {
          TaxInvoicePage vo = new TaxInvoicePage();
          BeanUtils.copyProperties(taxInvoice, vo);
          List<TaxInvoiceGoods> taxInvoiceGoodsList = taxInvoiceGoodsService.selectByMainId(taxInvoice.getId());
          vo.setTaxInvoiceGoodsList(taxInvoiceGoodsList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "红字发票申请单列表");
      mv.addObject(NormalExcelConstants.CLASS, TaxInvoicePage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("红字发票申请单列表数据", "导出人:Jeecg", "导出信息"));
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
              List<TaxInvoicePage> list = ExcelImportUtil.importExcel(file.getInputStream(), TaxInvoicePage.class, params);
              for (TaxInvoicePage page : list) {
                  TaxInvoice po = new TaxInvoice();
                  BeanUtils.copyProperties(page, po);
                  taxInvoiceService.saveMain(po, page.getTaxInvoiceGoodsList());
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
