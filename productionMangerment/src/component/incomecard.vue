<template>
<el-row class="margin">
  <el-col :span="8" v-for="(o, index) in 4" :key="o" >
    <el-card :body-style="{ padding: '0px' }" class="box">
      <div style="padding: 14px;" >
        <p>公司名称</p>
        <p>应收金额</p>
        <p>已收金额</p>
        <div class="bottom clearfix">
          <time class="time">{{ currentDate }}</time>
          <el-button type="text" class="button">查看详情</el-button>
        </div>
      </div>
    </el-card>
  </el-col>
</el-row>


</template>

<style>
.box{
    margin-left: 5px!important;
    margin-bottom: 5px !important;
}
.margin{
    margin-top: 5px;
    margin-left: 5px;
}
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
</style>

<script>
export default {
  data() {
    return {
      currentDate: new Date()
    };
  }
}
</script>