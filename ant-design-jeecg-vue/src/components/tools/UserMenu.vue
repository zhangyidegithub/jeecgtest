<template>
  <div class="user-wrapper" :class="theme">
    <!--<span class="action">-->
      <!--<a-icon type="question-circle-o"></a-icon>-->
    <!--</span>-->
    <!--<header-notice class="action"/>-->
    <a-dropdown>
      <span class="action action-full ant-dropdown-link user-dropdown-menu">
        <a-avatar class="avatar" size="small" :src="getAvatar()"/>
        <span v-if="isDesktop()">欢迎您，{{ nickname() }}</span>
      </span>
      <!--<a-menu slot="overlay" class="user-dropdown-menu-wrapper">-->
        <!--&lt;!&ndash;<a-menu-item key="0">&ndash;&gt;-->
          <!--&lt;!&ndash;<router-link :to="{ name: 'account-center' }">&ndash;&gt;-->
            <!--&lt;!&ndash;<a-icon type="user"/>&ndash;&gt;-->
            <!--&lt;!&ndash;<span>个人中心</span>&ndash;&gt;-->
          <!--&lt;!&ndash;</router-link>&ndash;&gt;-->
        <!--&lt;!&ndash;</a-menu-item>&ndash;&gt;-->
        <!--&lt;!&ndash;<a-menu-item key="1">&ndash;&gt;-->
          <!--&lt;!&ndash;<router-link :to="{ name: 'account-settings' }">&ndash;&gt;-->
            <!--&lt;!&ndash;<a-icon type="setting"/>&ndash;&gt;-->
            <!--&lt;!&ndash;<span>账户设置</span>&ndash;&gt;-->
          <!--&lt;!&ndash;</router-link>&ndash;&gt;-->
        <!--&lt;!&ndash;</a-menu-item>&ndash;&gt;-->
       <!--&lt;!&ndash; <a-menu-item key="2" disabled>-->
          <!--<a-icon type="setting"/>-->
          <!--<span>测试</span>-->
        <!--</a-menu-item>-->
        <!--<a-menu-divider/>-->
        <!--<a-menu-item key="3">-->
          <!--<a href="javascript:;" @click="handleLogout">-->
            <!--<a-icon type="logout"/>-->
            <!--<span>退出登录</span>-->
          <!--</a>-->
        <!--</a-menu-item>&ndash;&gt;-->
      <!--</a-menu>-->
    </a-dropdown>
    <span class="action">
      <a class="logout_title" href="javascript:;" @click="handleLogout">
        <a-icon type="logout"/>
        <span v-if="isDesktop()">&nbsp;退出登录</span>
      </a>
    </span>
  </div>
</template>

<script>
  import HeaderNotice from './HeaderNotice'
  import { mapActions, mapGetters } from 'vuex'
  import { mixinDevice } from '@/utils/mixin.js'
  export default {
    name: "UserMenu",
    mixins: [mixinDevice],
    components: {
      HeaderNotice
    },
    props: {
      theme: {
        type: String,
        required: false,
        default: 'dark'
      }
    },
    methods: {
      ...mapActions(["Logout"]),
      ...mapGetters(["nickname", "avatar"]),
      getAvatar(){
        console.log('url = '+ window._CONFIG['imgDomainURL']+"/"+this.avatar())
        return window._CONFIG['imgDomainURL']+"/"+this.avatar()
      },
      handleLogout() {
        const that = this

        this.$confirm({
          title: '提示',
          content: '真的要注销登录吗 ?',
          onOk() {
            return that.Logout({}).then(() => {
                window.location.href="/";
              //window.location.reload()
            }).catch(err => {
              that.$message.error({
                title: '错误',
                description: err.message
              })
            })
          },
          onCancel() {
          },
        });
      },
    }
  }
</script>

<style scoped>
  .logout_title {
    color: inherit;
    text-decoration: none;
  }
</style>