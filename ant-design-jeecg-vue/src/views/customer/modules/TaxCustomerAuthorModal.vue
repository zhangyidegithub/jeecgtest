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
    <template slot="title">
      <div style="width: 100%;">
        <span>{{ title }}</span>
        <span style="display:inline-block;width:calc(100% - 51px);padding-right:10px;text-align: right">
          <a-button @click="toggleScreen" icon="appstore" style="height:20px;width:20px;border:0px"></a-button>
        </span>
      </div>
    </template>
    <!-- 编辑 -->
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="纳税人识别号"
          v-model="this.custTaxCode"
          :hidden="hiding"
          hasFeedback>
          <a-input v-decorator="[ 'custTaxCode', {}]" disabled="disabled"/>
        </a-form-item>
       <!-- <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="纳税人识别号">
          <a-input placeholder="请输入企业纳税人识别号" v-decorator="['custTaxCode',{rules: [{ required: custTaxCode, message: '请输入企业纳税人识别号!' }]}]" />
        </a-form-item>-->
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="盘号">
          <a-input placeholder="请输入盘号，用于客户端授权验证使用" v-decorator="['checkCode', validatorRules.checkCode]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="开票机号">
          <a-input placeholder="请输入本开票机号码，主开票机为0" v-decorator="['machineNo', validatorRules.machineNo]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联系人">
          <a-input placeholder="请输入联系人" v-decorator="['linkMan', validatorRules.linkMan]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联系人手机号">
          <a-input placeholder="请输入联系人手机号" v-decorator="['linkPhone', validatorRules.linkPhone]" />
        </a-form-item>
        <a-form-item label="无票标志" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select v-decorator="[ 'isinvEmpty', validatorRules.isinvEmpty]" placeholder="请选择无票标志">
            <a-select-option :value="1">有可用发票</a-select-option>
            <a-select-option :value="0">无可用发票</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="抄税标志" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select v-decorator="[ 'isrepReached', {rules: [{ required: true, message: '请选择是否到抄税期!' }]}]" placeholder="请选择是否到抄税期">
            <a-select-option :value="1">已到抄税期</a-select-option>
            <a-select-option :value="0">未到抄税期</a-select-option>
          </a-select>
        </a-form-item>
        <!--{initialValue:0}-->
        <a-form-item label="锁死标志" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select  v-decorator="[ 'islockReached', {rules: [{ required: true, message: '请选择是否到锁死期!' }]}]" placeholder="请选择锁死标志">
            <a-select-option :value="1">已到锁死期</a-select-option>
            <a-select-option :value="0">未到锁死期</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="授权状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-select v-decorator="[ 'authorStatus', {rules: [{ required: true, message: '请选择授权状态!' }]}]" placeholder="请选择授权状态">
            <a-select-option :value="1">有效</a-select-option>
            <a-select-option :value="0">无效</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="企业信息Id"
          v-model="this.customerId"
          :hidden="true"
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

  export default {
    name: "TaxCustomerAuthorModal",
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
            title: '授权状态',
            align: "center",
            dataIndex: 'authorStatus',
          },
          {
            title: '联系人',
            align: "center",
            dataIndex: 'linkMan',
          },
          {
            title: '联系人手机号',
            align: "center",
            dataIndex: 'linkPhone',
          },
          {
            title: '盘号',
            dataIndex: 'checkCode',
            align: "checkCode",
          },
          {
            title: '开票机号',
            dataIndex: 'machineNo',
            align: "center",
          },
          {
            title: '无票标志',
            dataIndex: 'isinvEmpty',
            align: "center",
          },
          {
            title: '锁死标志',
            dataIndex: 'islockReached',
            align: "center",
          },
          {
            title: '创建人',
            dataIndex: 'createBy',
            align: "center",
          },
          {
            title: '创建时间',
            dataIndex: 'createTime',
            align: "center",
          },
          {
            title: '更新时间',
            dataIndex: 'updateBy',
            align: "center",
          },
          {
            title: '更新人',
            dataIndex: 'updateTime',
            align: "center",
          },
        ],
        selectedRowKeys: [],
        customerId: "",
        custTaxCode: "",
        hiding: false,
        headers: {},
        picUrl: "",
        previewVisible: false,
        addStatus: false,
        editStatus: false,
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
          checkCode:{rules: [{ required: true, message: '请输入盘号!' }]},
          machineNo:{rules: [{ required: true, message: '请输入本开票机号码，主开票机为0' }]},
          linkMan:{rules: [{ required: true, message: '请输入联系人!' }]},
          linkPhone:{rules: [{required: true, message: '请输入联系人手机号码!' },{validator: this.validatePhone}]},
          isinvEmpty:{rules: [{required: true, message: '请选择是否有票!' }]},
        },
        url: {
          add: "/customer/taxCustomerAuthor/add",
          edit: "/customer/taxCustomerAuthor/edit",
          getTaxCustomerAuthorList: "/customer/taxCustomer/queryTaxCustomerAuthorListByMainId",
        },
      }
    },
    created () {
      const token = Vue.ls.get(ACCESS_TOKEN);
      this.headers = {"X-Access-Token": token}
    },
    methods: {
      validatePhone(rule, value, callback){
        if(!value || new RegExp(/^1[3|4|5|7|8][0-9]\d{8}$/).test(value)){
          callback();
        }else{
          callback("请输入正确格式的手机号码!");
        }
      },
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
      add (customerId,custTaxCode) {
        this.hiding = true;
        if (customerId) {
          this.customerId = customerId;
          this.custTaxCode = custTaxCode;
          this.edit({customerId,custTaxCode}, '');
        } else {
          this.$message.warning("请选择一个企业信息");
        }
      },
      detail(record) {
        this.edit(record, 'd');
      },
      edit(record, v) {
        this.resetScreenSize(); // 调用此方法,根据屏幕宽度自适应调整抽屉的宽度
        if (v == 'e') {
          this.hiding = false;
          this.disableSubmit = false;
        } else if (v == 'd') {
          this.hiding = false;
          this.disableSubmit = true;
        } else {
          this.hiding = true;
          this.disableSubmit = false;
        }
        this.form.resetFields();
        this.customerId = record.customerId;
        this.custTaxCode = record.custTaxCode;
        this.model = Object.assign({}, record);
        if (record.id) {
          this.hiding = false;
          this.addStatus = false;
          this.editStatus = true;

          this.$nextTick(() => {
            this.model.islockReached = parseInt(this.model.islockReached);
            this.model.isrepReached = parseInt(this.model.isrepReached);
            this.model.isinvEmpty = parseInt(this.model.isinvEmpty);
            this.form.setFieldsValue(pick(this.model,'customerId','custTaxCode','authorStatus','linkMan','linkPhone','checkCode','machineNo','isinvEmpty','isrepReached','islockReached'))
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
            formData.customerId = this.customerId;
            formData.custTaxCode = this.custTaxCode;

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