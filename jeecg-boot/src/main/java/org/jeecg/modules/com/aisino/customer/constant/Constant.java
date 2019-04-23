package org.jeecg.modules.com.aisino.customer.constant;

import org.jeecg.common.constant.CommonConstant;

/**
 * @author cheng
 * @PackageName:org.jeecg.modules.customer.constant
 * @ClassName:Constant
 * @Description:
 * @data 2019/4/22 11:19
 */
public class Constant implements CommonConstant {
    /**
     * 授权
     */
    public static Integer STATUS_AUTHOR_EFFECTIVITY = 1;
    /**
     * 授权无效
     */
    public static Integer STATUS_AUTHOR_DISABLED = 0;
    /**
     * 已到锁死期 islockReached
     */
    public static Integer STATUS_LOCK_EFFECTIVITY = 1;
    /**
     * 未到锁死期
     */
    public static Integer STATUS_LOCK_DISABLED = 0;
}
