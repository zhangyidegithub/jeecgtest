<template>
  <div class="page-header-index-wide">
    <a-row :gutter="24">
      <a-col :sm="24" :md="12" :xl="6" >
        <chart-card :loading="loading" title="今日申请冲红金额" :total="this.redMoneyDay">
          <a-tooltip title="今日申请冲红金额" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <template slot="footer">累计申请冲红金额<span>￥ {{ this.sumRedMoney }}</span></template>
        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">
        <chart-card :loading="loading" title="今日申请红票数量" :total="this.countRedDay" >
          <a-tooltip title="今日申请红票数量" slot="action">
            <a-icon type="info-circle-o" />
          </a-tooltip>
          <div>
            <!--<mini-area x="月份" y="票量"/>-->
          </div>
          <template slot="footer">累计申请红票数量<span> {{ this.sumCountRed }}</span></template>
        </chart-card>
      </a-col>
      <a-col :sm="24" :md="12" :xl="6" :style="{ marginBottom: '24px' }">

        <!--<chart-card :loading="loading" title="日红票量" :total="8846 | NumberFormat">-->
          <chart-card :loading="loading" v-has="'ip:show'" :bordered="false" title="-">
            <div style="height: 105px">
              <a-row>
                <a-col :span="8">
                  <div class="head-info" :class="center && 'center'">
                    <span>今日IP</span>
                    <p><a>{{ loginfo.todayIp }}</a></p>
                  </div>
                </a-col>
                <a-col :span="8">
                  <div class="head-info" :class="center && 'center'">
                    <span>今日访问</span>
                    <p><a>{{ loginfo.todayVisitCount }}</a></p>
                  </div>
                </a-col>
                <a-col :span="8">
                  <div class="head-info" :class="center && 'center'">
                    <span>访问总览</span>
                    <p><a>{{ loginfo.totalVisitCount }}</a></p>
                  </div>
                </a-col>
              </a-row>
            </div>
            <template slot="footer">今日访问量<span> {{ loginfo.todayVisitCount }}</span></template>
          </chart-card>
      </a-col>
    </a-row>

    <!--<a-card :loading="loading" :bordered="false" :body-style="{padding: '0'}">-->
      <!--<div class="salesCard">-->
        <!--<a-tabs default-active-key="1" size="large" :tab-bar-style="{marginBottom: '24px', paddingLeft: '16px'}">-->

          <!--<div class="extra-wrapper" slot="tabBarExtraContent">-->
          <!--</div>-->
          <!--<a-tab-pane loading="true" tab="红字发票申请单量同比" key="1">-->
            <!--<a-row>-->
              <!--<a-col :xl="24" :lg="24" :md="24" :sm="24" :xs="24">-->
                <!--&lt;!&ndash;<bar title="申请红字发票统计" :dataSource="barData"/> 柱状体&ndash;&gt;-->
                <!--<line-chart-multid :height="420" :sourceDataConst="barData3"/>-->
              <!--</a-col>-->
            <!--</a-row>-->
          <!--</a-tab-pane>-->
        <!--</a-tabs>-->
      <!--</div>-->
    <!--</a-card>-->
    <a-card :loading="loading" :bordered="false" :body-style="{padding: '0'}">
      <div class="salesCard">
        <a-tabs default-active-key="1" size="large" :tab-bar-style="{marginBottom: '24px', paddingLeft: '16px'}">
          <div class="extra-wrapper" slot="tabBarExtraContent">
          </div>
          <a-tab-pane loading="true" tab="红字发票申请单量同比" key="1">
            <a-row>
              <a-col :xl="24" :lg="24" :md="24" :sm="24" :xs="24">
                <!--<bar title="申请红字发票统计" :dataSource="barData"/> 柱状体-->
                <!--<line-chart-multid :height="420" :sourceDataConst="barData3"/>-->
                <EcHistogram ref="ech" :dataSource="barData5" ></EcHistogram>
              </a-col>
            </a-row>
          </a-tab-pane>
        </a-tabs>
      </div>
    </a-card>
  </div>
</template>

<script>
  import ChartCard from '@/components/ChartCard'
  import ACol from "ant-design-vue/es/grid/Col"
  import ATooltip from "ant-design-vue/es/tooltip/Tooltip"
  import MiniArea from '@/components/chart/MiniArea'
  import MiniBar from '@/components/chart/MiniBar'
  import MiniProgress from '@/components/chart/MiniProgress'
  import RankList from '@/components/chart/RankList'
  import Bar from '@/components/chart/Bar'
  import Trend from '@/components/Trend'
  import {getLoginfo} from '@/api/api'
  import Vue from 'vue';
  import LineChartMultid from '@/components/chart/LineChartMultid'
  import EcHistogram from '@/components/chart/echarts/EcHistogram'
  import store from '@/store'
  import { redFpCylindrical, redFpStatisticsCount, redFpStatisticsMoney } from '../../api/login'
  import { ACCESS_TOKEN, USER_NAME } from '../../store/mutation-types'
  import { formatDate, formatDate2 } from '../../utils/util'
  const rankList = []
  const barData = []
  let datee=new Date();
  const token=Vue.ls.get(ACCESS_TOKEN);
  export default {
    name: "Analysis",
    components: {
      ATooltip,
      ACol,
      ChartCard,
      MiniArea,
      MiniBar,
      MiniProgress,
      RankList,
      Bar,
      Trend,
      LineChartMultid,
      EcHistogram
    },
    data() {
      return {
        loading: true,
        center: null,
        rankList,
        barData:[
        ],
        loginfo:{},
        lastYear:'',
        thisYear:'',
        Year:'',
        NSRSBH:'',
        redMoneyDay:'',
        sumRedMoney:'',
        countRedDay:'',
        sumCountRed:'',
        today:'',
        barData4:[ [0,0,0,0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0,0,0,0]],
        barData5:[]
      }
    },
    created() {
      setTimeout(() => {
        // const date=new Date;
        this.today=formatDate2(datee,'yyyy-MM-dd');
        this.loading = !this.loading
        this.thisYear=datee.getFullYear();
        this.lastYear=datee.getFullYear()-1;
        this.Year=this.thisYear;
        // console.log(today);
        this.NSRSBH=Vue.ls.get(USER_NAME);
        // this.redFpmonthly(),
        // this.redFpBrokenLine(),
        this.redMoneyday(),
          this.sumRedmoney(),
          this.countRedday(),
          this.sumCountred(),
        this.EredFpBrokenLine()

      }, 100)
      this.initLogInfo();
      const a=setInterval(this.Eshujufuyu, 1000);
      setInterval(() => {
        clearInterval(a);
      },5000)

    },
    watch: {
      // 'DataRes1': function () {
      //   this.shuju(this.DataRes1);
      //   this.shuju(this.DataRes2);
      // },
      // 'DataRes2': function () {
      //   this.shuju(this.DataRes1);
      //   this.shuju(this.DataRes2);
      // },
      // 'DataRes1': function () {
      //   this.shujufuyu();
      // },
      // 'DataRes2': function () {
      //   this.shujufuyu();
      // },
      // 'DataRes2': function () {
      //   this.shujufuyu();
      // },
      'barData4': function () {
        this.Eshujufuyu();
        this.$refs.ech.drawLine();
      },
      // 'barData5': function () {
      //   this.$refs.ech.drawLine();
      // }

    },
    methods: {
      //年份切换
      // onClick({ key }){
      //   if(key==='1'){
      //     this.Year=this.lastYear;
      //     this.redFpmonthly()
      //     }else if (key==='2'){
      //     this.Year=this.thisYear;
      //     this.redFpmonthly()
      //   }
      // },
      //红字发票柱状体请求
      // redFpmonthly() {
      //   const that = this;
      //   let NSRSBH = this.userNameAchane(that.NSRSBH);
      //   let params;
      //   if (NSRSBH === '') {
      //     params = {
      //       year: that.Year
      //     }
      //   } else {
      //     params = {
      //       count: 0,
      //       month: "",
      //       userName: NSRSBH,
      //       year: that.Year
      //     }
      //   }
      //   that.loading = true;
      //   redFpCylindrical(token,params).then((res)=>{
      //     if(res.success){
      //       that.$message.success(res.message);
      //       that.loading = false;
      //       const totalList = []
      //       let x,y
      //       for (let i = 0; i < res.result.length; i++) {
      //          x=res.result[i].month
      //          y=res.result[i].count
      //         totalList.push({
      //           x: x+"月",
      //           y: y
      //         })
      //       }
      //       this.barData=totalList;
      //     }else {
      //       that.$message.error(res.message);
      //       that.loading = false;
      //     }
      //   })
      // },
      EredFpBrokenLine(){
        const that = this;
        let NSRSBH = this.userNameAchane(that.NSRSBH);
        let params1;
        let params2;
        if (NSRSBH === '') {
          params1 = {
            year: that.thisYear
          }
          params2 ={
            year: that.lastYear
          }
        } else {
          params1 = {
            count: 0,
            month: "",
            userName: NSRSBH,
            year: that.thisYear
          }
          params2 = {
            count: 0,
            month: "",
            userName: NSRSBH,
            year: that.lastYear
          }
        }
        //今年数据
        redFpCylindrical(token,params1).then((res)=>{
          if(res.success){
            that.loading = false;
            if (res.result!==null){
              let i
              for (i=0;i<res.result.length;i++){
                res.result[i].month=res.result[i].month.replace(/^0/, '');
                this.barData4[0][parseInt(res.result[i].month)]=res.result[i].count;
              }
            } else {
              that.$message.success("今年数据为空");
            }
          }else {
            that.$message.error(res.message);
            that.loading = false;
          }
        })
        //去年数据
        redFpCylindrical(token,params2).then((res)=>{
          if(res.success){
            that.loading = false;
            if (res.result!==null){
              let i
              for (i=0;i<res.result.length;i++){
                res.result[i].month=res.result[i].month.replace(/^0/, '');
                this.barData4[1][parseInt(res.result[i].month)]=res.result[i].count;
              }
            } else {
              that.$message.success("去年数据为空");
            }
          }else {
            that.$message.error(res.message);
            that.loading = false;
          }
        })
      },
      Eshujufuyu(){
        this.barData5=this.barData4;
      },
      //红字发票折线图请求
//       redFpBrokenLine(){
//         const that = this;
//         let NSRSBH = this.userNameAchane(that.NSRSBH);
//         let params1;
//         let params2;
//         if (NSRSBH === '') {
//           params1 = {
//             year: that.thisYear
//           }
//           params2 ={
//             year: that.lastYear
//           }
//         } else {
//           params1 = {
//             count: 0,
//             month: "",
//             userName: NSRSBH,
//             year: that.thisYear
//           }
//           params2 = {
//             count: 0,
//             month: "",
//             userName: NSRSBH,
//             year: that.lastYear
//           }
//         }
//         // 今年数据的请求
//         redFpCylindrical(token,params1).then((res)=>{
//           if(res.success){
//             that.loading = false;
//             this.DataRes1=res.result;
//             this.shuju(this.DataRes1);
//           }else {
//             that.$message.error(res.message);
//             that.loading = false;
//           }
//         })
//         // 去年数据的请求
//           redFpCylindrical(token,params2).then((res)=>{
//             if(res.success){
//               that.loading = false;
//               this.DataRes2=res.result;
//               this.shuju(this.DataRes2);
//             }else {
//               that.$message.error(res.message);
//               that.loading = false;
//             }
//           })
//       },
//         shuju(arry){
//         if (arry === null){
//           return arry;
//         }
//           // 数据处理
//           let i,j,k
//           for (i=0;i<this.barData2.length;i++){
//             // 今年
//             for (j=0;j<arry.length;j++){
//               if (this.barData2[i].type===arry[j].month){
//                 // console.log(this.DataRes1[j].month)
//                 if (arry===this.DataRes1) {
//                   this.barData2[i].ThisYear=arry[j].count;}else if (arry===this.DataRes2){
//                   this.barData2[i].LastYear=arry[j].count;
//                 }
//                 // console.log(this.barData2[i].year);
//               }
//             }
//           }
//         },
// // 数据赋予
//           shujufuyu(){
//             setTimeout(() => {
//               this.barData3=this.barData2;
//             },1000)
//           },







      //今日冲红金额
      redMoneyday(){
        const that=this;
        let NSRSBH=this.userNameAchane(that.NSRSBH);
        let params
          if(NSRSBH===''){
            params= {
              date:that.today
            }
          }else {
            params= {
              date:that.today,
              userName: NSRSBH
            }
          }
        redFpStatisticsMoney(token,params).then((res)=>{
          if (res.success){
            // that.$message.success(res.message);
            that.loading=false;
            if (res.result === null) {
              that.redMoneyDay="0";
            }else {
              that.redMoneyDay = res.result.amount +"";
            }          }else {
            that.$message.error(res.message);
            that.loading = false;
          }
        })
      },
      //本月冲红金额
      sumRedmoney(){
        const that=this;
        let NSRSBH=this.userNameAchane(that.NSRSBH);
        let params
        if(NSRSBH===''){
          params= {
          }
        }else {
          params= {
            userName: NSRSBH
          }
        }
        redFpStatisticsMoney(token,params).then((res)=>{
          if (res.success){
            // that.$message.success(res.message);
            that.loading=false;
            if (res.result === null) {
              that.sumRedMoney="0";
            }else {
              that.sumRedMoney = res.result.amount + "";
            }          }else {
            that.$message.error(res.message);
            that.loading = false;
          }
        })

      },
      // //今日红票量
      countRedday(){
        const that=this;
        let NSRSBH=this.userNameAchane(that.NSRSBH);
        let params
        if(NSRSBH===''){
          params= {
            date:that.today,
          }
        }else {
          params= {
            date:that.today,
            userName: NSRSBH
          }
        }
        redFpStatisticsCount(token,params).then((res)=>{
          if (res.success){
            // that.$message.success(res.message);
            that.loading=false;
            that.countRedDay=res.result.count+"";
          }else {
            that.$message.error(res.message);
            that.loading = false;
          }
        })

      },
      // //本月红票量
      sumCountred(){
        const that=this;
        let NSRSBH=this.userNameAchane(that.NSRSBH)+'';
        let params
        if(NSRSBH===''){
          params= {
          }
        }else {
          params= {
            userName: NSRSBH
          }
        }
        redFpStatisticsCount(token,params).then((res)=>{
          if (res.success){
            // that.$message.success(res.message);
            that.loading=false;
            that.sumCountRed=res.result.count+"";
          }else {
            that.$message.error(res.message);
            that.loading = false;
          }
        })
      },
      userNameAchane(a){
        if (a==='admin' || a==='ywadmin'){
          return a='';
        } else {
          return a;
        }
      },
      initLogInfo () {
        getLoginfo(null).then((res)=>{
          if(res.success){
            this.loginfo = res.result;
          }
        })
      },
    }
  }
</script>

<style lang="scss" scoped>
  .extra-wrapper {
    line-height: 55px;
    padding-right: 24px;

    .extra-item {
      display: inline-block;
      margin-right: 24px;

      a {
        margin-left: 24px;
      }
    }
  }

  /* 首页访问量统计 */
  .head-info {
    position: relative;
    text-align: left;
    padding: 0 32px 0 0;
    min-width: 125px;

    &.center {
      text-align: center;
      padding: 0 32px;
    }

    span {
      color: rgba(0, 0, 0, .45);
      display: inline-block;
      font-size: .95rem;
      line-height: 42px;
      margin-bottom: 4px;
    }
    p {
      line-height: 42px;
      margin: 0;
      a {
        font-weight: 600;
        font-size: 1rem;
      }
    }
  }
</style>