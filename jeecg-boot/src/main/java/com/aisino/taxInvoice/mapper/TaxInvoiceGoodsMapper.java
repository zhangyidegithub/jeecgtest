package com.aisino.taxInvoice.mapper;

import java.util.List;

import com.aisino.taxInvoice.entity.TaxInvoiceGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 商品明细
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
@Mapper
public interface TaxInvoiceGoodsMapper extends BaseMapper<TaxInvoiceGoods> {

	public boolean deleteByMainId(String mainId);
    
	public List<TaxInvoiceGoods> selectByMainId(String mainId);
}
