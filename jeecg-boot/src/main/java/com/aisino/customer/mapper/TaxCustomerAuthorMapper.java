package com.aisino.customer.mapper;

import com.aisino.customer.entity.TaxCustomerAuthor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: 企业授权
 * @author： zhangchengping
 * @date：   2019-04-18
 * @version： V1.0
 */
@Mapper
public interface TaxCustomerAuthorMapper extends BaseMapper<TaxCustomerAuthor> {

	public boolean deleteByMainId(String mainId);
    
	public List<TaxCustomerAuthor> selectByMainId(String mainId);

	/**
	 * 更新盘授权状态
	 * @param checkCode
	 * @param customerId
	 * @param authorStatus
	 */
	public Integer updateAuthorStatus(@Param("checkCode") String checkCode, @Param("customerId")String customerId, @Param("authorStatus")Integer authorStatus,@Param("islockReached")Integer islockReached);
	/**
	 * 检查授权过期得苏剧
	 * @return List<TaxCustomerAuthor>
	 */
	public List<TaxCustomerAuthor> selectAuthorExpire();
}
