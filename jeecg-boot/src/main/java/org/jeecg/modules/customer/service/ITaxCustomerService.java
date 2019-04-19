package org.jeecg.modules.customer.service;

import org.jeecg.modules.customer.entity.TaxCustomerAuthor;
import org.jeecg.modules.customer.entity.TaxCustomerAuthorInfo;
import org.jeecg.modules.customer.entity.TaxCustomer;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 企业信息
 * @author： jeecg-boot
 * @date：   2019-04-18
 * @version： V1.0
 */
public interface ITaxCustomerService extends IService<TaxCustomer> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(TaxCustomer taxCustomer, List<TaxCustomerAuthor> taxCustomerAuthorList, List<TaxCustomerAuthorInfo> taxCustomerAuthorInfoList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TaxCustomer taxCustomer, List<TaxCustomerAuthor> taxCustomerAuthorList, List<TaxCustomerAuthorInfo> taxCustomerAuthorInfoList);
	
	/**
	 * 删除一对多
	 */
	public void delMain(String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain(Collection<? extends Serializable> idList);
	
}
