package org.jeecg.modules.com.aisino.customer.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 企业信息
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Data
@TableName("TAX_CUSTOMER")
public class TaxCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
	/**主表ID*/
	@TableId(type = IdType.UUID)
	private String customerId;
	/**联系人*/
	private String linkMan;
	/**联系人手机号*/
	private String linkPhone;
	/**备用字段1*/
	private String p1;
	/**备用字段2*/
	private String p2;
	/**备用字段3*/
	private String p3;
	/**用户密码*/
	private String password;
	/**创建人*/
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**接口版本*/
	private String version;
	/**企业纳税人识别号*/
	private String custTaxCode;
	/**企业纳税人名称*/
	private String custTaxName;
	/**企业纳税人地址电话*/
	private String custAddressPhone;
	/**企业纳税人银行账号*/
	private String custBankAccount;
}
