<template>
  <div class="footer">
    <div class="links">
      <!--<a href="#" target="_parent">陝西航天信息企业发票服务管理平台</a>-->
      <!--<a href="https://github.com/zhangdaiscott/jeecg-boot" target="_blank">-->
        <!--<a-icon type="github"/>-->
      <!--</a>-->
      <!--<a href="http://sn.aisino.com">访问官网</a>-->
      <!--<a href="https://vuecomponent.github.io/ant-design-vue/docs/vue/introduce-cn/">Vue Antd</a>-->
    </div>
    <div class="copyright">
      Copyright
      <a-icon type="copyright"/>
      2019 <span><a href="http://sn.aisino.com" style="color: rgba(0, 0, 0, .45);">陕西航天信息有限公司</a> 出品</span>
    </div>
  </div>
</template>

<script>
  export default {
    name: "LayoutFooter"
  }
</script>

<style lang="scss" scoped>
  .footer {
    padding: 0 16px;
    margin: 48px 0 24px;
    text-align: center;

    .links {
      margin-bottom: 8px;


      a {
        color: rgba(0, 0, 0, .45);

        &:hover {
          color: rgba(0, 0, 0, .65);
        }

        &:not(:last-child) {
          margin-right: 40px;
        }
      }
    }
    .copyright {
      color: rgba(0, 0, 0, .45);
      font-size: 14px;
      margin: 1rem;
    }
  }
</style>