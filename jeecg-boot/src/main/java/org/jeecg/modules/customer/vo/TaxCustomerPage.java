package org.jeecg.modules.customer.vo;

import java.util.List;

import org.jeecg.modules.customer.entity.TaxCustomerAuthor;
import org.jeecg.modules.customer.entity.TaxCustomerAuthorInfo;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Data
public class TaxCustomerPage {
	
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
	/**备用字段3*/
  	@Excel(name = "备用字段3", width = 15)
	private String p3;
	/**用户密码*/
  	@Excel(name = "用户密码", width = 15)
	private String password;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createTime;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private String updateBy;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateTime;
	/**主表ID*/
  	@Excel(name = "主表ID", width = 15)
	private String customerId;
	/**接口版本*/
  	@Excel(name = "接口版本", width = 15)
	private String version;
	/**企业纳税人识别号*/
  	@Excel(name = "企业纳税人识别号", width = 15)
	private String custTaxCode;
	/**企业纳税人名称*/
  	@Excel(name = "企业纳税人名称", width = 15)
	private String custTaxName;
	/**企业纳税人地址电话*/
  	@Excel(name = "企业纳税人地址电话", width = 15)
	private String custAddressPhone;
	/**企业纳税人银行账号*/
  	@Excel(name = "企业纳税人银行账号", width = 15)
	private String custBankAccount;
	
	@ExcelCollection(name="企业授权")
	private List<TaxCustomerAuthor> taxCustomerAuthorList;
	@ExcelCollection(name="企业授权明细")
	private List<TaxCustomerAuthorInfo> taxCustomerAuthorInfoList;
	
}
