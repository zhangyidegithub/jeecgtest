package org.jeecg.modules.com.aisino.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.com.aisino.entity.TaxInvoiceDetail;
import org.jeecg.modules.com.aisino.mapper.TaxInvoiceDetailMapper;
import org.jeecg.modules.com.aisino.service.ITaxInvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 客户明细
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Service
public class TaxInvoiceDetailServiceImpl extends ServiceImpl<TaxInvoiceDetailMapper, TaxInvoiceDetail> implements ITaxInvoiceDetailService {
	
	@Autowired
	private TaxInvoiceDetailMapper taxInvoiceDetailMapper;
	
	@Override
	public List<TaxInvoiceDetail> selectByMainId(String mainId) {
		return taxInvoiceDetailMapper.selectByMainId(mainId);
	}
}
