<template>
 
      <el-col :span="4" class="sidebar" style="min-height:100%">
    <el-menu mode="vertical"  router=true theme="dark" class="sidebar">

        <el-submenu index="/order">
        <template slot="title"><i class="el-icon-document"></i>订 单 管 理</template>
         <el-menu-item index="/order/listMangerment">订 单 管 理</el-menu-item>
    <el-menu-item index="/order/sale">客 户 销 售 分 析</el-menu-item>
    <el-menu-item index="/order/income">客 户 应 收 查 询</el-menu-item>
        </el-submenu>
        
         <!-- <el-menu-item index="/system">系 统 管 理</el-menu-item>  -->
         <el-submenu index="/system" v-show="disablemode">
        <template slot="title"><i class="el-icon-setting"></i>系 统 管 理</template>
         <!-- <el-menu-item index="/system/addadmin">添 加 管 理 员</el-menu-item> -->
    <el-menu-item index="/system/adminMangerment">管 理 员 管 理</el-menu-item>
    <el-menu-item index="/system/mypage">我 的 设 置</el-menu-item>
        </el-submenu>      

        <el-submenu index="/product">
          <template slot="title"><i class="el-icon-message"></i>库 存 管 理</template>
            <el-menu-item index="/product/in">入 库 管 理</el-menu-item>
    <el-menu-item index="/product/out">出 库 管 理</el-menu-item>
        </el-submenu>
        <el-menu-item index="/menber"><i class="el-icon-information
"></i>会员管理</el-menu-item>
    </el-menu>
  </el-col>

</template>

<script>
export default {
  data(){
    return{
     disablemode:false
    }
  },
  created:function(){
       var admin=JSON.parse(localStorage.getItem('st_admin'));
       if(admin[0].access!=null){
         console.log(admin[0].access)
        if(admin[0].access==1){
          this.disablemode=true;
        }
        else{
          this.disablemode=false
        }
       
     }

   },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>

<style>
  .sidebar{
      height:800px !important; 
 } 
    .cleanpadding{
        padding-left: 0px !important;
    }
</style>
