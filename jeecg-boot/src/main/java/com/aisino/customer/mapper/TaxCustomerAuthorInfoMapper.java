package com.aisino.customer.mapper;

import com.aisino.customer.entity.TaxCustomerAuthorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 企业授权明细
 * @author：zhangchengping
 * @date：   2019-04-18
 * @version： V1.0
 */
@Mapper
public interface TaxCustomerAuthorInfoMapper extends BaseMapper<TaxCustomerAuthorInfo> {

	public boolean deleteByMainId(String mainId);
    
	public List<TaxCustomerAuthorInfo> selectByMainId(String mainId);
}
