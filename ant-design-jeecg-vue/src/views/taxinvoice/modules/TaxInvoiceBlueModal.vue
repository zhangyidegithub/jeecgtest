<template>
  <a-drawer
    :title="title"
    :width="950"
    placement="right"
    :visible="visible"
    :maskClosable="true"
    :closable="true"
    @ok="handleCancel"
    @close="handleCancel">
    <a-spin :spinning="confirmLoading">
      <table width="860" height="89" border="0" align="center" cellpadding="0" cellspacing="0" class="invoiceTitle">
        <tbody>
        <tr>
          <td width="682">&nbsp;</td>
          <td width="178" rowspan="4">
            <table>
              <tr>
                <td>
                  <label class="Validform_label">发票代码:{{this.model.infoTypeCode}}</label>
                </td>
              </tr>
              <tr>
                <td>
                  <label class="Validform_label">发票号码:{{this.model.infoNumber}}</label>
                </td>
              </tr>
              <tr>
                <td>
                  <label class="Validform_label">开票日期:{{this.model.invDate}}</label>
                </td>
              </tr>
              <tr>
                <td>
                  <label class="Validform_label">&nbsp;&nbsp;校验码:</label>
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td align="center" border="0" style=" padding-left:66px; ">
            <table height="42" border="0" cellpadding="0" cellspacing="0" class="invoiceTitle_text">
              <tbody>
              <tr>
                <td style="font-size:28px; border-bottom:3px double  #cc301b; color:  #cc301b;">陕西省增值税电子普通发票</td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
        </tr>
        <tr>
          <td> 机器编号:</td>
        </tr>
        </tbody>
      </table>
      <table width="860" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor=" #cc301b">
        <tbody>
        <tr>
          <td align="center" valign="middle">
            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style=" border-collapse:collapse;">
              <tbody>
              <tr>
                <td width="40" align="center" valign="middle" style="border-right:1px  #cc301b solid; border-collapse:collapse;">购<br>
                  买<br>
                  方
                </td>
                <td width="600" border="0">
                  <label class="Validform_label">&nbsp;&nbsp;购货方名称:{{this.model.clientTaxName}}</label>
                  <br>
                  <label class="Validform_label">纳税人识别号:{{this.model.clientTaxCode}}</label>
                  <br>
                  <label class="Validform_label">&nbsp;地址&nbsp;、电话:{{this.model.clientAddressPhone}}</label>
                  <br>
                  <label class="Validform_label">开户行及账号:{{this.model.clientBankAccount}}</label>
                </td>
                <td width="40" align="center" valign="middle" style="border-right:1px  #cc301b solid;">&nbsp;</td>
                <td width="40" align="center" valign="middle" style="border-right:1px  #cc301b solid;">
                  密<br>
                  码<br>
                  区
                </td>
                <td width="200">

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
                <td align="center" style="border-right:1px  #cc301b solid; width: 220px" bgcolor="#EEEEEE">货物或应税劳务、服务名称</td>
                <td align="center" style="border-right:1px  #cc301b solid; width: 70px" bgcolor="#EEEEEE">规格型号</td>
                <td align="center" style="border-right:1px  #cc301b solid;width: 30px" bgcolor="#EEEEEE">单位</td>
                <td align="center" style="border-right:1px  #cc301b solid;width: 30px" bgcolor="#EEEEEE">数量</td>
                <td align="center" style="border-right:1px  #cc301b solid;width: 70px" bgcolor="#EEEEEE">单价</td>
                <td align="center" style="border-right:1px  #cc301b solid;width: 68px" bgcolor="#EEEEEE">金额</td>
                <td align="center" style="border-right:1px  #cc301b solid;width: 20px" bgcolor="#EEEEEE">税率</td>
                <td align="center" bgcolor="#EEEEEE" style="width: 70px" border="0">税额</td>
              </tr>
              </tbody>
              <tbody>

              <tr v-for="taxInvoiceGoods in this.taxInvoiceGoodsTable.dataSource">
                <td align="center" style="border-right:1px  #cc301b solid;">
                  <label class="Validform_label">{{taxInvoiceGoods.goodsName}}</label>
                </td>
                <td align="center" style="border-right:1px  #cc301b solid;">
                  <label class="Validform_label">{{taxInvoiceGoods.goodsXh}}</label>
                </td>
                <td align="center" style="border-right:1px  #cc301b solid;">
                  <label class="Validform_label">{{taxInvoiceGoods.goodsUnit}}</label>
                </td>
                <td align="center" style="border-right:1px  #cc301b solid;">
                  <label class="Validform_label">{{taxInvoiceGoods.goodsNumber}}</label>
                </td>
                <td align="center" style="border-right:1px  #cc301b solid;">
                  <label class="Validform_label">{{taxInvoiceGoods.goodsPrice}}</label>
                </td>
                <td align="center" style="border-right:1px  #cc301b solid;">
                  <label class="Validform_label">{{taxInvoiceGoods.goodsJe}}</label>
                </td>
                <td align="center" style="border-right:1px  #cc301b solid;">
                  <label class="Validform_label">{{taxInvoiceGoods.goodsTaxRate}}</label>
                </td>
                <td align="center" style="border-right:0px  #cc301b solid;">
                  <label class="Validform_label">{{taxInvoiceGoods.goodsSe}}</label>
                </td>
              </tr>
              </tbody>
              <tbody>
              <tr v-for="blandk in this.taxInvoiceGoodsTable.fillBlank">
                <td align="center" style="border-right:1px #cc301b solid;"></td>
                <td align="center" style="border-right:1px  #cc301b solid;"><span v-html="blandk.fill"></span></td>
                <td align="center" style="border-right:1px  #cc301b solid;"><span v-html="blandk.fill"></span></td>
                <td align="center" style="border-right:1px  #cc301b solid;"><span v-html="blandk.fill"></span></td>
                <td align="center" style="border-right:1px  #cc301b solid;"><span v-html="blandk.fill"></span></td>
                <td align="center" style="border-right:1px  #cc301b solid;"><span v-html="blandk.fill"></span></td>
                <td align="center" style="border-right:1px  #cc301b solid;"><span v-html="blandk.fill"></span></td>
                <td align="center" border="0"><span v-html="blandk.fill"></span></td>
              </tr>
              </tbody>
              <tbody>
              <td align="center" style="border-right:1px  #cc301b solid;">合计:</td>
              <td align="center" style="border-right:1px  #cc301b solid;">&nbsp;</td>
              <td align="center" style="border-right:1px  #cc301b solid;">&nbsp;</td>
              <td align="center" style="border-right:1px  #cc301b solid;">&nbsp;</td>
              <td align="center" style="border-right:1px  #cc301b solid;">&nbsp;</td>
              <td align="center" style="border-right:1px  #cc301b solid;">{{this.model.infoAmount}}</td>
              <td align="center" style="border-right:1px  #cc301b solid;">&nbsp;</td>
              <td align="center"  border="0">{{this.model.tax}}</td>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td>
            &nbsp;&nbsp;<label class="Validform_label" style="align:right">价税合计(小写):￥{{this.model.amount}}</label>
          </td>
        </tr>
        <tr>
          <td valign="top">
            <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
              <tbody>
              <tr>
                <td width="40" align="center" valign="middle" style="border-right:1px  #cc301b solid;">
                  销<br>
                  售<br>
                  方
                </td>
                <td width="600">
                  <label class="Validform_label">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:{{this.model.clientTaxName}}</label>
                  <br>
                  <label class="Validform_label">纳税人识别号:{{this.model.clientTaxCode}}</label>
                  <br>
                  <label class="Validform_label">地址&nbsp;、&nbsp;电话:{{this.model.clientAddressPhone}}</label>
                  <br>
                  <label class="Validform_label">开户行及账号:{{this.model.clientBankAccount}}</label>
                </td>
                <td width="40" align="center" valign="middle" style="border-right:1px  #cc301b solid;">&nbsp;</td>
                <td width="40" align="center" style="border-right:1px  #cc301b solid;">
                  备<br>
                  注
                </td>
                <td width="200" align="center">
                  <label class="Validform_label">{{this.model.bz}}</label>
                </td>
              </tr>
              </tbody>
            </table>
          </td>
        </tr>
        </tbody>
      </table>
      <table width="860" height="42" border="0" align="center" cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
          <td width="253"><label class="Validform_label">&nbsp;&nbsp;收款人:{{this.model.cashier}}</label></td>
          <td width="246"><label class="Validform_label">复核人:{{this.model.checker}}</label></td>
          <td width="182"><label class="Validform_label">开票人:{{this.model.invoicer}}</label></td>
          <td width="179">销售方：（章）</td>
        </tr>
        </tbody>
      </table>
    </a-spin>
  </a-drawer>
</template>

<script>

  import moment from 'moment'
  import pick from 'lodash.pick'
  import { FormTypes } from '@/utils/JEditableTableUtil'
  import { JEditableTableOneToManyMixin } from '@/mixins/JEditableTableOneToManyMixin'

  export default {
    name: 'TaxInvoiceBlueModal',
    mixins: [JEditableTableOneToManyMixin],
    data() {
      return {
        validatorRules: {

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
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
          this.requestSubTableData(this.url.taxInvoiceGoods.list, params, this.taxInvoiceGoodsTable)
        }
      },
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
  .invoiceTitle{

  }
</style>