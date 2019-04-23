package org.jeecg.modules.customer.service.impl;

import org.jeecg.modules.customer.constant.Constant;
import org.jeecg.modules.customer.entity.TaxCustomerAuthorInfo;
import org.jeecg.modules.customer.mapper.TaxCustomerAuthorInfoMapper;
import org.jeecg.modules.customer.mapper.TaxCustomerAuthorMapper;
import org.jeecg.modules.customer.service.ITaxCustomerAuthorInfoService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
			Integer result =  taxCustomerAuthorMapper.updateAuthorStatus(entity.getCheckCode(),entity.getCustomerId(), Constant.STATUS_AUTHOR_EFFECTIVITY,Constant.STATUS_LOCK_EFFECTIVITY);
		}
		return flag;
	}
}
