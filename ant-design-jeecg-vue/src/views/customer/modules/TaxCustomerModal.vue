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

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="纳税人识别号"

          hasFeedback>
          <a-input placeholder="请输入企业纳税人识别号" v-decorator="['custTaxCode', validatorRules.custTaxCode ]" :disabled="disabled"/>
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="纳税人名称"
          hasFeedback>
          <a-input placeholder="请输入企业纳税人名称" v-decorator="['custTaxName',  validatorRules.custTaxName]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="纳税人地址电话">
          <a-input placeholder="请输入企业纳税人地址电话" v-decorator="['custAddressPhone', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="企业纳税人银行账号">
          <a-input placeholder="请输入企业纳税人银行账号" v-decorator="['custBankAccount', {}]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联系人"
          hasFeedback>
          <a-input placeholder="请输入联系人" v-decorator="['linkMan',validatorRules.linkMan]" />
        </a-form-item>
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="联系人手机号"
          hasFeedback>
          <a-input placeholder="请输入联系人手机号" v-decorator="['linkPhone',validatorRules.linkPhone]" />
        </a-form-item>
        <a-form-item label="登陆密码" :labelCol="labelCol" :wrapperCol="wrapperCol" :hidden="hidding" hasFeedback>
          <a-input type="password" placeholder="请输入登陆密码" v-decorator="[ 'password', validatorRules.password]" />
        </a-form-item>

        <a-form-item label="确认密码" :labelCol="labelCol" :wrapperCol="wrapperCol" :hidden="hidding"  hasFeedback>
          <a-input type="password" @blur="handleConfirmBlur" placeholder="请重新输入登陆密码" v-decorator="[ 'confirmpassword', validatorRules.confirmpassword]"/>
        </a-form-item>
        <template>
          <a-form-item
            label="是否同步至用户"
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            :hidden="hidding" >
            <a-radio-group @change="onChangeRadio"   v-decorator="[ 'isSyncUser', {initialValue:1}]">
              <a-radio :value="1">是</a-radio>
              <a-radio :value="0">否</a-radio>
            </a-radio-group>
          </a-form-item>
          <a-form-item
            label="选取角色"
            :labelCol="labelCol"
            :wrapperCol="wrapperCol"
            :hidden="roleHidding"
            hasFeedback>
            <a-select
              mode="multiple"
              style="width: 100%"
              placeholder="选取角色"
              :getPopupContainer="getPopupContainer"
              v-decorator="[ 'selectedRole',validatorRules.selectedRole]">
              <a-select-option v-for="(role) in roleList" :key="role.id" :value="role.id" >
                {{ role.roleName }}
              </a-select-option>
            </a-select>
          </a-form-item>
        </template>
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
  import { httpAction ,getAction} from '@/api/manage'
  import pick from 'lodash.pick'
  import { ACCESS_TOKEN } from "@/store/mutation-types"
  import Vue from 'vue'
  import {queryall } from '@/api/api'
  //import moment from "moment"

  export default {
    name: "TaxCustomerModal",
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
        confirmLoading: false,
        headers:{},
        roleList:[],
        selectedroles:[],
        roleHidding:false,
        hidding:false,
        disabled:false,
        selectedRole:[],
        roleRadio:"1",
        form: this.$form.createForm(this),
        validatorRules:{
          custTaxCode:{rules: [
            { required: true, message: '请输入企业纳税人识别号!' },
              {
                validator: this.validateCustTaxCode,
              }
          ]},
          custTaxName:{rules: [{ required: true, message: '请输入企业纳税人名称!' }]},
          linkMan:{rules: [{ required: true, message: '请输入联系人!' }]},
          linkPhone:{rules: [{required: true, message: '请输入联系人手机号码!' },{validator: this.validatePhone}]},
          selectedRole:{
            rules: [{
              validator:this.checkSelectRole,
            }],
          initialValue:["8845ccc45c95dd5ad7919eba58daab56"],
          },
          confirmpassword:{
            rules: [{
              required: true, message: '请重新输入登陆密码!',
            }, {
              validator: this.compareToFirstPassword,
            }],
          },
          password:{
            rules: [{
              required: true, message: '请输入密码!',
            }, {
              validator: this.validateToNextPassword,
            }],
          },
        },
        url: {
          add: "/customer/taxCustomer/add",
          edit: "/customer/taxCustomer/edit",
          taxCustomerAuthorList: "/customer/taxCustomer/queryTaxCustomerAuthorByMainId",
        },
      }
    },
    created () {
      const token = Vue.ls.get(ACCESS_TOKEN);
      this.headers = {"X-Access-Token":token}
    },
    methods: {
      getPopupContainer(trigger){//解决下拉组件不更随滚动问题
        return trigger.parentElement;
      },
      onChangeRadio(e){
        var radioValue = e.target.value;
        switch(radioValue){
          case 1://是
            this.roleHidding = false;
            this.roleRadio='1';
            this.initialRoleList();
            break;
          case 0://否
            this.roleRadio = "0";
            this.roleHidding = true;
            break;
          default:
            break;
        }
      },
      /*handleChange(value,optionArray) {
        var selectedRoleId = [];
       optionArray.forEach(function(v,i){
          selectedRoleId.push(v.key);
        })
        this.selectedRoleIds = selectedRoleId;
      },*/
      initialRoleList(){
        queryall().then((res)=>{
          if(res.success){
            var roleArray = res.result;
            var roles = [];
            roleArray.forEach(function(v,i) {
              if(v.roleCode!="admin" && v.roleCode!="ywgly"){
                roles.push(v);
              }
            });
            this.roleList = roles;
          }else{
            console.log(res.message);
          }
        });
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
      add () {
        this.validatorRules.selectedRole={
          rules: [{
            validator:this.checkSelectRole,
          }],
            initialValue:["8845ccc45c95dd5ad7919eba58daab56"],
        };
        this.validatorRules.confirmpassword = {
          rules: [{
            required: true, message: '请重新输入登陆密码!',
          }, {
            validator: this.compareToFirstPassword,
          }],
        };
        this.validatorRules.password ={
          rules: [{
            required: true, message: '请输入密码!',
          }, {
            validator: this.validateToNextPassword,
          }],
        };
        this.edit({});
      },
      edit (record) {
        this.selectedRoleIds=[];
        this.selectedroles=[];
        this.roleHidding=false;
        this.selectedRole=[];
        this.roleRadio="1";
        this.hidding = false;
        this.roleRadio="1";
        this.disabled = false;
        this.resetScreenSize(); // 调用此方法,根据屏幕宽度自适应调整抽屉的宽度
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        if(this.model.customerId){
          this.hidding = true;
          this.roleHidding = true;
          this.roleRadio="0";
          this.disabled = "disabled";
          this.validatorRules.password={};
          this.validatorRules.confirmpassword={};
          this.validatorRules.selectedRole={};
        }
        if(this.roleRadio=='1'){
          this.roleHidding = false;
          this.initialRoleList();
        }
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'linkMan','linkPhone','password','customerId','custTaxCode','custTaxName','custAddressPhone','custBankAccount'))
          //时间格式化
        });

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
            if(!this.model.customerId){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
              method = 'put';
            }
            let formData = Object.assign(this.model, values);
            formData.isSyncUser = this.roleRadio;
            formData.selectedroles = formData.selectedRole.length>0?formData.selectedRole.join(","):'';
            console.info(formData.selectedroles)
            //时间格式化 formData.isSyncUser
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
      validatePhone(rule, value, callback){
        if(!value || new RegExp(/^1[3|4|5|7|8][0-9]\d{8}$/).test(value)){
          callback();
        }else{
          callback("请输入正确格式的手机号码!");
        }
      },
      validateToNextPassword  (rule, value, callback) {
        const form = this.form;
        if (value && this.confirmDirty && !this.hidding) {
          form.validateFields(['confirm'], { force: true })
        }
        callback();
      },
      compareToFirstPassword  (rule, value, callback) {
        const form = this.form;
        if (value && value !== form.getFieldValue('password') && !this.hidding) {
          callback('两次输入的密码不一样！');
        } else {
          callback()
        }
      },
      checkSelectRole  (rule, value, callback) {
        if (this.roleRadio=='1' && value.length<=0 && !this.roleHidding) {
          callback('请选择角色！');
        } else {
          callback();
        }
      },
      validateCustTaxCode(rule, value, callback){
          var params = {
            customerId:this.model.customerId,
            custTaxCode:value
          };
          getAction("/customer/taxCustomer/checkCustTaxCode",params).then((res)=>{
            if(res.success){
              callback();
            }else{
              callback("纳税人识别号已存在！");
            }
          });
        },
      handleConfirmBlur  (e) {
        const value = e.target.value
        this.confirmDirty = this.confirmDirty || !!value
      }

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