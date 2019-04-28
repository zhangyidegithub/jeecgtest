package org.jeecg.modules.com.aisino.taxInvoice.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Report implements Serializable {
    private String userName;//用户名
    private Integer count;//统计数量
    private String year;//年份
    private String month;//月份
}
