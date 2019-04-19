<template>
  <div class="footer">
    <!--<div class="links">-->
      <!--&lt;!&ndash;<a href="http://www.jeecg.org" target="_blank">JEECG 首页</a>&ndash;&gt;-->
      <!--&lt;!&ndash;<a href="https://github.com/zhangdaiscott/jeecg-boot" target="_blank">&ndash;&gt;-->
        <!--&lt;!&ndash;<a-icon type="github"/>&ndash;&gt;-->
      <!--&lt;!&ndash;</a>&ndash;&gt;-->
      <!--&lt;!&ndash;<a href="https://ant.design/">Ant Design</a>&ndash;&gt;-->
      <!--&lt;!&ndash;<a href="https://vuecomponent.github.io/ant-design-vue/docs/vue/introduce-cn/">Vue Antd</a>&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--&lt;!&ndash;<div class="copyright">&ndash;&gt;-->
      <!--&lt;!&ndash;Copyright&ndash;&gt;-->
      <!--&lt;!&ndash;<a-icon type="copyright"/>&ndash;&gt;-->
      <!--&lt;!&ndash;2019 <span>JEECG开源社区 出品</span>&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
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
    }
  }
</style>