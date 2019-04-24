<template>
  <a-card :bordered="false">

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus" v-has="'customerAuthor:add'" >新增</a-button>
      <!-- <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
       <a-upload name="file" :showUploadList="false" :multiple="false" :action="importExcelUrl" @change="handleImportExcel">
         <a-button type="primary" icon="import">导入</a-button>
       </a-upload>-->
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete" v-has="'customerAuthor:delete'"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="small"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">
        <template slot="authorStatus"  slot-scope="textVal">
          <span  :style="{color:(textVal=='1'?'limegreen':'red')}">{{textVal=='1'?'有效':'无效'}}</span>
        </template>
        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)" v-has="'customerAuthor:edit'" >编辑</a>
          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a href="javascript:;" @click="handleDetail(record)" v-has="'customerAuthor:detail'" >详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)" v-has="'customerAuthor:delete'" >
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <tax-customer-author-modal ref="modalForm" @ok="modalFormOk"></tax-customer-author-modal>
  </a-card>
</template>

<script>
  import TaxCustomerAuthorModal from './modules/TaxCustomerAuthorModal'
  import TaxCustomerList from './TaxCustomerList'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import {getAction} from '@/api/manage'

  export default {
    name: "TaxCustomerAuthorList",
    mixins:[JeecgListMixin],
    components: {
      TaxCustomerAuthorModal,
      TaxCustomerList
    },
    data () {
      return {
        description: '授权信息',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
         /* {
            title: '企业纳税人识别号',
            align:"center",
            dataIndex: 'custTaxCode'
          },*/
          {
            title: '盘号',
            align:"center",
            dataIndex: 'checkCode'
          },
          {
            title: '开票机号',
            align:"center",
            dataIndex: 'machineNo'
          },
          {
            title: '授权状态',
            align:"center",
            dataIndex: 'authorStatus',
            scopedSlots: { customRender: 'authorStatus' }

          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        custTaxCode: "",
        url: {
          list: "/customer/taxCustomer/queryTaxCustomerAuthorByMainId",
          delete: "/customer/taxCustomerAuthor/delete",
          deleteBatch: "/customer/taxCustomerAuthor/deleteBatch",
          exportXlsUrl: "customer/taxCustomerAuthor/exportXls",
          importExcelUrl: "customer/taxCustomerAuthor/importExcel",
        },
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      loadData(arg) {
        if (arg === 1) {
          this.ipagination.current = 1;
        }
        var params = this.getQueryParams();
        getAction(this.url.list, {mainId: params.mainId}).then((res) => {
          if (res.success) {
            this.dataSource = res.result;
          } else {
            this.dataSource = null;
          }
        })
      },
      getCustomerMain(customerId,custTaxCode) {
        this.queryParam.mainId = customerId;
        this.custTaxCode = custTaxCode;
        this.loadData(1);
      },
      handleAdd: function () {
        this.$refs.modalForm.add(this.queryParam.mainId,this.custTaxCode);
        this.$refs.modalForm.title = "添加盘号信息";
      }
    }
  }
</script>
<style lang="less" scoped>
  /** Button按钮间距 */
  .ant-btn {
    margin-left: 3px
  }
  .ant-card-body .table-operator{
    margin-bottom: 18px;
  }
  .ant-table-tbody .ant-table-row td{
    padding-top:15px;
    padding-bottom:15px;
  }
  .anty-row-operator button{margin: 0 5px}
  .ant-btn-danger{background-color: #ffffff}

  .ant-modal-cust-warp{height: 100%}
  .ant-modal-cust-warp .ant-modal-body{height:calc(100% - 110px) !important;overflow-y: auto}
  .ant-modal-cust-warp .ant-modal-content{height:90% !important;overflow-y: hidden}
</style>