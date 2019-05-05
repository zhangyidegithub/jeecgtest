package com.aisino.customer.service.impl;

import com.aisino.customer.constant.Constant;
import com.aisino.customer.entity.*;
import com.aisino.customer.mapper.*;
import com.aisino.customer.service.ITaxCustomerAuthorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description: 企业授权明细
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Service
public class TaxCustomerAuthorInfoServiceImpl extends ServiceImpl<TaxCustomerAuthorInfoMapper, TaxCustomerAuthorInfo> implements ITaxCustomerAuthorInfoService {

	@Autowired
	TaxCustomerAuthorInfoMapper taxCustomerAuthorInfoMapper;
	@Autowired
	TaxCustomerAuthorMapper taxCustomerAuthorMapper;
	
	@Override
	public List<TaxCustomerAuthorInfo> selectByMainId(String mainId) {
		return taxCustomerAuthorInfoMapper.selectByMainId(mainId);
	}

	@Override
	@Transactional
	public boolean save(TaxCustomerAuthorInfo entity) {
		boolean flag = super.save(entity);
		if(flag){
			Integer result =  taxCustomerAuthorMapper.updateAuthorStatus(entity.getCheckCode(),entity.getCustomerId(), Constant.STATUS_AUTHOR_EFFECTIVITY,Constant.STATUS_LOCK_DISABLED);
		}
		return flag;
	}
}
