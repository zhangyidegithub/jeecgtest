package org.jeecg.modules.com.aisino.taxInvoice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: 客户明细
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Data
@TableName("TAX_INVOICE_DETAIL")
public class TaxInvoiceDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**项目名称*/
    @Excel(name = "项目名称", width = 15)
	private java.lang.String spmc;
	/**项目单位*/
    @Excel(name = "项目单位", width = 15)
	private java.lang.String jldw;
	/**规格型号*/
    @Excel(name = "规格型号", width = 15)
	private java.lang.String ggxh;
	/**税率*/
    @Excel(name = "税率", width = 15)
	private java.lang.String slv;
	/**含税标志*/
    @Excel(name = "含税标志", width = 15)
	private java.lang.String hsjbz;
	/**发票行性质0正常行、1折扣行、2被折扣行*/
    @Excel(name = "发票行性质0正常行、1折扣行、2被折扣行", width = 15)
	private java.lang.String fphxz;
	/**项目单价*/
    @Excel(name = "项目单价", width = 15)
	private java.math.BigDecimal dj;
	/**商品编码*/
    @Excel(name = "商品编码", width = 15)
	private java.lang.String spbm;
	/**自行编码*/
    @Excel(name = "自行编码", width = 15)
	private java.lang.String zxbm;
	/**优惠政策标识*/
    @Excel(name = "优惠政策标识", width = 15)
	private java.lang.String yhzcbs;
	/**零税率标识*/
    @Excel(name = "零税率标识", width = 15)
	private java.lang.String lslbs;
	/**增值税特殊管理*/
    @Excel(name = "增值税特殊管理", width = 15)
	private java.lang.String zzstsgl;
	/**扣除额*/
    @Excel(name = "扣除额", width = 15)
	private java.math.BigDecimal kce;
	/**项目金额*/
    @Excel(name = "项目金额", width = 15)
	private java.math.BigDecimal je;
	/**项目数量*/
    @Excel(name = "项目数量", width = 15)
	private java.math.BigDecimal sl;
	/**税额*/
    @Excel(name = "税额", width = 15)
	private java.math.BigDecimal se;
	/**备用字段1*/
	private java.lang.String p1;
	/**备用字段2*/
	private java.lang.String p2;
	/**备用字段3*/
	private java.lang.String p3;
	/**备用字段4*/
	private java.lang.String p4;
	/**备用字段5*/
	private java.lang.String p5;
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**主表ID*/
	private java.lang.String dataid;
}
