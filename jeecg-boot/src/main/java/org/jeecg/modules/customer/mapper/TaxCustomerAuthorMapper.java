package org.jeecg.modules.customer.mapper;

import java.util.List;
import org.jeecg.modules.customer.entity.TaxCustomerAuthor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 企业授权
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Mapper
public interface TaxCustomerAuthorMapper extends BaseMapper<TaxCustomerAuthor> {

	public boolean deleteByMainId(String mainId);
    
	public List<TaxCustomerAuthor> selectByMainId(String mainId);
}
