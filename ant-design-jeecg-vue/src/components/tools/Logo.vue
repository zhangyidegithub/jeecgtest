<template>
  <div class="logo">
    <router-link :to="{name:'dashboard'}">
      <img style="height: 59px;width: 200px;" src="~@/assets/logo2.jpg" alt="logo">
      <img style="padding-left: 10px" src="~@/assets/logo2.png" alt="logo">
    </router-link>
  </div>
</template>
<!--<style scoped>-->
  <!--.actived{-->
    <!--display: none;-->
  <!--}-->
<!--</style>-->
<script>
  export default {
    name: 'Logo',
    props: {
      title: {
        type: String,
        default: '发票服务',
        required: false,
      },
      showTitle: {
        type: Boolean,
        default: true,
        required: false
      }
    },
    data(){
      return{
        show:true
      }
    },
    methods: {
      whatfuckdat: function(){
        // alert("a");
        // console.log("aaa")
      }
    }

  }
</script>
<style lang="scss" scoped>
  /*缩小首页布 局顶部的高度*/
  $height: 59px;

  .sider {
    box-shadow: none !important;
    .logo {
      height: $height !important;
      line-height: $height !important;
      box-shadow: none !important;
      transition: background 300ms;
      padding-left:0.1rem !important;
      a {
        color: white;
        &:hover {
          color: rgba(255, 255, 255, 0.8);
        }
      }
    }

    &.light .logo {
      background-color: #1890ff;
    }
  }
</style>
