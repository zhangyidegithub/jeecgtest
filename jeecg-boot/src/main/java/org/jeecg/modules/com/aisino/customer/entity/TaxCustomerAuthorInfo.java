package org.jeecg.modules.com.aisino.customer.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;

/**
 * @Description: 企业授权明细
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Data
@TableName("TAX_CUSTOMER_AUTHOR_INFO")
public class TaxCustomerAuthorInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private String id;
	/**客户信息表ID*/
	private String customerId;
	/**企业纳税人识别号*/
    @Excel(name = "企业纳税人识别号", width = 15)
	private String custTaxCode;
	/**盘号，用于客户端授权验证使用*/
    @Excel(name = "盘号，用于客户端授权验证使用", width = 15)
	private String checkCode;
	/**开始时间*/
	@Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date authorBeginDate;
	/**失效时间*/
	@Excel(name = "失效时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date authorEndDate;
	/**操作人*/
    @Excel(name = "操作人", width = 15)
	private String createdUserBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdDate;
}
