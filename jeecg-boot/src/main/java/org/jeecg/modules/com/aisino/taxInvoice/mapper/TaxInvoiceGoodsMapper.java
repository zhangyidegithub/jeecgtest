package org.jeecg.modules.com.aisino.taxInvoice.mapper;

import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.com.aisino.taxInvoice.entity.TaxInvoiceGoods;

/**
 * @Description: 商品明细
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
public interface TaxInvoiceGoodsMapper extends BaseMapper<TaxInvoiceGoods> {

	public boolean deleteByMainId(String mainId);
    
	public List<TaxInvoiceGoods> selectByMainId(String mainId);
}
