<template>
<el-form class="formStyle" :rules="rules" ref="ruleForm" :model="menberdetail1">
   <el-form-item label="公 司 编 号" class="fontcolor" >
    <p>{{menberdetail1.id}}</p>
  </el-form-item>

     <el-form-item label="公 司 名 称" class="fontcolor" >
    <p>{{menberdetail1.companyname}}</p>
  </el-form-item>

   <el-form-item label="联 系 方 式" class="fontcolor" prop="contact">
     <div class="forminput">
    <el-input v-model="menberdetail1.contact"></el-input>
     </div>       
  </el-form-item>
 
 
        <el-form-item label-width="80px">
    <el-button type="primary" @click="onModify('ruleForm')">修改</el-button>
    <el-button @click="cancelAdd">取消</el-button>
  </el-form-item>
  </el-form>  
</template>

<script>
var STORAGE_KEY='menberTemp';
var menberStorageMo={
         save:function(data){
           localStorage.setItem(STORAGE_KEY,JSON.stringify(data))
             },
        fetch:function(){
            return JSON.parse(localStorage.getItem(STORAGE_KEY));
        }
             };
export default {
  data(){
      return{
          menberdetail1:menberStorageMo.fetch(),
          
        }

       

      
  },

 methods:{
   onModify:function(formName){
                 this.$refs[formName].validate((valid) => {
          if (valid) {
           this.$http.put(this.servicerurl+'/menber'+'/'+this.menberdetail1.id,this.menberdetail1,{
      headers:{},
      emulateJSON:true
    }).then(function(response){
      localStorage.setItem('menberTemp',JSON.stringify(response.data));
      //getagain,save in total_localstorage
      this.$http.get(this.servicerurl+'/menber',{},{
      headers:{},
      emulateJSON:true
    }).then(function(response){
      console.log(response.data);
       localStorage.setItem('menberList',JSON.stringify(response.data));
      
      this.$router.push({ path: '/menber'})
    })
      
      console.log(response.data);
    },function(response){
      console.log(response);
    }) 
          } else {
            console.log('error submit!!');
            return false;
          }
        });
   },
   
      cancelAdd:function(){
    this.$router.push({ path: '/menber' })
  },
 },
   
  
  
}
</script>

<style>
.forminput{
  width:500px;
  display: inline-block;
  margin-top: 10px;
}
.fontcolor > label{
  color: black !important;
}
.formStyle{
 margin-left: 30px;
 background-color: white;
 padding: 10px;
 border-radius: 15px;
 margin-top: 40px;
}
</style>