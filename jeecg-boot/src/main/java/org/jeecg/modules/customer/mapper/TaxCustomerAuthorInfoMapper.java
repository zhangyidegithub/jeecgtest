package org.jeecg.modules.customer.mapper;

import java.util.List;
import org.jeecg.modules.customer.entity.TaxCustomerAuthorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: 企业授权明细
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Mapper
public interface TaxCustomerAuthorInfoMapper extends BaseMapper<TaxCustomerAuthorInfo> {

	public boolean deleteByMainId(String mainId);
    
	public List<TaxCustomerAuthorInfo> selectByMainId(String mainId);
}
