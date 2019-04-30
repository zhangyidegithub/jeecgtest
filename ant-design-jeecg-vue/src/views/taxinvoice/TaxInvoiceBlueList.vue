<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="信息表编号">
              <a-input placeholder="请输入信息表编号" v-model="queryParam.ddbh"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="购方名称">
              <a-input placeholder="请输入购方名称" v-model="queryParam.clientTaxName"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6" >
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
      <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
    </div>
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
          <a @click="handleDetail(record)">查看</a>
        </span>
      </a-table>

    <!-- table区域-end -->

    <!-- 表单区域 -->
    <tax-invoice-blue-modal ref="modalForm" @ok="modalFormOk"/>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import TaxInvoiceBlueModal from './modules/TaxInvoiceBlueModal'

  export default {
    name: "TaxInvoiceBlueList",
    mixins: [JeecgListMixin],
    components: {
      TaxInvoiceBlueModal
    },
    data () {
      return {
        description: '蓝票信息页面',
        // 表头
        columns: [
          {
            title: '信息表编号',
            align:"center",
            dataIndex: 'ddbh'
          },
          {
            title: '开票日期',
            align:"center",
            dataIndex: 'invDate'
          },
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
          {
            title: '购方名称',
            align:"center",
            dataIndex: 'clientTaxName'
          },
          {
            title: '销货名称',
            align:"center",
            dataIndex: 'sellerTaxName'
          },
          {
            title: '经办人',
            align:"center",
            dataIndex: 'invoicer'
          },
          {
            title: '价税合计金额',
            align:"center",
            dataIndex: 'amount'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        // 请求参数
        url: {
          list: "/com/aisino/taxInvoice/listBlue",
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