package org.jeecg.modules.com.aisino.taxInvoice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.com.aisino.taxInvoice.entity.TaxInvoiceDetail;

import java.util.List;

/**
 * @Description: 客户明细
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
public interface TaxInvoiceDetailMapper extends BaseMapper<TaxInvoiceDetail> {

	public boolean deleteByMainId(String mainId);
    
	public List<TaxInvoiceDetail> selectByMainId(String mainId);
}
