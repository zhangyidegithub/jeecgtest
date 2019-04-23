<template>
  <a-drawer
    :title="title"
    :width="1200"
    placement="right"
    :visible="visible"
    :maskClosable="false"
    :closable="true"
    @ok="handleCancel"
    @close="handleCancel">


<!--    :title="title"-->
<!--    :width="1200"-->
<!--    :visible="visible"-->
<!--    :maskClosable="false"-->
<!--    :confirmLoading="confirmLoading"-->
<!--    @ok="handleOk"-->
<!--    @cancel="handleCancel"-->
    <a-spin :spinning="confirmLoading">
      <!-- 主表单区域 -->
      <a-form :form="form">
        <a-row>
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="发票流水号">-->
<!--              <a-input placeholder="请输入发票流水号" v-decorator="['fpqqlsh', validatorRules.fpqqlsh ]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="订单编号">
              <a-input  disabled placeholder="请输入订单编号" v-decorator="['ddbh', validatorRules.ddbh ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="购方名称">
              <a-input disabled  placeholder="请输入购方名称" v-decorator="['clientTaxName', validatorRules.clientTaxName ]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="备注发票红票流水号">-->
<!--              <a-input placeholder="请输入备注发票红票流水号" v-decorator="['fpqqlshRed', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="销方识别号">-->
<!--              <a-input placeholder="请输入销方识别号" v-decorator="['sellerTaxCode', validatorRules.sellerTaxCode ]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
        </a-row>
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="销方名称">-->
<!--              <a-input placeholder="请输入销方名称" v-decorator="['sellerTaxName', validatorRules.sellerTaxName ]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="销方地址电话">-->
<!--              <a-input placeholder="请输入销方地址电话" v-decorator="['sellerAddressPhone', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="销方银行账号">-->
<!--              <a-input placeholder="请输入销方银行账号" v-decorator="['sellerBankAccount', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="购方名称">-->
<!--              <a-input placeholder="请输入购方名称" v-decorator="['clientTaxName', validatorRules.clientTaxName ]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="购方识别号">
              <a-input disabled  placeholder="请输入购方识别号" v-decorator="['clientTaxCode', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="购方地址电话">
              <a-input disabled  placeholder="请输入购方地址电话" v-decorator="['clientAddressPhone', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="购方银行账号">-->
<!--              <a-input placeholder="请输入购方银行账号" v-decorator="['clientBankAccount', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="开票员">
              <a-input  disabled placeholder="请输入开票员" v-decorator="['invoicer', validatorRules.invoicer ]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="复核人">
              <a-input disabled  placeholder="请输入复核人" v-decorator="['checker', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row>
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="复核人">-->
<!--              <a-input placeholder="请输入复核人" v-decorator="['checker', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="收款员">
              <a-input disabled  placeholder="请输入收款员" v-decorator="['cashier', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="价税合计金额">
              <a-input disabled placeholder="请输入价税合计金额" v-decorator="['amount', validatorRules.amount ]"/>
            </a-form-item>
          </a-col>
        </a-row>
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="开票类型">-->
<!--              <a-input placeholder="请输入开票类型" v-decorator="['infoKind', validatorRules.infoKind ]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
        <a-row>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="发票号码">
              <a-input disabled placeholder="请输入发票号码" v-decorator="['infoNumber', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="发票代码">
              <a-input  disabled placeholder="请输入发票代码" v-decorator="['infoTypeCode', {}]"/>
            </a-form-item>
          </a-col>
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="清单标志">-->
<!--              <a-input placeholder="请输入清单标志" v-decorator="['listFlag', validatorRules.listFlag ]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
        </a-row>
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="清单发票项目名称">-->
<!--              <a-input placeholder="请输入清单发票项目名称" v-decorator="['qdxmmc', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="冲红原因">-->
<!--              <a-input placeholder="请输入冲红原因" v-decorator="['chyy', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="价税合计金额">-->
<!--              <a-input placeholder="请输入价税合计金额" v-decorator="['amount', validatorRules.amount ]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="合计不含税金额">-->
<!--              <a-input placeholder="请输入合计不含税金额" v-decorator="['infoAmount', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="合计税额">-->
<!--              <a-input placeholder="请输入合计税额" v-decorator="['tax', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="信息表类型">-->
<!--              <a-input placeholder="请输入信息表类型" v-decorator="['billType', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="多税率标志">-->
<!--              <a-input placeholder="请输入多税率标志" v-decorator="['ismutiRate', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="税率，多税率时为空">-->
<!--              <a-input placeholder="请输入税率，多税率时为空" v-decorator="['taxRate', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="申请说明">-->
<!--              <a-input placeholder="请输入申请说明" v-decorator="['reqMemo', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="商品编码版本号">-->
<!--              <a-input placeholder="请输入商品编码版本号" v-decorator="['spbmbbh', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
        <a-row>
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="含税税率标志">-->
<!--              <a-input placeholder="请输入含税税率标志" v-decorator="['slbz', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="备注">
              <a-textarea disabled placeholder="备注信息" v-decorator="['bz', {}]"/>
            </a-form-item>
          </a-col>
          <a-col :span="12" :gutter="8">
            <a-form-item
              :labelCol="labelCol"
              :wrapperCol="wrapperCol"
              label="描述信息">
              <a-textarea disabled  placeholder="描述信息" v-decorator="['retMsg', {}]"/>
            </a-form-item>
          </a-col>
        </a-row>
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="收购标志">-->
<!--              <a-input placeholder="请输入收购标志" v-decorator="['sgbz', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="代开标志">-->
<!--              <a-input placeholder="请输入代开标志" v-decorator="['dkbz', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="折扣标志">-->
<!--              <a-input placeholder="请输入折扣标志" v-decorator="['zkbz', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="是否申请开票">-->
<!--              <a-input placeholder="请输入是否申请开票" v-decorator="['isApply', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="是否红字单">-->
<!--              <a-input placeholder="请输入是否红字单" v-decorator="['isRed', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="已经开过发票">-->
<!--              <a-input placeholder="请输入已经开过发票" v-decorator="['isInvoice', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="是否申请作废">-->
<!--              <a-input placeholder="请输入是否申请作废" v-decorator="['isCancel', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="打印标志">-->
<!--              <a-input placeholder="请输入打印标志" v-decorator="['printFlag', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="发票报送状态">-->
<!--              <a-input placeholder="请输入发票报送状态" v-decorator="['uploadFlag', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="作废标志">-->
<!--              <a-input placeholder="请输入作废标志" v-decorator="['cancelFlag', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="返回代码">-->
<!--              <a-input placeholder="请输入返回代码" v-decorator="['retCode', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="开票日期">-->
<!--              <a-date-picker-->
<!--                placeholder="请输入开票日期"-->
<!--                style="width:100%"-->
<!--                v-decorator="[ 'invDate', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="开票成功日期">-->
<!--              <a-date-picker-->
<!--                placeholder="请输入开票成功日期"-->
<!--                style="width:100%"-->
<!--                v-decorator="[ 'createSuccessDate', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="冲红成功日期">-->
<!--              <a-date-picker-->
<!--                placeholder="请输入冲红成功日期"-->
<!--                style="width:100%"-->
<!--                v-decorator="[ 'createRedDate', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="作废成功日期">-->
<!--              <a-date-picker-->
<!--                placeholder="请输入作废成功日期"-->
<!--                style="width:100%"-->
<!--                v-decorator="[ 'createCancelDate', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="写入时间">-->
<!--              <a-date-picker-->
<!--                placeholder="请输入写入时间"-->
<!--                style="width:100%"-->
<!--                v-decorator="[ 'createdDate', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="数据状态">-->
<!--              <a-input placeholder="请输入数据状态" v-decorator="['status', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="发票类型">-->
<!--              <a-input placeholder="请输入发票类型" v-decorator="['invoiceType', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="错误描述信息">-->
<!--              <a-input placeholder="请输入错误描述信息" v-decorator="['retMsg', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="企业纳税人识别号">-->
<!--              <a-input placeholder="请输入企业纳税人识别号" v-decorator="['custTaxCode', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--        </a-row>-->
<!--        <a-row>-->
<!--          <a-col :span="12" :gutter="8">-->
<!--            <a-form-item-->
<!--              :labelCol="labelCol"-->
<!--              :wrapperCol="wrapperCol"-->
<!--              label="销货清单标志">-->
<!--              <a-input placeholder="请输入销货清单标志" v-decorator="['goodsListFlag', {}]"/>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
<!--&lt;!&ndash;          <a-col :span="12" :gutter="8">&ndash;&gt;-->
<!--&lt;!&ndash;            <a-form-item&ndash;&gt;-->
<!--&lt;!&ndash;              :labelCol="labelCol"&ndash;&gt;-->
<!--&lt;!&ndash;              :wrapperCol="wrapperCol"&ndash;&gt;-->
<!--&lt;!&ndash;              label="是否显示边距确认对话框">&ndash;&gt;-->
<!--&lt;!&ndash;              <a-input placeholder="请输入是否显示边距确认对话框：0否，1是" v-decorator="['infoShowPrtdlg', {}]"/>&ndash;&gt;-->
<!--&lt;!&ndash;            </a-form-item>&ndash;&gt;-->
<!--&lt;!&ndash;          </a-col>&ndash;&gt;-->
<!--        </a-row>-->
      </a-form>

      <!-- 子表单区域 -->
      <a-tabs v-model="activeKey" @change="handleChangeTabs">
        <a-tab-pane tab="客户明细" :key="refKeys[0]" :forceRender="true">
          <j-editable-table
            :ref="refKeys[0]"
            :loading="taxInvoiceDetailTable.loading"
            :columns="taxInvoiceDetailTable.columns"
            :dataSource="taxInvoiceDetailTable.dataSource"
            :maxHeight="600"
            :rowNumber="false"
            :rowSelection="false"
            :actionButton="false"
            :aria-readonly="true"/>
        </a-tab-pane>
      </a-tabs>

    </a-spin>
  </a-drawer>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableOneToManyMixin } from '@/mixins/JEditableTableOneToManyMixin'

  export default {
    name: 'TaxInvoiceModal',
    mixins: [JEditableTableOneToManyMixin],
    data() {
      return {
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
          fpqqlsh: { rules: [{ required: true, message: '请输入发票请求唯一流水号!' }] },
          ddbh: { rules: [{ required: true, message: '请输入订单编号!' }] },
          sellerTaxCode: { rules: [{ required: true, message: '请输入销方识别号!' }] },
          sellerTaxName: { rules: [{ required: true, message: '请输入销方名称!' }] },
          clientTaxName: { rules: [{ required: true, message: '请输入购方名称!' }] },
          invoicer: { rules: [{ required: true, message: '请输入开票员!' }] },
          infoKind: { rules: [{ required: true, message: '请输入开票类型（0 专用发票；2 普通发票；11 货物运输业增值税专用发票；12 机动车销售统一发票）!' }] },
          listFlag: { rules: [{ required: true, message: '请输入清单标志!' }] },
          amount: { rules: [{ required: true, message: '请输入价税合计金额!' }] },
        },
        refKeys: ['taxInvoiceDetail', ],
        activeKey: 'taxInvoiceDetail',
        // 客户明细
        taxInvoiceDetailTable: {
          loading: false,
          dataSource: [],
          columns: [
            {
              title: '项目名称',
              key: 'spmc',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '商品编码',
              key: 'spbm',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '项目单位',
              key: 'jldw',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '规格型号',
              key: 'ggxh',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },

            // {
            //   title: '含税标志',
            //   key: 'hsjbz',
            //   type: FormTypes.input,
            //   defaultValue: '',
            //   placeholder: '请输入${title}',
            //   validateRules: [{ required: true, message: '${title}不能为空' }],
            // },
            // {
            //   title: '发票行性质',
            //   key: 'fphxz',
            //   type: FormTypes.input,
            //   defaultValue: '',
            //   placeholder: '请输入${title}',
            //   validateRules: [{ required: true, message: '${title}不能为空' }],
            // },
            {
              title: '项目单价',
              key: 'dj',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },

            // {
            //   title: '自行编码',
            //   key: 'zxbm',
            //   type: FormTypes.input,
            //   defaultValue: '',
            //   placeholder: '请输入${title}',
            // },
            // {
            //   title: '优惠政策标识',
            //   key: 'yhzcbs',
            //   type: FormTypes.input,
            //   defaultValue: '',
            //   placeholder: '请输入${title}',
            // },
            // {
            //   title: '零税率标识',
            //   key: 'lslbs',
            //   type: FormTypes.input,
            //   defaultValue: '',
            //   placeholder: '请输入${title}',
            // },
            // {
            //   title: '增值税特殊管理',
            //   key: 'zzstsgl',
            //   type: FormTypes.input,
            //   defaultValue: '',
            //   placeholder: '请输入${title}',
            // },
            // {
            //   title: '扣除额',
            //   key: 'kce',
            //   type: FormTypes.input,
            //   defaultValue: '',
            //   placeholder: '请输入${title}',
            // },
            {
              title: '项目金额',
              key: 'je',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '项目数量',
              key: 'sl',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '税率',
              key: 'slv',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              validateRules: [{ required: true, message: '${title}不能为空' }],
            },
            {
              title: '税额',
              key: 'se',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
              disabled:true,
            },
          ]
        },
        url: {
          add: "/com/aisino/taxInvoice/add",
          edit: "/com/aisino/taxInvoice/edit",
          taxInvoiceDetail: {
            list: '/com/aisino/taxInvoice/queryTaxInvoiceDetailByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          // this.form.setFieldsValue(pick(this.model, 'ddbh', 'sellerTaxName', 'clientTaxCode', 'clientAddressPhone','invoicer', 'checker', 'cashier', 'amount', 'infoTypeCode', 'infoNumber','bz','retMsg', ))
          this.form.setFieldsValue(pick(this.model,  'ddbh', 'clientTaxName', 'clientTaxCode', 'clientAddressPhone','invoicer', 'checker', 'cashier', 'amount','infoNumber', 'infoTypeCode', 'bz','retMsg',))
          // 时间格式化
          // this.form.setFieldsValue({ invDate: this.model.invDate ? moment(this.model.invDate) : null })
          // this.form.setFieldsValue({ createSuccessDate: this.model.createSuccessDate ? moment(this.model.createSuccessDate) : null })
          // this.form.setFieldsValue({ createRedDate: this.model.createRedDate ? moment(this.model.createRedDate) : null })
          // this.form.setFieldsValue({ createCancelDate: this.model.createCancelDate ? moment(this.model.createCancelDate) : null })
          // this.form.setFieldsValue({ createdDate: this.model.createdDate ? moment(this.model.createdDate) : null })
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.taxInvoiceDetail.list, params, this.taxInvoiceDetailTable)
        }
      },
 
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        //时间格式化
        main.invDate = main.invDate ? main.invDate.format() : null;
        main.createSuccessDate = main.createSuccessDate ? main.createSuccessDate.format() : null;
        main.createRedDate = main.createRedDate ? main.createRedDate.format() : null;
        main.createCancelDate = main.createCancelDate ? main.createCancelDate.format() : null;
        main.createdDate = main.createdDate ? main.createdDate.format() : null;
        return {
          ...main, // 展开
          taxInvoiceDetailList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
  input{
    color: #000c17;
  }
</style>