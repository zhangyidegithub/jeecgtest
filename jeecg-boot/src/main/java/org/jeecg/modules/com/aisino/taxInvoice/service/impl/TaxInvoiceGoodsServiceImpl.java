package org.jeecg.modules.com.aisino.taxInvoice.service.impl;

import org.jeecg.modules.com.aisino.taxInvoice.entity.TaxInvoiceGoods;
import org.jeecg.modules.com.aisino.taxInvoice.mapper.TaxInvoiceGoodsMapper;
import org.jeecg.modules.com.aisino.taxInvoice.service.ITaxInvoiceGoodsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 商品明细
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
@Service
public class TaxInvoiceGoodsServiceImpl extends ServiceImpl<TaxInvoiceGoodsMapper, TaxInvoiceGoods> implements ITaxInvoiceGoodsService {
	
	@Autowired
	private TaxInvoiceGoodsMapper taxInvoiceGoodsMapper;
	
	@Override
	public List<TaxInvoiceGoods> selectByMainId(String mainId) {
		return taxInvoiceGoodsMapper.selectByMainId(mainId);
	}
}
