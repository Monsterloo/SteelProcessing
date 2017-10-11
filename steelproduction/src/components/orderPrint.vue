<template>
    <div class="clear">
        <el-card class="box-card">
            <el-form v-model="tableData2" >
                <el-form-item label="订单编号" style="color:#0085ef!important">
                    <span style="margin-left: 10px;">{{ tableData2.oid }}</span>
                </el-form-item>
                <el-form-item label="工程名称" style="color:#0085ef!important">
                    <span>{{ tableData2.projectName}}</span>
                </el-form-item>
                <el-form-item label="公司名称" style="color:#0085ef!important">
                    <span> {{ tableData2.client.cname}}</span>
                </el-form-item>
                <el-form-item label="到期日" style="color:#0085ef!important">
                    <span>{{ tableData2.dueDate}}</span>
                </el-form-item>
                <el-form-item label="总价" style="color:#0085ef!important">
                    <span>{{ tableData2.totalPrice}}</span>
                </el-form-item>
               
               <el-table :data="tableData2.orderDetailList" style="margin-bottom: 20px; width:800px">
          <el-table-column label="直径">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.diametre}}cm</span>
            </template>
          </el-table-column>
          <el-table-column label="数量">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.count}}</span>
            </template>
          </el-table-column>
          
        
          <el-table-column label="重量">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.weight}}cm/kg</span>
            </template>
          </el-table-column>

           <el-table-column label="钢筋种类">
            <template scope="scope">
              <span v-if='scope.row.steelCategory=="1"'> 光 圆 钢 筋</span>
              <span v-if='scope.row.steelCategory=="2"'> 带 肋 钢 筋</span>
              <span v-if='scope.row.steelCategory=="3"'> 扭 转 钢 筋</span>
              <span v-if='scope.row.steelCategory=="4"'> 变 形 钢 筋 (直)</span>
              <span v-if='scope.row.steelCategory=="5"'> 变 形 钢 筋 (圆)</span>
            </template>
          </el-table-column>
          <el-table-column label="简图" width="180">
            <template scope="scope">
             <img class="avatar" :src='scope.row.picsrc' style="height:36px;">
            </template>
          </el-table-column>
          <el-table-column label="总价">
            <template scope="scope">
            <span>{{scope.row.totalPrice}}</span>
            </template>
          </el-table-column>
         
        </el-table>
               

            </el-form>
            <el-button @click="print" class="not-print" type="primary" style="margin:10px">确定打印</el-button>
            <el-button @click="back" class="not-print"  style="margin:10px">返回</el-button>

        </el-card>
    </div>
</template>



<script>
export default {
    data() {
        return {
            tableData2: {

            },
        };
    },
    mounted: function() {
       

        this.tableData2 = JSON.parse(localStorage.getItem('printTemp') || []);
        for(let i=0;i<this.tableData2.orderDetailList.length;i++){
            this.tableData2.orderDetailList[i].picsrc=require('../assets/addimg/' + this.tableData2.orderDetailList[i].sid + '.jpg');
        }
       
      
    },
    methods: {
        print: function() {
            window.print();
        },
        back:function(){
            this.$router.push({ path: '/order/listMangerment' });
        }

    }
};
</script>

<style scoped>

@media print {
    .not-print {
        opacity: 0
    }
}

.clear {
    clear: both
}
.tabelprint{
    margin: 0,auto
}
.box-card {
    width: 670px;
    margin: 10px auto;
}
</style>
