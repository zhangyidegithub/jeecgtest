package com.aisino.customer.controller;

import com.aisino.customer.entity.TaxCustomerAuthorInfo;
import com.aisino.customer.service.ITaxCustomerAuthorInfoService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysUser;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @Title: Controller
* @Description: 授权信息
* @author： zhangchengping
* @date：   2019-04-18
* @version： V1.0
*/
@RestController
@RequestMapping("/customer/taxCustomerAuthorInfo")
@Slf4j
public class TaxCustomerAuthorInfoController {
   @Autowired
   private ITaxCustomerAuthorInfoService taxCustomerAuthorInfoService;

   /**
     * 分页列表查询
    * @param taxCustomerAuthorInfo
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @GetMapping(value = "/list")
   public Result<IPage<TaxCustomerAuthorInfo>> queryPageList(TaxCustomerAuthorInfo taxCustomerAuthorInfo,
                                                             @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                                             @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                                             HttpServletRequest req) {
       Result<IPage<TaxCustomerAuthorInfo>> result = new Result<IPage<TaxCustomerAuthorInfo>>();
       QueryWrapper<TaxCustomerAuthorInfo> queryWrapper = QueryGenerator.initQueryWrapper(taxCustomerAuthorInfo, req.getParameterMap());
       Page<TaxCustomerAuthorInfo> page = new Page<TaxCustomerAuthorInfo>(pageNo, pageSize);
       IPage<TaxCustomerAuthorInfo> pageList = taxCustomerAuthorInfoService.page(page, queryWrapper);
       result.setSuccess(true);
       result.setResult(pageList);
       return result;
   }

   /**
     *   添加
    * @param taxCustomerAuthorInfo
    * @return
    */
   @PostMapping(value = "/add")
   @RequiresPermissions(value={"customerAuthorInfo:add"})
   public Result<TaxCustomerAuthorInfo> add(@RequestBody TaxCustomerAuthorInfo taxCustomerAuthorInfo) {
       Result<TaxCustomerAuthorInfo> result = new Result<TaxCustomerAuthorInfo>();
       try {
           SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
           taxCustomerAuthorInfo.setCreatedDate(new Date());
           taxCustomerAuthorInfo.setCreatedUserBy(sysUser.getUsername());
           taxCustomerAuthorInfoService.save(taxCustomerAuthorInfo);
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
    * @param taxCustomerAuthorInfo
    * @return
    */
   @PutMapping(value = "/edit")
   @RequiresPermissions(value={"customerAuthorInfo:edit","customerAuthorInfo:detail"},logical= Logical.OR)
   public Result<TaxCustomerAuthorInfo> edit(@RequestBody TaxCustomerAuthorInfo taxCustomerAuthorInfo) {
       Result<TaxCustomerAuthorInfo> result = new Result<TaxCustomerAuthorInfo>();
       TaxCustomerAuthorInfo taxCustomerAuthorInfoEntity = taxCustomerAuthorInfoService.getById(taxCustomerAuthorInfo.getId());
       if(taxCustomerAuthorInfoEntity==null) {
           result.error500("未找到对应实体");
       }else {
           boolean ok = taxCustomerAuthorInfoService.updateById(taxCustomerAuthorInfo);
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
   @RequiresPermissions(value={"customerAuthor:delete"})
   public Result<TaxCustomerAuthorInfo> delete(@RequestParam(name="id",required=true) String id) {
       Result<TaxCustomerAuthorInfo> result = new Result<TaxCustomerAuthorInfo>();
       TaxCustomerAuthorInfo taxCustomerAuthorInfo = taxCustomerAuthorInfoService.getById(id);
       if(taxCustomerAuthorInfo==null) {
           result.error500("未找到对应实体");
       }else {
           boolean ok = taxCustomerAuthorInfoService.removeById(id);
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
   @RequiresPermissions(value={"customerAuthor:delete"})
   public Result<TaxCustomerAuthorInfo> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       Result<TaxCustomerAuthorInfo> result = new Result<TaxCustomerAuthorInfo>();
       if(ids==null || "".equals(ids.trim())) {
           result.error500("参数不识别！");
       }else {
           this.taxCustomerAuthorInfoService.removeByIds(Arrays.asList(ids.split(",")));
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
   public Result<TaxCustomerAuthorInfo> queryById(@RequestParam(name="id",required=true) String id) {
       Result<TaxCustomerAuthorInfo> result = new Result<TaxCustomerAuthorInfo>();
       TaxCustomerAuthorInfo taxCustomerAuthorInfo = taxCustomerAuthorInfoService.getById(id);
       if(taxCustomerAuthorInfo==null) {
           result.error500("未找到对应实体");
       }else {
           result.setResult(taxCustomerAuthorInfo);
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
     QueryWrapper<TaxCustomerAuthorInfo> queryWrapper = null;
     try {
         String paramsStr = request.getParameter("paramsStr");
         if (oConvertUtils.isNotEmpty(paramsStr)) {
             String deString = URLDecoder.decode(paramsStr, "UTF-8");
             TaxCustomerAuthorInfo taxCustomerAuthorInfo = JSON.parseObject(deString, TaxCustomerAuthorInfo.class);
             queryWrapper = QueryGenerator.initQueryWrapper(taxCustomerAuthorInfo, request.getParameterMap());
         }
     } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
     }

     //Step.2 AutoPoi 导出Excel
     ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
     List<TaxCustomerAuthorInfo> pageList = taxCustomerAuthorInfoService.list(queryWrapper);
     //导出文件名称
     mv.addObject(NormalExcelConstants.FILE_NAME, "授权信息列表");
     mv.addObject(NormalExcelConstants.CLASS, TaxCustomerAuthorInfo.class);
     mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("授权信息列表数据", "导出人:Jeecg", "导出信息"));
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
             List<TaxCustomerAuthorInfo> listTaxCustomerAuthorInfos = ExcelImportUtil.importExcel(file.getInputStream(), TaxCustomerAuthorInfo.class, params);
             for (TaxCustomerAuthorInfo taxCustomerAuthorInfoExcel : listTaxCustomerAuthorInfos) {
                 taxCustomerAuthorInfoService.save(taxCustomerAuthorInfoExcel);
             }
             return Result.ok("文件导入成功！数据行数：" + listTaxCustomerAuthorInfos.size());
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
