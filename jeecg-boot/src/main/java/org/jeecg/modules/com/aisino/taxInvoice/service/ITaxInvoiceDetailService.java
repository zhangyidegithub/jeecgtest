package org.jeecg.modules.com.aisino.taxInvoice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.com.aisino.taxInvoice.entity.TaxInvoiceDetail;

import java.util.List;

/**
 * @Description: 客户明细
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
public interface ITaxInvoiceDetailService extends IService<TaxInvoiceDetail> {

	public List<TaxInvoiceDetail> selectByMainId(String mainId);
}
