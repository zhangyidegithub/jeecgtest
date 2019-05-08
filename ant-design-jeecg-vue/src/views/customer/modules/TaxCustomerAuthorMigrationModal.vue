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
          label="当前金税盘号"
          hasFeedback>
          <a-input v-model="sourceCusCheckCode" disabled="disabled"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="目标金税盘号"
          hasFeedback>
          <a-select
            showSearch
            v-decorator="[ 'targetAuthorId', validatorRules.targetAuthorId]"
            :getPopupContainer="getPopupContainer"
            :filterOption="filterOption"
            optionFilterProp="children"
            placeholder="请选择金税盘">
            <a-select-option v-for="item in customerAuthors" v-if="item.checkCode==sourceCusCheckCode ? false:true"  :key="item.id" >{{item.checkCode}}</a-select-option>
          </a-select>
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
    name: "TaxCustomerAuthorMigrationModal",
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
        headers: {},
        previewVisible: false,
        addStatus: false,
        editStatus: false,
        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
          targetAuthorId:{rules: [{ required: true, message: '请选择金税盘号!' }]},
        },
        url: {
          add: "/customer/taxCustomerAuthor/migration",
          authorList: "/customer/taxCustomer/queryTaxCustomerAuthorByMainId",
          edit: "/customer/taxCustomerAuthor/edit",
          getTaxCustomerAuthorInfoList: "/customer/taxCustomer/queryTaxCustomerAuthorInfoListByMainId",
        },
        cusAuthorId: '',
        targetCusAuthorId: '',
        sourceCusCheckCode:'',
        customerAuthors:[],
      }
    },
    created () {
      const token = Vue.ls.get(ACCESS_TOKEN);
      this.headers = {"X-Access-Token": token}
    },
    methods: {
      moment,
      getPopupContainer(trigger){//解决下拉组件不更随滚动问题
        return trigger.parentElement;
      },
      filterOption(input, option) {
        return option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
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
      add (authorId) {
        if (authorId) {
          this.cusAuthorId = authorId;
          this.edit({"authorId":authorId}, '');
        } else {
          this.$message.warning("请选择金税盘");
        }
      },
      edit(record, v) {
        if (v == 'e') {
          this.disableSubmit = false;
        } else if (v == 'd') {
          this.disableSubmit = false;
        } else {
          this.disableSubmit = false;
        }

        this.form.resetFields();
        if (record.id) {
          this.model = Object.assign({}, record);
          this.addStatus = false;
          this.editStatus = true;
          this.sourceCusCheckCode = record.checkCode;
         // this.form.setFieldsValue({"sourceCusCheckCode":this.model.checkCode});
          this.$nextTick(() => {

          });
          this.cusAuthorId = this.model.id;
          getAction(this.url.authorList, {mainId: this.model.customerId}).then((res) => {
            if (res.success) {
              this.customerAuthors = res.result;
            } else {
              this.customerAuthors = [];
            }
          })
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
            let httpurl = this.url.add;
            let method =  'post';
            let formData = Object.assign(this.model, values);
            formData.sourceAuthId = this.cusAuthorId;
            formData.targetAuthId = formData.targetAuthorId;
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