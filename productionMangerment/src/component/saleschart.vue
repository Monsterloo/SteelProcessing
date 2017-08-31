<template>
  <div class="home">
    <p>本月销售种类结构</p>
    <div class="chart" id="monthChart"> </div>
    
    <div class="amountchart" id="saleamount"> </div>
   <p class="charttitle">今年销售种类折线图</p>
  </div>

 
</template>

<script>
    // 引入 ECharts 主模块
var echarts = require('echarts/lib/echarts');


// 引入柱状图
// require('echarts/lib/chart/bar');
require('echarts/lib/chart/pie');
require('echarts/lib/chart/line');
require('echarts/lib/chart/bar');

require('echarts/lib/chart/scatter');
require('echarts/lib/chart/radar');
// 引入提示框和标题组件
require('echarts/lib/component/tooltip');
require('echarts/lib/component/title');
  
    export default {

  data () {
    return {
     saledata:[],
    }
  },
   mounted:function() {
      //http
     this.$http.get(this.servicerurl+'/salesamountMonth',{},{
      headers:{},
      emulateJSON:true
    }).then(function(response){
          console.log(response.data);
          this.saledata=response.data;
          console.log(this.saledata[0].bianxinggangjinzhi);
          //chart
          var myChart = echarts.init(document.getElementById('monthChart'));
            //option
            myChart.setOption({
                
                 tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)",
      
    },
    legend: {
        orient: 'vertical',
        x: 'top',
        // 光圆钢筋、带肋钢筋、扭转钢筋
        data:['光圆钢筋','带肋钢筋','扭转钢筋','变形钢筋(直)','变形钢筋(圆)']
    },
    series: [
        {
            name:'种类结构',
            type:'pie',
            radius: ['40%', '60%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center',
                    
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '25',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:this.saledata[0].guangyuangangjing, name:'光圆钢筋', 
                itemStyle: {
        normal: {
            color: '#34D0BA'
        }
    }},
                {value:this.saledata[0].daileigangjing, name:'带肋钢筋',
                itemStyle: {
        normal: {
            color: '#FFA400'
        }}},
                {value:this.saledata[0].niuzhuangangjing, name:'扭转钢筋'},
                {value:this.saledata[0].bianxinggangjinzhi, name:'变形钢筋(直)',
                itemStyle: {
        normal: {
            color: '#8370D8'
        }}},
                {value:this.saledata[0].bianxinggangjinyuan, name:'变形钢筋(圆)',
                itemStyle: {
        normal: {
            color: '#3216B0'
        }}}
            ]
        }
    ]
            });
    },function(response){
      console.log(response)
    })
    //echarts 实例化


 this.$http.get(this.servicerurl+'/salesamountYear',{},{
      headers:{},
      emulateJSON:true
 }).then(function(response){
        console.log(response.data);
        console.log(response.data.length);
        var databianxingyuan=[];
        var databianxingzhi=[];
        var datadailei=[];
        var dataniuzhuan=[];
        var dataguangyuan=[];
        for(var i=0;i<response.data.length;i++){
            databianxingyuan.push(response.data[i].bianxinggangjinyuan);
             databianxingzhi.push(response.data[i].bianxinggangjinzhi);
            datadailei.push(response.data[i].daileigangjing);
            dataniuzhuan.push(response.data[i].niuzhuangangjing);
             dataguangyuan.push(response.data[i].guangyuangangjing);
        };
         // 基于准备好的dom，初始化echarts实例
        var myChart2 = echarts.init(document.getElementById('saleamount'));

          
              myChart2.setOption({
               tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['光圆钢筋','带肋钢筋','扭转钢筋','变形钢筋(直)','变形钢筋(圆)']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'光圆钢筋',
            type:'line',
            stack: '总量',
            data:dataguangyuan,
            itemStyle: {
        normal: {
            color: '#34D0BA'
        }
            }   
        },
        {
            name:'带肋钢筋',
            type:'line',
            stack: '总量',
            data:datadailei,
            itemStyle: {
        normal: {
            color: '#FFA400'
        }}
        },
        {
            name:'扭转钢筋',
            type:'line',
            stack: '总量',
            data:dataniuzhuan
        },
        {
            name:'变形钢筋(直)',
            type:'line',
            stack: '总量',
            data:databianxingzhi,
            itemStyle: {
        normal: {
            color: '#8370D8'
        }}
        },
        {
            name:'变形钢筋(圆)',
            type:'line',
            stack: '总量',
            data:databianxingyuan,
            itemStyle: {
        normal: {
            color: '#3216B0'
        }}
        }
    ]
            });
        },function(response){
            console.log(response);
        })
 


 
   
  }
}

</script>

<style scoped>
.charttitle{
    text-align: center;
}
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.chart {
     width: 40%;
     height: 400px;
     display: inline-block;
 }
 .amountchart{
     width: 40%;
     height: 400px;
    display: inline-block;
 }
 .home{
     width: 100%;
     margin-left: 20px;
 }
</style>
