<template>
  <a-drawer
    :title="title"
    :maskClosable="true"
    :width="drawerWidth"
    placement="right"
    :closable="true"
    @close="handleCancel"
    :visible="visible"
    style="height: calc(100% - 55px);overflow: auto;padding-bottom: 53px;">

    <!-- 编辑 -->
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="纳税人识别号">
          <a-input placeholder="请输入企业纳税人识别号" v-decorator="['custTaxCode', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="盘号">

          <a-select  v-decorator="[ 'checkCode', {}]" placeholder="请选择锁盘号">
            <a-select-option v-for="item in customerAuthors"  :value="item.checkCode">{{item.checkCode}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开始时间">
          <a-date-picker format='YYYY-MM-DD' v-decorator="[ 'authorBeginDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="失效时间">
          <a-date-picker format='YYYY-MM-DD' v-decorator="[ 'authorEndDate', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="企业信息Id"
          v-model="this.customerId"
          :hidden="hiding"
          hasFeedback>
          <a-input v-decorator="[ 'customerId', {}]" disabled="disabled"/>
        </a-form-item>
      </a-form>
    </a-spin>
    <div class="drawer-bootom-button" v-show="!disableSubmit">
      <a-popconfirm title="确定放弃编辑？" @confirm="handleCancel" okText="确定" cancelText="取消">
        <a-button style="margin-right: .8rem">取消</a-button>
      </a-popconfirm>

      <a-button @click="handleOk" type="primary"   :loading="confirmLoading">提交</a-button>
    </div>
  </a-drawer>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import Vue from 'vue'
  import {ACCESS_TOKEN} from "@/store/mutation-types"
  import moment from "moment"
  import {getAction} from '@/api/manage'
  export default {
    name: "TaxCustomerAuthorInfoModal",
    components: {
    },
    data () {
      return {
        modalWidth:800,
        drawerWidth:700,
        modaltoggleFlag:true,
        confirmDirty: false,
        disableSubmit:false,
        title:"操作",
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
// 表头
        columns: [
          {
            title: '企业信息Id',
            align: "center",
            dataIndex: 'customerId',
          },
          {
            title: '纳税人识别号',
            align: "center",
            dataIndex: 'custTaxCode',
          },
          {
            title: '盘号',
            align: "center",
            dataIndex: 'checkCode',
          },
          {
            title: '开始时间',
            align: "center",
            dataIndex: 'authorBeginDate',
          },
          {
            title: '结束时间',
            align: "center",
            dataIndex: 'authorEndDate',
          },
          {
            title: '创建人',
            dataIndex: 'createdUserBy',
            align: "center",
          },
          {
            title: '创建时间',
            dataIndex: 'createdDate',
            align: "center",
          },

        ],
        selectedRowKeys: [],
        customerId: "",
        hiding: false,
        headers: {},
        picUrl: "",
        previewVisible: false,
        addStatus: false,
        editStatus: false,
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        },
        url: {
          add: "/customer/taxCustomerAuthorInfo/add",
          authorList: "/customer/taxCustomer/queryTaxCustomerAuthorByMainId",
          edit: "/customer/taxCustomerAuthorInfo/edit",
          getTaxCustomerAuthorInfoList: "/customer/taxCustomer/queryTaxCustomerAuthorInfoListByMainId",
        },
        selectCustTaxCode: '',
        customerAuthors:[],
      }
    },
    created () {
      const token = Vue.ls.get(ACCESS_TOKEN);
      this.headers = {"X-Access-Token": token}
    },
    methods: {
      toggleScreen(){
        if(this.modaltoggleFlag){
          this.modalWidth = window.innerWidth;
        }else{
          this.modalWidth = 800;
        }
        this.modaltoggleFlag = !this.modaltoggleFlag;
      },
      // 根据屏幕变化,设置抽屉尺寸
      resetScreenSize(){
        let screenWidth = document.body.clientWidth;
        if(screenWidth < 500){
          this.drawerWidth = screenWidth;
        }else{
          this.drawerWidth = 700;
        }
      },
      add (customerId) {
        this.hiding = true;
        if (customerId) {
          this.customerId = customerId;
          this.edit({customerId}, '');
        } else {
          this.$message.warning("请选择一个企业信息");
        }
      },
      detail(record) {
        this.edit(record, 'd');
      },
      edit(record, v) {
        if (v == 'e') {
          this.hiding = false;
          this.disableSubmit = false;
        } else if (v == 'd') {
          this.hiding = false;
          this.disableSubmit = false;
        } else {
          this.hiding = true;
          this.disableSubmit = false;
        }
        this.form.resetFields();
        this.customerId = record.customerId;
        getAction(this.url.authorList, {mainId: this.customerId}).then((res) => {
          if (res.success) {
            console.info(res.result)
            this.customerAuthors = res.result;
          } else {
            this.customerAuthors = [];
          }
        })
        if (record.id) {
          this.model = Object.assign({}, record);
          this.hiding = false;
          this.addStatus = false;
          this.editStatus = true;
          this.$nextTick(() => {
            this.form.setFieldsValue(pick(this.model,'customerId','custTaxCode','checkCode'))
            this.form.setFieldsValue({authorBeginDate: this.model.authorBeginDate ? moment(this.model.authorBeginDate) : null}) //时间格式化
            this.form.setFieldsValue({authorEndDate: this.model.authorEndDate ? moment(this.model.authorEndDate) : null}) //时间格式化
          });
        } else {
          this.addStatus = false;
          this.editStatus = true;
        }
        this.visible = true;
      },
      close () {
        this.$emit('close');
        this.visible = false;
        this.disableSubmit = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
              method = 'put';
            }
            let formData = Object.assign(this.model, values);
            formData.authorBeginDate = formData.authorBeginDate ? formData.authorBeginDate.format('YYYY-MM-DD HH:mm:ss') : null;
            formData.authorEndDate = formData.authorEndDate ? formData.authorEndDate.format('YYYY-MM-DD HH:mm:ss') : null;
            formData.customerId = this.customerId;

            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })



          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>

<style scoped>
  .avatar-uploader > .ant-upload {
    width:104px;
    height:104px;
  }
  .ant-upload-select-picture-card i {
    font-size: 49px;
    color: #999;
  }

  .ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
  }

  .ant-table-tbody .ant-table-row td{
    padding-top:10px;
    padding-bottom:10px;
  }

  .drawer-bootom-button {
    position: absolute;
    bottom: -8px;
    width: 100%;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: right;
    left: 0;
    background: #fff;
    border-radius: 0 0 2px 2px;
  }
</style>