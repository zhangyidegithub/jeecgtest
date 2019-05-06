package com.aisino.customer.service.impl;

import com.aisino.customer.entity.TaxCustomerAuthor;
import com.aisino.customer.mapper.TaxCustomerAuthorInfoMapper;
import com.aisino.customer.mapper.TaxCustomerAuthorMapper;
import com.aisino.customer.service.ITaxCustomerAuthorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 企业授权
 * @author： zhangchengping
 * @date：   2019-04-18
 * @version： V1.0
 */
@Service
public class TaxCustomerAuthorServiceImpl extends ServiceImpl<TaxCustomerAuthorMapper, TaxCustomerAuthor> implements ITaxCustomerAuthorService {
	@Autowired
	private TaxCustomerAuthorMapper taxCustomerAuthorMapper;
	@Autowired
	TaxCustomerAuthorInfoMapper taxCustomerAuthorInfoMapper;
	
	@Override
	public List<TaxCustomerAuthor> selectByMainId(String mainId) {
		return taxCustomerAuthorMapper.selectByMainId(mainId);
	}

	@Override
	public List<TaxCustomerAuthor> selectAuthorExpire() {
		return taxCustomerAuthorMapper.selectAuthorExpire();
	}

	@Override
	@Transactional
	public void deleteBatchByIds(String ids) {
		if(StringUtils.isNotBlank(ids)){
			taxCustomerAuthorInfoMapper.deleteByAuthorIds(Arrays.asList(ids.split(",")));
		}
		super.removeByIds(Arrays.asList(ids.split(",")));

	}
}
