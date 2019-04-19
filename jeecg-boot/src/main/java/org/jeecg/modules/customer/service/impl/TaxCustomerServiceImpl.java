package org.jeecg.modules.customer.service.impl;

import org.jeecg.modules.customer.entity.TaxCustomer;
import org.jeecg.modules.customer.entity.TaxCustomerAuthor;
import org.jeecg.modules.customer.entity.TaxCustomerAuthorInfo;
import org.jeecg.modules.customer.mapper.TaxCustomerAuthorMapper;
import org.jeecg.modules.customer.mapper.TaxCustomerAuthorInfoMapper;
import org.jeecg.modules.customer.mapper.TaxCustomerMapper;
import org.jeecg.modules.customer.service.ITaxCustomerService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 企业信息
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Service
public class TaxCustomerServiceImpl extends ServiceImpl<TaxCustomerMapper, TaxCustomer> implements ITaxCustomerService {

	@Autowired
	private TaxCustomerMapper taxCustomerMapper;
	@Autowired
	private TaxCustomerAuthorMapper taxCustomerAuthorMapper;
	@Autowired
	private TaxCustomerAuthorInfoMapper taxCustomerAuthorInfoMapper;
	
	@Override
	@Transactional
	public void saveMain(TaxCustomer taxCustomer, List<TaxCustomerAuthor> taxCustomerAuthorList,List<TaxCustomerAuthorInfo> taxCustomerAuthorInfoList) {
		taxCustomerMapper.insert(taxCustomer);
		for(TaxCustomerAuthor entity:taxCustomerAuthorList) {
			//外键设置
			entity.setCustomerId(taxCustomer.getCustomerId());
			taxCustomerAuthorMapper.insert(entity);
		}
		for(TaxCustomerAuthorInfo entity:taxCustomerAuthorInfoList) {
			//外键设置
			entity.setCustomerId(taxCustomer.getCustomerId());
			taxCustomerAuthorInfoMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(TaxCustomer taxCustomer,List<TaxCustomerAuthor> taxCustomerAuthorList,List<TaxCustomerAuthorInfo> taxCustomerAuthorInfoList) {
		taxCustomerMapper.updateById(taxCustomer);
		
		//1.先删除子表数据
		taxCustomerAuthorMapper.deleteByMainId(taxCustomer.getCustomerId());
		taxCustomerAuthorInfoMapper.deleteByMainId(taxCustomer.getCustomerId());
		
		//2.子表数据重新插入
		for(TaxCustomerAuthor entity:taxCustomerAuthorList) {
			//外键设置
			entity.setCustomerId(taxCustomer.getCustomerId());
			taxCustomerAuthorMapper.insert(entity);
		}
		for(TaxCustomerAuthorInfo entity:taxCustomerAuthorInfoList) {
			//外键设置
			entity.setCustomerId(taxCustomer.getCustomerId());
			taxCustomerAuthorInfoMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		taxCustomerMapper.deleteById(id);
		taxCustomerAuthorMapper.deleteByMainId(id);
		taxCustomerAuthorInfoMapper.deleteByMainId(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			taxCustomerMapper.deleteById(id);
			taxCustomerAuthorMapper.deleteByMainId(id.toString());
			taxCustomerAuthorInfoMapper.deleteByMainId(id.toString());
		}
	}
	
}
