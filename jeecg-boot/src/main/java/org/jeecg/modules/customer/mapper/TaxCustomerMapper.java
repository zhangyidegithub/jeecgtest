package org.jeecg.modules.customer.mapper;


import org.jeecg.modules.customer.entity.TaxCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Description: 企业信息
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
@Mapper
public interface TaxCustomerMapper extends BaseMapper<TaxCustomer> {

}
