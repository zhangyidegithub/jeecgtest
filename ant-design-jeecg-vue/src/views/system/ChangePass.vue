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
    <password-modal ref="passwordmodal" @ok="passwordModalOk"></password-modal>
  </a-card>
</template>

<script>
  import Vue from 'vue';
  import PasswordModal from './modules/PasswordModal'
  import  store from '../../store'
  import { ACCESS_TOKEN, USER_NAME } from '../../store/mutation-types'
  import { login, logout } from "@/api/login"
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
        // this.username=store.getters.username;
        this.username=Vue.ls.get(USER_NAME);
        // alert(this.username)
      },500);
    },
    methods: {
      handleChangePassword() {
        this.username=Vue.ls.get(USER_NAME);
        this.$refs.passwordmodal.show(this.username);
      },
      passwordModalOk(){
        // alert('ok')
          store.dispatch('Logout').then(() => {
            Vue.ls.remove(ACCESS_TOKEN)
            window.location.reload()
          })
        },
        // this.$store.commit('SET_TOKEN','');
        // this.$router.push('/dashboard/analysis');
      username1(){
        this.username=store.getters.username;
        console.log(this.username);
        console.log(store.getters);
      }
    }
  }

</script>