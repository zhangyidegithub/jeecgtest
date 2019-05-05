package com.aisino.customer.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 企业授权
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Data
@TableName("TAX_CUSTOMER_AUTHOR")
public class TaxCustomerAuthor implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主表ID*/
	@TableId(type = IdType.UUID)
	private String id;
	/**客户信息表ID*/
	private String customerId;
	/**企业纳税人识别号*/
    @Excel(name = "企业纳税人识别号", width = 15)
	private String custTaxCode;
	/**授权状态 1:有效  0:无效*/
    @Excel(name = "授权状态 1:有效  0:无效", width = 15)
	private Integer authorStatus;
	/**联系人*/
    @Excel(name = "联系人", width = 15)
	private String linkMan;
	/**联系人手机号*/
    @Excel(name = "联系人手机号", width = 15)
	private String linkPhone;
	/**备用字段1*/
    @Excel(name = "备用字段1", width = 15)
	private String p1;
	/**备用字段2*/
    @Excel(name = "备用字段2", width = 15)
	private String p2;
	/**盘号，用于客户端授权验证使用*/
    @Excel(name = "盘号，用于客户端授权验证使用", width = 15)
	private String checkCode;
	/**本开票机号码，主开票机为0*/
    @Excel(name = "本开票机号码，主开票机为0", width = 15)
	private String machineNo;
	/**无票标志：0为有可用发票，1为无可用发票*/
    @Excel(name = "无票标志：0为有可用发票，1为无可用发票", width = 15)
	private String isinvEmpty;
	/**抄税标志：0为未到抄税期，1为已到抄税期*/
    @Excel(name = "抄税标志：0为未到抄税期，1为已到抄税期", width = 15)
	private String isrepReached;
	/**锁死标志：0为未到锁死期，1为已到锁死期*/
    @Excel(name = "锁死标志：0为未到锁死期，1为已到锁死期", width = 15)
	private String islockReached;
	/**备用字段3*/
    @Excel(name = "备用字段3", width = 15)
	private String p3;
}
