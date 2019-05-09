<template>
  <div id="myChart" :style="{width: '98%', height: '550px',marginBottom:'3%'}"></div>
</template>
<script>
  let datee=new Date();
  let thisYear=datee.getFullYear()+'';
  let lastYear=(datee.getFullYear()-1)+'';
  export default {
    name:'EcHistogram',
    props:{
      dataSource:{
        type:Array,
        default:()=>[]
      }
    },
    mounted(){
      // this.drawLine();
    },
    watch: {
      'dataSource': function () {
        this.drawLine();
      }
    },
    methods: {
      drawLine(){
        // 基于准备好的dom，初始化echarts实例
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 处理数据
        // if (this.dataSource!==undefined){
        // let i,j
        // let par1=0;
        // for (i=0;i<dataSource[0].length;i++){
        //   par1=par1+dataSource[0][i];
        // }
        // console.log(par1);
        // }
        // 绘制图表
        myChart.setOption({
          // title: { text: '在Vue中使用echarts' },
          tooltip: {
            trigger: 'axis'
          },
          // title: {
          //   text: '折线图堆叠'
          // },
          legend: {
            data:[thisYear,lastYear]
          },
          color:["#1890FF","#34C45F"],
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          yAxis: {
            type: 'value'
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          series: [ {
            name:thisYear,
            type:'line',
            stack: '总量',
            data:this.dataSource[0],
            lineStyle:{
              normal:{
                color:'#1890FF',
                width:1
              }
            }
          },
            {
              name:lastYear,
              type:'line',
              stack: '总量',
              data:this.dataSource[1],
              lineStyle:{
                normal:{
                  color:'#34C45F',
                  width:1
                }
              }
            },
            ]
        });
      }
    }
  }

</script>

<style lang="scss" scoped>

</style>