package com.aisino.customer.mapper;


import com.aisino.customer.entity.TaxCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 企业信息
 * @author： zhangchengping
 * @date：   2019-04-18
 * @version： V1.0
 */
@Mapper
public interface TaxCustomerMapper extends BaseMapper<TaxCustomer> {

}
