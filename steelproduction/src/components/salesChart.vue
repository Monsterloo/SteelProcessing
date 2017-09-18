<template>
<div>
    <!-- v-model="input" -->
<el-input  placeholder="请输入公司名称" class="input_chart"></el-input>
<el-button type="primary" class="addbtn not-print" >查 询 <i class="el-icon-search"></i></el-button>
 <el-table :data="tableData" stripe style="width: 100%" class="inchart">
    <el-table-column
      prop="date"
      label="订单编号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="name"
      label="订单日期"
      width="180">
    </el-table-column>
    <el-table-column
      prop="address"
      label="公司名称">
    </el-table-column>
     <el-table-column
      prop="address"
      label="订单金额">
    </el-table-column>
  </el-table>
   <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChange" v-on:current-change="pageIndexChange" class="pagination not-print">
 </el-pagination>
</div>
</template>
 <style scoped>
 .addbtn {
    background-color: #ff9800 !important;
    float: right;
    margin-top: 20px;
    width: 120px;
    margin-right: 20px;
}
.input_chart{
    width: 500px;
    margin-top: 20px;
    margin-left: 132px;

}
.inchart{
    margin-left: 20px;
    margin-top: 20px;
}
.pagination {
    margin-top: 30px;
    margin-left: 40%;
}
 </style>
 <script>
 export default {
     data() {
         return{
             pageIndex: 0,
             pageSize: 10,
             total: 0,
             pageSizes: [10, 20, 50, 100],
             tableData:[],
         };
     },
     methods: {
         SizeChange: function(pageSize) {
             this.pageSize = pageSize;
            // this.fetchData();
         },
         pageIndexChange: function(pageIndex) {
             this.pageIndex = pageIndex-1;
            // this.fetchData();
         },
     }
 };
 </script>
 