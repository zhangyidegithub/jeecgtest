<template>
  <div :style="{ padding: '0 0 32px 32px' }">
    <h4 :style="{ marginBottom: '20px' }">{{ title }}</h4>
    <v-chart :force-fit="true" :height="height" :data="data" :scale="scale" :padding="['auto', 'auto', '40', '50']">
      <v-tooltip />
      <v-axis />
      <v-legend />
      <v-line position="type*y" color="x"/>
      <v-point position="type*y" color="x" :size="4" :v-style="style" :shape="'circle'" />
    </v-chart>
  </div>
</template>

<script>
  import { DataSet } from '@antv/data-set'

  export default {
    name: 'LineChartMultid',
    props: {
      title: {
        type: String,
        default: ''
      },
      dataSource:{
        type:Array,
        default:()=>[]
      },
      sourceDataConst:{
        type:Array,
        default:()=>[]
      },
      fields:{
        type:Array,
        default: () => ['ThisYear', 'LastYear']
      },
      height:{
        type:Number,
        default:254
      }
    },
    data() {
      return {
        data:"",
        scale: [{
          dataKey: 'x',
          min: 0,
          max: 1
        }],
        style: { stroke: '#fff', lineWidth: 1 },
      };
    },
    watch: {
      'dataSource': function () {
        this.drawChart();
      },
      'sourceDataConst': function () {
        this.drawChart();
      }
    },
    mounted(){
      this.drawChart()
    },
    methods:{
      drawChart(){
        let temp = this.sourceDataConst;
        if (this.dataSource && this.dataSource.length > 0) {
          temp = this.dataSource.map(item => {
            // 为了防止直接修改源数据导致报错
            let obj = Object.assign({}, item)
            obj.type = obj.x
            return obj
          })
        }
        const dv = new DataSet.View().source(temp);
        dv.transform({
          type: 'fold',
          fields: this.fields,
          key: 'x',
          value: 'y',
        });
        this.data=dv.rows;
      }
    }
  }
</script>

<style scoped>

</style>