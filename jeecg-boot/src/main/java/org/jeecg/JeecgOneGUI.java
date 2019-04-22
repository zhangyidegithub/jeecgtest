package org.jeecg;

import org.jeecgframework.codegenerate.generate.impl.CodeGenerateOne;
import org.jeecgframework.codegenerate.generate.pojo.TableVo;
import org.jeecgframework.codegenerate.window.CodeWindow;

/**
 * @Title: 单表代码生成器入口
 * @author 张代浩
 * @site www.jeecg.org
 * @version：V1.0.1
 */
public class JeecgOneGUI {

	/** 详细使用手册： http://jeecg-boot.mydoc.io/?t=338140 */
    public static void main(String[] args) {

		/*System.out.println("----jeecg--------- Code------------- Generation -----[单表模型]------- 生成中。。。");
		TableVo var1 = new TableVo();
		var1.setTableName("TAX_CUSTOMER_AUTHOR_INFO");
		var1.setPrimaryKeyPolicy("uuid");
		var1.setEntityPackage("customer2");
		var1.setEntityName("TaxCustomerAuthorInfo");
		var1.setFtlDescription("授权信息");
		(new CodeGenerateOne(var1)).generateCodeFile();
		System.out.println("----jeecg--------- Code------------- Generation -----[单表模型]------- 生成完成。。。");*/
    	new CodeWindow().pack();
	}

}