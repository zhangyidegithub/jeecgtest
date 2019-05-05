package com.aisino.taxInvoice.service;

import com.aisino.taxInvoice.entity.TaxInvoiceGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 商品明细
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
public interface ITaxInvoiceGoodsService extends IService<TaxInvoiceGoods> {

	public List<TaxInvoiceGoods> selectByMainId(String mainId);
}
