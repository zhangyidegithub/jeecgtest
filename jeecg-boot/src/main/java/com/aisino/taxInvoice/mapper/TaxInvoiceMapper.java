package com.aisino.taxInvoice.mapper;

import java.util.List;

import com.aisino.taxInvoice.entity.Report;
import com.aisino.taxInvoice.entity.TaxInvoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapping;

/**
 * @Description: 红字发票申请单
 * @author： jeecg-boot
 * @date：   2019-04-25
 * @version： V1.0
 */
@Mapper
public interface TaxInvoiceMapper extends BaseMapper<TaxInvoice> {
    /**
     * 报表统计
     * @param report    请求参数
     * @return  Report
     */
    @Mapper
    public List<Report> findByReport(Report report);

    /**
     * 报表开票量查询
     * @param report    请求参数
     * @return  Report
     */
    @Mapper
    public Report findByNumber(Report report);

    /**
     * 报表金额查询
     * @param report    请求参数
     * @return  Report
     */
    @Mapper
    public Report findByAmount(Report report);



}
