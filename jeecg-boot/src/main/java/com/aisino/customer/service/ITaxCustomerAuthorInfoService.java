package com.aisino.customer.service;

import com.aisino.customer.entity.TaxCustomerAuthorInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 企业授权明细
 * @author： zhangchengping
 * @date：   2019-04-18
 * @version： V1.0
 */
public interface ITaxCustomerAuthorInfoService extends IService<TaxCustomerAuthorInfo> {

	public List<TaxCustomerAuthorInfo> selectByMainId(String mainId);
}
