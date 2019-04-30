<template>
  <a-drawer
    :title="title"
    :width="950"
    placement="right"
    :visible="visible"
    :maskClosable="false"
    :closable="true"
    @ok="handleCancel"
    @close="handleCancel">
    <a-spin :spinning="confirmLoading">
      <table width="860" height="89" border="0" align="center" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td width="682">&nbsp;</td>
            <td width="178" rowspan="4">
            </td>
          </tr>
          <tr>
            <td align="center" style=" padding-left:66px; ">
              <table height="42" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                  <tr>
                    <td style="font-size:28px; border-bottom:3px double #A52A2A; color: #A52A2A;">开具红字增值税专用发票信息表</td>
                  </tr>
                </tbody>
              </table>
            </td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </tbody>
      </table>
      <table width="860" border="0" align="center" cellpadding="0" cellspacing="0">
        <tbody>
          <tr>
            <td width="286" >填开日期:&nbsp;{{this.model.invDate}}</td>
            <td width="286" >申请方经办人:&nbsp;{{this.model.invoicer}}</td>
            <td width="288" >No:&nbsp;{{this.model.fpqqlsh}}</td>
          </tr>
        </tbody>
      </table>
      <table width="860" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor="#A52A2A">
        <tbody>
          <tr>
            <td align="center" valign="middle"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
              <tbody>
              <tr>
                <td width="40" align="center" valign="middle" style="border-right:2px #A52A2A solid;">
                  销<br>
                  售<br>
                  方
                </td>
                <td width="350">
                  <label class="Validform_label">&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;称&nbsp;&nbsp;:{{this.model.sellerTaxName}}</label>
                  <br>&nbsp;&nbsp;
                  <br>
                  <label class="Validform_label">纳税人识别号:{{this.model.sellerTaxCode}}</label>
                  <br>&nbsp;&nbsp;
                </td>
                <td width="1" align="center" valign="middle" style="border-right:2px #A52A2A solid;">&nbsp;</td>
                <td width="40" align="center" valign="middle" style="border-right:2px #A52A2A solid;">
                  购<br>
                  买<br>
                  方
                </td>
                <td width="350">
                  <label class="Validform_label">&nbsp;&nbsp;名&nbsp;&nbsp;&nbsp;&nbsp;称&nbsp;&nbsp;:{{this.model.clientTaxName}}</label>
                  <br>&nbsp;&nbsp;
                  <br>
                  <label class="Validform_label">纳税人识别号:{{this.model.clientTaxCode}}</label>
                  <br>&nbsp;&nbsp;
                </td>
              </tr>
              </tbody>
            </table>
            </td>
          </tr>
        <tr>
          <td valign="top"><!--{{this.taxInvoiceGoodsTable.dataSource}}-->
            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
              <tbody>
                <tr>
                  <td align="center" style="border-right:2px #A52A2A solid; width: 200px" bgcolor="#EEEEEE">货物(劳务、服务)名称</td>
                  <td align="center" style="border-right:2px #A52A2A solid; width: 50px" bgcolor="#EEEEEE">规格型号</td>
                  <td align="center" style="border-right:2px #A52A2A solid;width: 30px" bgcolor="#EEEEEE">单位</td>
                  <td align="center" style="border-right:2px #A52A2A solid;width: 30px" bgcolor="#EEEEEE">数量</td>
                  <td align="center" style="border-right:2px #A52A2A solid;width: 100px" bgcolor="#EEEEEE">单价(不含税)</td>
                  <td align="center" style="border-right:2px #A52A2A solid;width: 100px" bgcolor="#EEEEEE">金额(不含税)</td>
                  <td align="center" style="border-right:2px #A52A2A solid;width: 20px" bgcolor="#EEEEEE">税率</td>
                  <td align="center" bgcolor="#EEEEEE" style="width: 70px">税额</td>
                </tr>
              </tbody>
            <tbody>

            <tr v-for="taxInvoiceGoods in this.taxInvoiceGoodsTable.dataSource">
              <td align="center" style="border-right:2px #A52A2A solid;">
                <label class="Validform_label">{{taxInvoiceGoods.goodsName}}</label>
              </td>
              <td align="center" style="border-right:2px #A52A2A solid;">
                <label class="Validform_label">{{taxInvoiceGoods.goodsXh}}</label>
              </td>
              <td align="center" style="border-right:2px #A52A2A solid;">
                <label class="Validform_label">{{taxInvoiceGoods.goodsUnit}}</label>
              </td>
              <td align="center" style="border-right:2px #A52A2A solid;">
                <label class="Validform_label">{{taxInvoiceGoods.goodsNumber}}</label>
              </td>
              <td align="center" style="border-right:2px #A52A2A solid;">
                <label class="Validform_label">{{taxInvoiceGoods.goodsPrice}}</label>
              </td>
              <td align="center" style="border-right:2px #A52A2A solid;">
                <label class="Validform_label">{{taxInvoiceGoods.goodsJe}}</label>
              </td>
              <td align="center" style="border-right:2px #A52A2A solid;">
                <label class="Validform_label">{{taxInvoiceGoods.goodsTaxRate}}</label>
              </td>
              <td align="center" style="border-right:2px #A52A2A solid;">
                <label class="Validform_label">{{taxInvoiceGoods.goodsSe}}</label>
              </td>
            </tr>
            </tbody>
              <tbody>
                <tr v-for="blandk in this.taxInvoiceGoodsTable.fillBlank">
                  <td align="center" style="border-right:2px #A52A2A solid;"></td>
                  <td align="center" style="border-right:2px #A52A2A solid;"><span v-html="blandk.fill"></span></td>
                  <td align="center" style="border-right:2px #A52A2A solid;"><span v-html="blandk.fill"></span></td>
                  <td align="center" style="border-right:2px #A52A2A solid;"><span v-html="blandk.fill"></span></td>
                  <td align="center" style="border-right:2px #A52A2A solid;"><span v-html="blandk.fill"></span></td>
                  <td align="center" style="border-right:2px #A52A2A solid;"><span v-html="blandk.fill"></span></td>
                  <td align="center" style="border-right:2px #A52A2A solid;"><span v-html="blandk.fill"></span></td>
                  <td align="center" style="border-right:2px #A52A2A solid;"><span v-html="blandk.fill"></span></td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            <table>
              <tbody>
                <tr>
                  <td width="33%" align="center">
                    <label class="Validform_label" style="align:right">合计</label>
                  </td>
                  <td width="1" align="center" valign="middle" style="border-right:0px #A52A2A solid;">&nbsp;</td>
                  <td width="33%" align="center">
                    <label class="Validform_label" style="align:right">金额:{{this.model.amount}}</label>
                  </td>
                  <td width="1" align="center" valign="middle" style="border-right:0px #A52A2A solid;">&nbsp;</td>
                  <td width="33%" align="center">
                    <label class="Validform_label" style="align:right">税额:{{this.model.tax}}</label>
                  </td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
              <tbody>
                <tr>
                  <td width="40" align="center" valign="middle" style="border-right:2px #A52A2A solid;">
                    说<br><br>
                    明
                  </td>
                  <td width="350">
                    <label class="Validform_label">{{this.model.bz}}</label>
                  </td>
                  <td width="1" align="center" valign="middle" style="border-right:2px #A52A2A solid;">&nbsp;</td>
                  <td width="40" align="center" style="border-right:2px #A52A2A solid;">
                    发<br>
                    票<br>
                    信<br>
                    息
                  </td>
                  <td width="350" align="left">
                    <label class="Validform_label">&nbsp;&nbsp;发票代码:{{this.model.infoTypeCode}}</label>
                    <br>
                    <label class="Validform_label">&nbsp;&nbsp;发票号码:{{this.model.infoNumber}}</label>
                    <br>
                    <label class="Validform_label">&nbsp;&nbsp;发票种类:增值税专用发票</label>
                  </td>
              </tr>
            </tbody>
          </table>
          </td>
        </tr>
        </tbody>
      </table>
      <!--<table width="860" height="42" border="0" align="center" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
          <td width="253"><label class="Validform_label">&nbsp;&nbsp;收款人:{{this.model.cashier}}</label></td>
          <td width="246"><label class="Validform_label">复核人:{{this.model.checker}}</label></td>
          <td width="182"><label class="Validform_label">开票人:{{this.model.invoicer}}</label></td>
        </tr>
        </tbody></table>-->
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
          clientTaxName: { rules: [{ required: true, message: '请输入购方名称!' }] },
          clientTaxCode: { rules: [{ required: true, message: '请输入购方识别号!' }] },
          amount: { rules: [{ required: true, message: '请输入价税合计金额!' }] },
        },
        refKeys: ['taxInvoiceGoods', ],
        activeKey: 'taxInvoiceGoods',
        // 商品明细
        taxInvoiceGoodsTable: {
          loading: false,
          dataSource: [],
          fillBlank:[
            {fill : "&nbsp;"},
            {fill : "&nbsp;"},
            {fill : "&nbsp;"},
            {fill : "&nbsp;"}
          ],
          columns: [
            {
              title: '商品名称',
              key: 'goodsName',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '商品编码',
              key: 'spbm',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },

            {
              title: '单位',
              key: 'goodsUnit',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '型号',
              key: 'goodsXh',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '单价',
              key: 'goodsPrice',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '数量',
              key: 'goodsNumber',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '税率',
              key: 'goodsTaxRate',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
            {
              title: '税额',
              key: 'goodsSe',
              type: FormTypes.input,
              defaultValue: '',
              placeholder: '请输入${title}',
            },
          ]
        },
        url: {
          add: "/com/aisino/taxInvoice/add",
          edit: "/com/aisino/taxInvoice/edit",
          taxInvoiceGoods: {
            list: '/com/aisino/taxInvoice/queryTaxInvoiceGoodsByMainId'
          },
        }
      }
    },
    methods: {
 
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model, 'ddbh','sellerTaxCode','sellerTaxName','sellerAddressPhone','sellerBankAccount','clientTaxName','clientTaxCode','clientAddressPhone','clientBankAccount','invoicer','checker','cashier','infoTypeCode','infoNumber','chyy','amount','bz','invDate','retMsg', ))
          // 时间格式化
          this.form.setFieldsValue({ invDate: this.model.invDate ? moment(this.model.invDate) : null })
          this.form.setFieldsValue({ createSuccessDate: this.model.createSuccessDate ? moment(this.model.createSuccessDate) : null })
          this.form.setFieldsValue({ createRedDate: this.model.createRedDate ? moment(this.model.createRedDate) : null })
          this.form.setFieldsValue({ createCancelDate: this.model.createCancelDate ? moment(this.model.createCancelDate) : null })
          this.form.setFieldsValue({ createdDate: this.model.createdDate ? moment(this.model.createdDate) : null })
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.taxInvoiceGoods.list, params, this.taxInvoiceGoodsTable)
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
          taxInvoiceGoodsList: allValues.tablesValue[0].values,
        }
      }
    }
  }
</script>

<style scoped>
  body,div,span,dl,dd,h1,h2,h3,h4,h5,h6,p,form,ul,ol,input{
    margin:0 auto;
    padding:0; font-size:16px; font-family:"楷体"; color:#000000; line-height:24px; height:24px;
  }
  textarea{
    resize:none
  }
  .Button{
    display: inline-block;
    outline: none;
    cursor: pointer;
    text-align: center;
    text-decoration: none;
    font: 14px/100% Arial, Helvetica, sans-serif;
    padding: .5em 2em .55em;
    -webkit-border-radius: .5em;
    -moz-border-radius: .5em;
    border-radius: .5em;
    -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
    -moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
    box-shadow: 0 1px 2px rgba(0,0,0,.2);
    color: #fef4e9;
    border: solid 1px #1D73F7;
    background: #1D73F7;
    background: -webkit-gradient(linear, left top, left bottom, from(#1D73F7), to(#1D51F7));
  }
</style>