package com.aisino.taxInvoice.service.impl;


import com.aisino.taxInvoice.entity.Report;
import com.aisino.taxInvoice.entity.TaxInvoice;
import com.aisino.taxInvoice.entity.TaxInvoiceGoods;
import com.aisino.taxInvoice.mapper.TaxInvoiceGoodsMapper;
import com.aisino.taxInvoice.mapper.TaxInvoiceMapper;
import com.aisino.taxInvoice.service.ITaxInvoiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 红字发票申请单
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
@Service
public class TaxInvoiceServiceImpl extends ServiceImpl<TaxInvoiceMapper, TaxInvoice> implements ITaxInvoiceService {

	@Autowired
	private TaxInvoiceMapper taxInvoiceMapper;
	@Autowired
	private TaxInvoiceGoodsMapper taxInvoiceGoodsMapper;
	
	@Override
	@Transactional
	public void saveMain(TaxInvoice taxInvoice, List<TaxInvoiceGoods> taxInvoiceGoodsList) {
		taxInvoiceMapper.insert(taxInvoice);
		for(TaxInvoiceGoods entity:taxInvoiceGoodsList) {
			//外键设置
			entity.setInvId(taxInvoice.getId());
			taxInvoiceGoodsMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(TaxInvoice taxInvoice,List<TaxInvoiceGoods> taxInvoiceGoodsList) {
		taxInvoiceMapper.updateById(taxInvoice);
		
		//1.先删除子表数据
		taxInvoiceGoodsMapper.deleteByMainId(taxInvoice.getId());
		
		//2.子表数据重新插入
		for(TaxInvoiceGoods entity:taxInvoiceGoodsList) {
			//外键设置
			entity.setInvId(taxInvoice.getId());
			taxInvoiceGoodsMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		taxInvoiceMapper.deleteById(id);
		taxInvoiceGoodsMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			taxInvoiceMapper.deleteById(id);
			taxInvoiceGoodsMapper.deleteByMainId(id.toString());
		}
	}

	@Override
	public List<Report> findReport(Report report){
		try{
			return this.taxInvoiceMapper.findByReport(report);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Report findNumber(Report report) {
		try {
			return this.taxInvoiceMapper.findByNumber(report);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Report findAount(Report report) {
		try {
			return this.taxInvoiceMapper.findByAmount(report);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}


}
