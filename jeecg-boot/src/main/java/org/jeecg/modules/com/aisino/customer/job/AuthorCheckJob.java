package org.jeecg.modules.com.aisino.customer.job;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.DateUtils;
import org.jeecg.modules.com.aisino.customer.entity.TaxCustomerAuthor;
import org.jeecg.modules.com.aisino.customer.service.ITaxCustomerAuthorInfoService;
import org.jeecg.modules.com.aisino.customer.service.ITaxCustomerAuthorService;
import org.jeecg.modules.com.aisino.customer.service.ITaxCustomerService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cheng
 * @PackageName:org.jeecg.modules.com.aisino.customer.job
 * @ClassName:AuthorCheckJob
 * @Description: 检验企业盘是否授权过期
 * @data 2019-04-24 13:42
 */
@Slf4j
@Component
public class AuthorCheckJob implements Job {
    @Autowired
    private ITaxCustomerService taxCustomerService;
    @Autowired
    private ITaxCustomerAuthorService taxCustomerAuthorService;
    @Autowired
    private ITaxCustomerAuthorInfoService taxCustomerAuthorInfoService;
    /**
     * 若参数变量名修改 QuartzJobController中也需对应修改
     */
    private String parameter;
    private Lock lock = new ReentrantLock();

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    private static class Holder {
        private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }

    public static ExecutorService getExecutorService(){
        return Holder.cachedThreadPool;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        lock.lock();
        try {
            //ExecutorService cachedThreadPool = getExecutorService();
            List<TaxCustomerAuthor> authors =  taxCustomerAuthorService.selectAuthorExpire();
            for (TaxCustomerAuthor author : authors) {
                author.setIslockReached("1");
                author.setAuthorStatus(0);
                taxCustomerAuthorService.updateById(author);
                log.info(String.format(" 盘授权过期处理 customerId:%s CustTaxCode:%s  时间:" + DateUtils.now(),author.getCustomerId(),author.getCustTaxCode()));

            }
        }catch (Exception e){
            log.error("盘授权过期处理异常",e);
        }finally {
            lock.unlock();
        }



    }
}
