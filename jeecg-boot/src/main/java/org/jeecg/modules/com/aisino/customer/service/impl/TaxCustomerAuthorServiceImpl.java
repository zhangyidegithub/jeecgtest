package org.jeecg.modules.com.aisino.customer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.com.aisino.customer.entity.TaxCustomerAuthor;
import org.jeecg.modules.com.aisino.customer.mapper.TaxCustomerAuthorMapper;
import org.jeecg.modules.com.aisino.customer.service.ITaxCustomerAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 企业授权
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Service
public class TaxCustomerAuthorServiceImpl extends ServiceImpl<TaxCustomerAuthorMapper, TaxCustomerAuthor> implements ITaxCustomerAuthorService {
	@Autowired
	private TaxCustomerAuthorMapper taxCustomerAuthorMapper;
	
	@Override
	public List<TaxCustomerAuthor> selectByMainId(String mainId) {
		return taxCustomerAuthorMapper.selectByMainId(mainId);
	}

	@Override
	public List<TaxCustomerAuthor> selectAuthorExpire() {
		return taxCustomerAuthorMapper.selectAuthorExpire();
	}
}
