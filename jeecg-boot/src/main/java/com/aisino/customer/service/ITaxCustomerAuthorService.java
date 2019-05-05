package com.aisino.customer.service;

import com.aisino.customer.entity.TaxCustomerAuthor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 企业授权
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
public interface ITaxCustomerAuthorService extends IService<TaxCustomerAuthor> {

	public List<TaxCustomerAuthor> selectByMainId(String mainId);
	/**
	 * 检查授权还在有效期内的数据
	 * @return
	 */
	public List<TaxCustomerAuthor> selectAuthorExpire();
}
