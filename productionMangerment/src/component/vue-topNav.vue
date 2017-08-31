<template>
 <div class="header">
        <div class="logo">
            <el-breadcrumb separator="-" class="breakcrumb">
  <el-breadcrumb-item :to="{ path: '/' }" class="breakcrumbfontcolor">首页</el-breadcrumb-item>
<el-breadcrumb-item class="breakcrumbfontcolor">{{path}}</el-breadcrumb-item>
</el-breadcrumb>
        </div>
        <div class="user-info">
            <el-dropdown trigger="click" >
                <span class="el-dropdown-link">
                    {{username}}
                </span>
                <!-- <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="loginout">退出</el-dropdown-item>
                </el-dropdown-menu> -->
                <el-dropdown @command="handleCommand">
  <el-button type="info">
    更多<i class="el-icon-caret-bottom el-icon--right"></i>
  </el-button>
  <el-dropdown-menu slot="dropdown">
     <el-dropdown-item command="loginout">退出</el-dropdown-item>
    <el-dropdown-item command='setting'>设置</el-dropdown-item>
   
  </el-dropdown-menu>
</el-dropdown>
            </el-dropdown>
        </div>
    </div>
</template>


<style>
.breakcrumb{
    font-size: 14px !important;
    margin-top: 20px;
   
}
.logo{
    margin-left: 20px;
}
.el-breadcrumb__item__inner, .el-breadcrumb__item__inner a{
    color: white !important;
}
.fr{
  float: right !important;

}
.clear{
  clear:both;
}
</style>




<script>
export default {
 
  data () {
    return {
    
    }
  },
  computed:{
     username:function(){
       let admin = JSON.parse(localStorage.getItem('st_admin'));
        console.log(admin[0].name)
         
    return  admin[0].name  
     },
     path:function(){
         if(this.$route.name!=null){
         //console.log(code[1].path)
         console.log(this.$route.name)
         return this.$route.name
         }
     }
  },
  methods:{
      

      handleCommand(command) {
          if(command=="loginout")
          {
                localStorage.removeItem('st_admin')
              this.$router.push({ path: '/login' })
            
          }
          else if(command=='setting'){
              this.$router.push({path:'/system/mypage'})
          }
        
      }
            
        }  
}
</script>

<style scoped>
    .header {
        position: relative;
        box-sizing: border-box;
        width: 104%;
        height: 54px;
        font-size: 22px;
        line-height: 54px;
        color: #fff;
        background-color:#0971f1;
    }
    .header .logo{
        float: left;
        width:250px;
        text-align: center;
    }
    .user-info {
        float: right;
        padding-right: 50px;
        font-size: 16px;
        color: #fff;
    }
    .user-info .el-dropdown-link{
        position: relative;
        display: inline-block;
        padding-left: 50px;
        color: #fff;
        cursor: pointer;
        vertical-align: middle;
    }
    .user-info .user-logo{
        position: absolute;
        left:0;
        top:15px;
        width:40px;
        height:40px;
        border-radius: 50%;
    }
    .el-dropdown-menu__item{
        text-align: center;
    }
</style>