<template>
  <a-card
    title="重新设定密码"
    :width="800"
    style="top:20px;"
  >
    <div>
      <p>当前账号：<span>{{ this.username }}</span></p>
    </div>
    <!--<a-button @click="username1()">username</a-button>-->
    <a-button href="javascript:;" @click="handleChangePassword()">修改密码</a-button>
    <!--<a-button @click="username1()">1111</a-button>-->
    <password-modal ref="passwordmodal"></password-modal>
  </a-card>
</template>

<script>
  import PasswordModal from './modules/PasswordModal'
  import  store from '../../store'
  export default {
    name: "ChangePass",
    components: {
      PasswordModal
    },
    data () {
      return {
        username:''
        }
      },
    mounted(){
      setTimeout(() => {
        this.username=store.getters.username;
      },500);
    },
    methods: {
      handleChangePassword() {
        this.username=store.getters.username;
        this.$refs.passwordmodal.show(this.username);
      },
      username1(){
        this.username=store.getters.username;
        console.log(this.username);
      }
    }
  }

</script>