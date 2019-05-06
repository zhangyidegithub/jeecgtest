package com.aisino.customer.mapper;

import com.aisino.customer.entity.TaxCustomerAuthorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

	/**
	 * 根据盘号删除授权信息
	 * @param customerId
	 * @param checkCode
	 * @return
	 */
	public boolean deleteByCheckCode(@Param(value = "customerId") String customerId,@Param(value = "checkCode") String checkCode);

	/**
	 *根据盘IDs删除授权信息
	 * @param authorIds
	 * @return
	 */
	public boolean deleteByAuthorIds(List<String> authorIds);
}
