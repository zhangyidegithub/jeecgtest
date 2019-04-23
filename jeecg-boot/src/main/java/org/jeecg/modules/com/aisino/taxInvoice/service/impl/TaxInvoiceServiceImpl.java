package org.jeecg.modules.com.aisino.taxInvoice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.com.aisino.taxInvoice.entity.TaxInvoice;
import org.jeecg.modules.com.aisino.taxInvoice.entity.TaxInvoiceDetail;
import org.jeecg.modules.com.aisino.taxInvoice.mapper.TaxInvoiceDetailMapper;
import org.jeecg.modules.com.aisino.taxInvoice.mapper.TaxInvoiceMapper;
import org.jeecg.modules.com.aisino.taxInvoice.service.ITaxInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 客户税号
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Service
public class TaxInvoiceServiceImpl extends ServiceImpl<TaxInvoiceMapper, TaxInvoice> implements ITaxInvoiceService {

	@Autowired
	private TaxInvoiceMapper taxInvoiceMapper;
	@Autowired
	private TaxInvoiceDetailMapper taxInvoiceDetailMapper;
	
	@Override
	@Transactional
	public void saveMain(TaxInvoice taxInvoice, List<TaxInvoiceDetail> taxInvoiceDetailList) {
		taxInvoiceMapper.insert(taxInvoice);
		for(TaxInvoiceDetail entity:taxInvoiceDetailList) {
			//外键设置
			entity.setDataid(taxInvoice.getId());
			taxInvoiceDetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(TaxInvoice taxInvoice,List<TaxInvoiceDetail> taxInvoiceDetailList) {
		taxInvoiceMapper.updateById(taxInvoice);
		
		//1.先删除子表数据
		taxInvoiceDetailMapper.deleteByMainId(taxInvoice.getId());
		
		//2.子表数据重新插入
		for(TaxInvoiceDetail entity:taxInvoiceDetailList) {
			//外键设置
			entity.setDataid(taxInvoice.getId());
			taxInvoiceDetailMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		taxInvoiceMapper.deleteById(id);
		taxInvoiceDetailMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			taxInvoiceMapper.deleteById(id);
			taxInvoiceDetailMapper.deleteByMainId(id.toString());
		}
	}
	
}
