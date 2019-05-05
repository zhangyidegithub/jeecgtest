package com.aisino.taxInvoice.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 商品明细
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
@Data
@TableName("TAX_INVOICE_GOODS")
public class TaxInvoiceGoods implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**主表ID*/
    @Excel(name = "主表ID", width = 15)
	private java.lang.String invId;
	/**商品名称*/
    @Excel(name = "商品名称", width = 15)
	private java.lang.String goodsName;
	/**单位*/
    @Excel(name = "单位", width = 15)
	private java.lang.String goodsUnit;
	/**单价*/
    @Excel(name = "单价", width = 15)
	private java.lang.String goodsPrice;
	/**税率*/
    @Excel(name = "税率", width = 15)
	private java.lang.String goodsTaxRate;
	/**型号*/
    @Excel(name = "型号", width = 15)
	private java.lang.String goodsXh;
	/**数量*/
    @Excel(name = "数量", width = 15)
	private java.lang.String goodsNumber;
	/**金额*/
    @Excel(name = "金额", width = 15)
	private java.lang.String goodsJe;
	/**税额*/
    @Excel(name = "税额", width = 15)
	private java.lang.String goodsSe;
	/**hsBz*/
    @Excel(name = "hsBz", width = 15)
	private java.lang.String hsBz;
	/**商品编码*/
    @Excel(name = "商品编码", width = 15)
	private java.lang.String spbm;
	/**是否使用优惠政策 1 使用 0 不使用*/
    @Excel(name = "是否使用优惠政策 1 使用 0 不使用", width = 15)
	private java.lang.Integer syyhzcbz;
	/**优惠政策名称*/
    @Excel(name = "优惠政策名称", width = 15)
	private java.lang.String yhzc;
	/**零税率标识 空：非零税率 0:出口退税 1：免税 2：不征收 3：普通零税率*/
    @Excel(name = "零税率标识 空：非零税率 0:出口退税 1：免税 2：不征收 3：普通零税率", width = 15)
	private java.lang.Integer lslbz;
	/**备用字段1*/
    @Excel(name = "备用字段1", width = 15)
	private java.lang.String p1;
	/**备用字段2*/
    @Excel(name = "备用字段2", width = 15)
	private java.lang.String p2;
	/**备用字段3*/
    @Excel(name = "备用字段3", width = 15)
	private java.lang.String p3;
	/**备用字段4*/
    @Excel(name = "备用字段4", width = 15)
	private java.lang.String p4;
	/**备用字段5*/
    @Excel(name = "备用字段5", width = 15)
	private java.lang.String p5;

}
