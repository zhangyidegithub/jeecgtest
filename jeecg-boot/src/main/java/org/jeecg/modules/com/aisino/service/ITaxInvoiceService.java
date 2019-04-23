package org.jeecg.modules.com.aisino.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.com.aisino.entity.TaxInvoice;
import org.jeecg.modules.com.aisino.entity.TaxInvoiceDetail;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 客户税号
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
public interface ITaxInvoiceService extends IService<TaxInvoice> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TaxInvoice taxInvoice,List<TaxInvoiceDetail> taxInvoiceDetailList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TaxInvoice taxInvoice,List<TaxInvoiceDetail> taxInvoiceDetailList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
