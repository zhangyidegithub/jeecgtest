<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">
          <a-col :span="6">
            <a-form-item label="纳税人识别号">
              <a-input placeholder="企业纳税人识别号" v-model="queryParam.custTaxCode"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="6">
            <a-form-item label="纳税人名称">
              <a-input placeholder="企业纳税人名称" v-model="queryParam.custTaxName"></a-input>
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
      <a-button @click="handleAdd" type="primary" icon="plus" v-has="'customer:add'">新增</a-button>
<!--
      <a-button type="primary" icon="download" @click="handleExportXls">导出</a-button>
-->
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i>
        <span>已选择</span>
        <a style="font-weight: 600">
          {{ selectedRowKeys.length }}
        </a>
        <span>项</span>
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="customerId"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange,type:type}"
        @change="handleTableChange"
        :customRow="clickThenCheck"
      >

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)" v-has="'customer:edit'">编辑</a>
           <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
               <a-menu-item>
                <a href="javascript:;" @click="handleDetail(record)" v-has="'customer:detail'">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.customerId)" >
                  <a v-has="'customer:delete'">删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->
    <a-tabs defaultActiveKey="1" @change="tabsChange">
      <a-tab-pane tab="盘号信息" key="1">
        <Tax-Customer-Author-List ref="taxCustomerAuthorList"></Tax-Customer-Author-List>
      </a-tab-pane>
      <a-tab-pane tab="授权信息" key="2" forceRender>
        <Tax-Customer-Author-Info-List ref="taxCustomerAuthorInfoList"></Tax-Customer-Author-Info-List>
      </a-tab-pane>
    </a-tabs>
    <!-- 表单区域 -->
    <tax-customer-modal ref="modalForm" @ok="modalFormOk"></tax-customer-modal>

  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import TaxCustomerModal from './modules/TaxCustomerModal'
  import TaxCustomerAuthorModal from './modules/TaxCustomerAuthorModal'
  import TaxCustomerAuthorList from './TaxCustomerAuthorList'
  import TaxCustomerAuthorInfoModal from './modules/TaxCustomerAuthorInfoModal'
  import TaxCustomerAuthorInfoList from './TaxCustomerAuthorInfoList'
  import {deleteAction} from '@/api/manage'
  export default {
    name: "TaxCustomerList",
    mixins: [JeecgListMixin],
    components: {
      TaxCustomerModal,
      TaxCustomerAuthorModal,
      TaxCustomerAuthorList,
      TaxCustomerAuthorInfoModal,
      TaxCustomerAuthorInfoList
    },
    data () {
      return {
        description: '企业信息管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key: 'rowIndex',
            width: 60,
            align: "center",
            customRender:function (t, r, index) {
              return parseInt(index)+1;
            }
          },
          {
            title: '企业纳税人识别号',
            align:"center",
            dataIndex: 'custTaxCode'
          },
          {
            title: '企业纳税人名称',
            align:"center",
            dataIndex: 'custTaxName'
          },
          {
            title: '创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title: '创建时间',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title: '联系人',
            align:"center",
            dataIndex: 'linkMan'
          },
          {
            title: '联系人手机号',
            align:"center",
            dataIndex: 'linkPhone'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
        // 请求参数
        type: "radio",
        tabsActiveKey:"1",
        selectedRowKeys:[],
        selectionRows:[],
        url: {
          list: "/customer/taxCustomer/list",
          delete: "/customer/taxCustomer/delete",
          deleteBatch: "/customer/taxCustomer/deleteBatch",
          exportXlsUrl: "customer/taxCustomer/exportXls",
          importExcelUrl: "customer/taxCustomer/importExcel",
        },
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      tabsChange(activeKey){
        var selectCustTaxCode = "";
        var selectRowKey= "";
        if(this.selectionRows.length>0){
          selectCustTaxCode = this.selectionRows[0].custTaxCode;
        }if(this.selectedRowKeys.length>0){
          selectRowKey = this.selectedRowKeys[0];
        }
         if(activeKey == 2){
           this.tabsActiveKey = "2";
           this.$refs.taxCustomerAuthorInfoList.getCustomerMain(selectRowKey,selectCustTaxCode);
         }else if(activeKey == "1"){
           this.tabsActiveKey = "1";
           this.$refs.taxCustomerAuthorList.getCustomerMain(selectRowKey,selectCustTaxCode);
         }
      },
      clickThenCheck(record) {
        return {
          on: {
            click: () => {
              this.onSelectChange(record.customerId.split(","), [record]);
            }
          }
        };
      },
      onSelectChange(selectedRowKeys, selectionRows) {
        this.selectedRowKeys = selectedRowKeys;
        this.selectionRows = selectionRows;
        if( this.tabsActiveKey == "1"){
          this.$refs.taxCustomerAuthorList.getCustomerMain(this.selectedRowKeys[0],this.selectionRows[0].custTaxCode);
        }else if(this.tabsActiveKey == "2"){
          this.$refs.taxCustomerAuthorInfoList.getCustomerMain(this.selectedRowKeys[0],this.selectionRows[0].custTaxCode);
        }
      },
      onClearSelected() {
        this.selectedRowKeys = [];
        this.selectionRows = [];
        if( this.tabsActiveKey == "1"){
          this.$refs.taxCustomerAuthorList.queryParam.mainId = null;
          this.$refs.taxCustomerAuthorList.loadData();
          this.$refs.taxCustomerAuthorList.selectedRowKeys = [];
          this.$refs.taxCustomerAuthorList.selectionRows = [];
        }else if(this.tabsActiveKey == "2"){
          this.$refs.taxCustomerAuthorInfoList.queryParam.mainId = null;
          this.$refs.taxCustomerAuthorInfoList.loadData();
          this.$refs.taxCustomerAuthorInfoList.selectedRowKeys = [];
          this.$refs.taxCustomerAuthorInfoList.selectionRows = [];
        }
      },
      handleDelete: function (id) {
        var that = this;
        deleteAction(that.url.delete, {id: id}).then((res) => {
          if (res.success) {
            that.$message.success(res.message);
            that.loadData();
            this.$refs.taxCustomerAuthorList.loadData();
            this.$refs.taxCustomerAuthorInfoList.loadData();
          } else {
            that.$message.warning(res.message);
          }
        });
      },
      searchQuery:function(){
        this.selectedRowKeys = [];
        this.selectionRows = [];
        if( this.tabsActiveKey == "1"){
          this.$refs.taxCustomerAuthorList.queryParam.mainId = null;
          // this.$refs.taxCustomerAuthorList.loadData();
          this.$refs.taxCustomerAuthorList.selectedRowKeys = [];
          this.$refs.taxCustomerAuthorList.selectionRows = [];
        }else if(this.tabsActiveKey == "2"){
          this.$refs.taxCustomerAuthorInfoList.queryParam.mainId = null;
          // this.$refs.taxCustomerAuthorInfoList.loadData();
          this.$refs.taxCustomerAuthorInfoList.selectedRowKeys = [];
          this.$refs.taxCustomerAuthorInfoList.selectionRows = [];
        }
        this.loadData();
      },
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