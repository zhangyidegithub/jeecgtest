<template>
  <div>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
<!--          <a-col :span="6">-->
<!--            <a-form-item label="发票流水号">-->
<!--              <a-input placeholder="请输入发票流水号" v-model="queryParam.fpqqlsh"></a-input>-->
<!--            </a-form-item>-->
<!--          </a-col>-->
          <a-col :span="6">
            <a-form-item label="订单编号">
              <a-input placeholder="请输入订单编号" v-model="queryParam.ddbh"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
<!--      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>-->
      <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :action="importExcelUrl" @change="handleImportExcel">
<!--        <a-button type="primary" icon="import">导入</a-button>-->
      </a-upload>
<!--      <a-dropdown v-if="selectedRowKeys.length > 0">-->
<!--        <a-menu slot="overlay">-->
<!--          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>-->
<!--        </a-menu>-->
<!--        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>-->
<!--      </a-dropdown>-->
    </div>

    <!-- table区域-begin -->
<!--    <div>-->
<!--      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">-->
<!--        <i class="anticon anticon-info-circle ant-alert-icon"></i>-->
<!--        <span>已选择</span>-->
<!--        <a style="font-weight: 600">-->
<!--          {{ selectedRowKeys.length }}-->
<!--        </a>-->
<!--        <span>项</span>-->
<!--        <a style="margin-left: 24px" @click="onClearSelected">清空</a>-->
<!--      </div>-->
      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">查看</a>


<!--          <a-divider type="vertical" />-->
<!--          <a-dropdown>-->
<!--            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>-->
<!--            <a-menu slot="overlay">-->
<!--              <a-menu-item>-->
<!--                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">-->
<!--                  <a>删除</a>-->
<!--                </a-popconfirm>-->
<!--              </a-menu-item>-->
<!--            </a-menu>-->
<!--          </a-dropdown>-->
        </span>

      </a-table>

    <!-- table区域-end -->

    <!-- 表单区域 -->
    <taxInvoice-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
  </div>
</template>

<script>
  import {putAction} from '@/api/manage';
  import {frozenBatch} from '@/api/api'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import TaxInvoiceModal from './modules/TaxInvoiceModal'

  export default {
    name: "TaxInvoiceList",
    mixins: [JeecgListMixin],
    components: {
      TaxInvoiceModal
    },
    data () {
      return {
        description: '客户税号管理页面',
        // 表头
        columns: [
          {
            title: '序号',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender:function (t, r, index) {
              return parseInt(index)+1;
            }
          },
          // {
          //   title: '发票流水号',
          //   align:"center",
          //   dataIndex: 'fpqqlsh'
          // },
          {
            title: '订单编号',
            align:"center",
            dataIndex: 'ddbh'
          },
          // {
          //   title: '备注发票红票流水号',
          //   align:"center",
          //   dataIndex: 'fpqqlshRed'
          // },
          // {
          //   title: '销方识别号',
          //   align:"center",
          //   dataIndex: 'sellerTaxCode'
          // },
          // {
          //   title: '销方名称',
          //   align:"center",
          //   dataIndex: 'sellerTaxName'
          // },
          // {
          //   title: '销方地址电话',
          //   align:"center",
          //   dataIndex: 'sellerAddressPhone'
          // },
          // {
          //   title: '销方银行账号',
          //   align:"center",
          //   dataIndex: 'sellerBankAccount'
          // },
          {
            title: '购方名称',
            align:"center",
            dataIndex: 'clientTaxName'
          },
          // {
          //   title: '购方识别号',
          //   align:"center",
          //   dataIndex: 'clientTaxCode'
          // },
          // {
          //   title: '购方地址电话',
          //   align:"center",
          //   dataIndex: 'clientAddressPhone'
          // },
          // {
          //   title: '购方银行账号',
          //   align:"center",
          //   dataIndex: 'clientBankAccount'
          // },
          {
            title: '开票员',
            align:"center",
            dataIndex: 'invoicer'
          },
          // {
          //   title: '复核人',
          //   align:"center",
          //   dataIndex: 'checker'
          // },
          // {
          //   title: '收款员',
          //   align:"center",
          //   dataIndex: 'cashier'
          // },
          // {
          //   title: '开票类型',
          //   align:"center",
          //   dataIndex: 'infoKind'
          // },
          {
            title: '发票代码',
            align:"center",
            dataIndex: 'infoTypeCode'
          },
          {
            title: '发票号码',
            align:"center",
            dataIndex: 'infoNumber'
          },
          // {
          //   title: '清单标志',
          //   align:"center",
          //   dataIndex: 'listFlag'
          // },
          // {
          //   title: '清单发票项目名称',
          //   align:"center",
          //   dataIndex: 'qdxmmc'
          // },
          // {
          //   title: '冲红原因',
          //   align:"center",
          //   dataIndex: 'chyy'
          // },
          {
            title: '价税合计金额',
            align:"center",
            dataIndex: 'amount'
          },
          // {
          //   title: '合计不含税金额',
          //   align:"center",
          //   dataIndex: 'infoAmount'
          // },
          // {
          //   title: '合计税额',
          //   align:"center",
          //   dataIndex: 'tax'
          // },
          // {
          //   title: '信息表类型',
          //   align:"center",
          //   dataIndex: 'billType'
          // },
          // {
          //   title: '多税率标志',
          //   align:"center",
          //   dataIndex: 'ismutiRate'
          // },
          // {
          //   title: '税率，多税率时为空',
          //   align:"center",
          //   dataIndex: 'taxRate'
          // },
          // {
          //   title: '申请说明',
          //   align:"center",
          //   dataIndex: 'reqMemo'
          // },
          // {
          //   title: '商品编码版本号',
          //   align:"center",
          //   dataIndex: 'spbmbbh'
          // },
          // {
          //   title: '含税税率标志',
          //   align:"center",
          //   dataIndex: 'slbz'
          // },
          {
            title: '备注',
            align:"center",
            dataIndex: 'bz'
          },
          // {
          //   title: '收购标志',
          //   align:"center",
          //   dataIndex: 'sgbz'
          // },
          // {
          //   title: '代开标志',
          //   align:"center",
          //   dataIndex: 'dkbz'
          // },
          // {
          //   title: '折扣标志',
          //   align:"center",
          //   dataIndex: 'zkbz'
          // },
          // {
          //   title: '是否申请开票',
          //   align:"center",
          //   dataIndex: 'isApply'
          // },
          // {
          //   title: '是否红字单',
          //   align:"center",
          //   dataIndex: 'isRed'
          // },
          // {
          //   title: '已经开过发票',
          //   align:"center",
          //   dataIndex: 'isInvoice'
          // },
          // {
          //   title: '是否申请作废',
          //   align:"center",
          //   dataIndex: 'isCancel'
          // },
          // {
          //   title: '打印标志',
          //   align:"center",
          //   dataIndex: 'printFlag'
          // },
          // {
          //   title: '发票报送状态',
          //   align:"center",
          //   dataIndex: 'uploadFlag'
          // },
          // {
          //   title: '作废标志',
          //   align:"center",
          //   dataIndex: 'cancelFlag'
          // },
          // {
          //   title: '返回代码',
          //   align:"center",
          //   dataIndex: 'retCode'
          // },
          {
            title: '开票日期',
            align:"center",
            dataIndex: 'invDate'
          },
          // {
          //   title: '开票成功日期',
          //   align:"center",
          //   dataIndex: 'createSuccessDate'
          // },
          // {
          //   title: '冲红成功日期',
          //   align:"center",
          //   dataIndex: 'createRedDate'
          // },
          // {
          //   title: '作废成功日期',
          //   align:"center",
          //   dataIndex: 'createCancelDate'
          // },
          // {
          //   title: '写入时间',
          //   align:"center",
          //   dataIndex: 'createdDate'
          // },
          // {
          //   title: '数据状态',
          //   align:"center",
          //   dataIndex: 'status'
          // },
          // {
          //   title: '发票类型',
          //   align:"center",
          //   dataIndex: 'invoiceType'
          // },
          {
            title: '描述信息',
            align:"center",
            dataIndex: 'retMsg'
          },
          // {
          //   title: '企业纳税人识别号',
          //   align:"center",
          //   dataIndex: 'custTaxCode'
          // },
          // {
          //   title: '销货清单标志',
          //   align:"center",
          //   dataIndex: 'goodsListFlag'
          // },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: { // 请求参数
          list: "/com/aisino/taxInvoice/list",
          delete: "/com/aisino/taxInvoice/delete",
          deleteBatch: "/com/aisino/taxInvoice/deleteBatch",
          exportXlsUrl: "com/aisino/taxInvoice/exportXls",
          importExcelUrl: "com/aisino/taxInvoice/importExcel",
        },


        }
      },
      computed: {
        importExcelUrl: function(){
          return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
        }
      },


    methods: {

      initDictConfig() {
      }

    }
  }
</script>
<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 3px
  }
  .ant-card-body .table-operator {
    margin-bottom: 18px;
  }

  .ant-table-tbody .ant-table-row td {
    padding-top: 15px;
    padding-bottom: 15px;
  }

  .anty-row-operator button {
    margin: 0 5px
  }

  .ant-btn-danger {
    background-color: #ffffff
  }

  .ant-modal-cust-warp {
    height: 100%
  }

  .ant-modal-cust-warp .ant-modal-body {
    height: calc(100% - 110px) !important;
    overflow-y: auto
  }

  .ant-modal-cust-warp .ant-modal-content {
    height: 90% !important;
    overflow-y: hidden
  }
</style>