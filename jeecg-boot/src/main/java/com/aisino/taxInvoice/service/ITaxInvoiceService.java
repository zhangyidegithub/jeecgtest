package com.aisino.taxInvoice.service;

import com.aisino.taxInvoice.entity.Report;
import com.aisino.taxInvoice.entity.TaxInvoice;
import com.aisino.taxInvoice.entity.TaxInvoiceGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 红字发票申请单
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
public interface ITaxInvoiceService extends IService<TaxInvoice> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TaxInvoice taxInvoice,List<TaxInvoiceGoods> taxInvoiceGoodsList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TaxInvoice taxInvoice,List<TaxInvoiceGoods> taxInvoiceGoodsList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);

	/**
	 * 统计报表查询
	 * @param report 转换类
	 * @return	List<Report>
	 */
	public List<Report> findReport(Report report);

	/**
	 * 报表开票量统计
	 * @param report	转换类
	 * @return	Report
	 */
	public Report findNumber(Report report);

	/**
	 *	报表金额统计
	 * @param report	转换类
	 * @return	Report
	 */
	public Report findAount(Report report);
}
