package com.aisino.customer.service;

import com.aisino.customer.entity.TaxCustomerAuthor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 企业授权
 * @author： zhangchengping
 * @date：   2019-04-18
 * @version： V1.0
 */
public interface ITaxCustomerAuthorService extends IService<TaxCustomerAuthor> {

	public List<TaxCustomerAuthor> selectByMainId(String mainId);
	/**
	 * 检查授权过期得数据
	 * @return
	 */
	public List<TaxCustomerAuthor> selectAuthorExpire();

	/**
	 * 批量删除
	 * @return
	 */
	public void deleteBatchByIds(String ids);
}
