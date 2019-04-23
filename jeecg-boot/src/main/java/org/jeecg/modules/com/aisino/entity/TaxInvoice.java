package org.jeecg.modules.com.aisino.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 客户税号
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Data
@TableName("TAX_INVOICE")
public class TaxInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**发票请求唯一流水号*/
	private java.lang.String fpqqlsh;
	/**订单编号*/
	private java.lang.String ddbh;
	/**备注发票红票流水号*/
	private java.lang.String fpqqlshRed;
	/**销方识别号*/
	private java.lang.String sellerTaxCode;
	/**销方名称*/
	private java.lang.String sellerTaxName;
	/**销方地址电话*/
	private java.lang.String sellerAddressPhone;
	/**销方银行账号*/
	private java.lang.String sellerBankAccount;
	/**购方名称*/
	private java.lang.String clientTaxName;
	/**购方识别号*/
	private java.lang.String clientTaxCode;
	/**购方地址电话*/
	private java.lang.String clientAddressPhone;
	/**购方银行账号*/
	private java.lang.String clientBankAccount;
	/**开票员*/
	private java.lang.String invoicer;
	/**复核人*/
	private java.lang.String checker;
	/**收款员*/
	private java.lang.String cashier;
	/**开票类型（0 专用发票；2 普通发票；11 货物运输业增值税专用
发票；12 机动车销售统一发票）*/
	private java.lang.String infoKind;
	/**发票代码*/
	private java.lang.String infoTypeCode;
	/**发票号码*/
	private java.lang.String infoNumber;
	/**清单标志*/
	private java.lang.String listFlag;
	/**清单发票项目名称*/
	private java.lang.String qdxmmc;
	/**冲红原因*/
	private java.lang.String chyy;
	/**价税合计金额*/
	private java.math.BigDecimal amount;
	/**合计不含税金额*/
	private java.math.BigDecimal infoAmount;
	/**合计税额。所有商品行税额之和*/
	private java.math.BigDecimal tax;
	/**信息表类型：0正常，1逾期（仅销方开具）*/
	private java.lang.String billType;
	/**多税率标志：0一票一税率，1一票多税率*/
	private java.lang.String ismutiRate;
	/**税率，多税率时为空*/
	private java.lang.String taxRate;
	/**十位数字表示的申请说明，含正常和逾期*/
	private java.lang.String reqMemo;
	/**商品编码版本号*/
	private java.lang.String spbmbbh;
	/**含税税率标志：0不含税税率，1含税税率*/
	private java.lang.String slbz;
	/**备注*/
	private java.lang.String bz;
	/**收购标志（Y收购票，C成品油）*/
	private java.lang.String sgbz;
	/**代开标志*/
	private java.lang.String dkbz;
	/**折扣标志*/
	private java.lang.String zkbz;
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**是否申请开票，0 开票中，2申请开票，3申请下载，4申请红冲，5冲红成功，6申请作废，7作废成功*/
	private java.lang.String isApply;
	/**是否红字单 1 是 0 否*/
	private java.lang.String isRed;
	/**已经开过发票，查看开票信息，0未开票，1开具发票失败，2下载发票失败，3下载发票成功，5冲红成功，6冲红失败*/
	private java.lang.String isInvoice;
	/**是否申请作废，0作废中，1作废失败，2申请作废，3失败原因作废成功*/
	private java.lang.String isCancel;
	/**打印标志（0已打印，1未打印）*/
	private java.lang.String printFlag;
	/**发票报送状态（0未报送，1已报送，2报送失败，3报送中，4验签失败）*/
	private java.lang.String uploadFlag;
	/**作废标志（0未作废，1已作废）*/
	private java.lang.String cancelFlag;
	/**返回代码*/
	private java.lang.String retCode;
	/**开票日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date invDate;
	/**开票成功日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createSuccessDate;
	/**冲红成功日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createRedDate;
	/**作废成功日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createCancelDate;
	/**写入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private java.util.Date createdDate;
	/**数据状态 1:正常  0: 无用*/
	private java.lang.Integer status;
	/**发票类型 1:蓝票 2:红票*/
	private java.lang.Integer invoiceType;
	/**错误描述信息*/
	private java.lang.String retMsg;
	/**企业纳税人识别号*/
	private java.lang.String custTaxCode;
	/**销货清单标志：0打印发票，1打印清单*/
	private java.lang.String goodsListFlag;
	/**是否显示边距确认对话框：0否，1是*/
	private java.lang.String infoShowPrtdlg;
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
	/**原始XML文件*/
	private java.lang.String xml;
}
