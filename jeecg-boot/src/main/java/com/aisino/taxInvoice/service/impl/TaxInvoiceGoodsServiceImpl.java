package com.aisino.taxInvoice.service.impl;

import com.aisino.taxInvoice.entity.*;
import com.aisino.taxInvoice.mapper.*;
import com.aisino.taxInvoice.service.ITaxInvoiceGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @Description: 商品明细
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
@Service
public class TaxInvoiceGoodsServiceImpl extends ServiceImpl<TaxInvoiceGoodsMapper, TaxInvoiceGoods> implements ITaxInvoiceGoodsService {
	
	@Resource
	private TaxInvoiceGoodsMapper taxInvoiceGoodsMapper;
	
	@Override
	public List<TaxInvoiceGoods> selectByMainId(String mainId) {
		return taxInvoiceGoodsMapper.selectByMainId(mainId);
	}
}
