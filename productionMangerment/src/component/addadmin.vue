<template>
<el-form class="formStyle" :rules="rules" ref="ruleForm" :model="admin">
 
    <el-form-item label="管理员名字" class="fontcolor" prop='name'>
     <div class="forminput">
    <el-input v-model="admin.name" placeholder="管 理 员 名 字"></el-input>
    </div> 
  </el-form-item>
  
   <el-form-item label="请输入密码" class="fontcolor" prop='password'>
     <div class="forminput">
    <el-input v-model="admin.password" placeholder="请输入密码" type='password'></el-input>
    </div> 
  </el-form-item>
    <el-form-item label="请再次输入密码" class="fontcolor" prop='password1'>
     <div class="forminput">
    <el-input v-model="admin.password1" placeholder="请再次输入密码" type='password'></el-input>
    </div> 
  </el-form-item>
  
   <el-form-item label="权限级别" class="fontcolor" >
     <div class="forminput">
<el-select v-model="admin.access" placeholder="请选择" >
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>    </div> 
  </el-form-item>
        <el-form-item label-width="80px">
    <el-button type="primary" @click="onSubmit('ruleForm')">立即添加</el-button>
    <el-button @click="cancelAdd">取消</el-button>
  </el-form-item>
  </el-form>  
</template>

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

<script>
export default {
 data(){
   return{
     admin:{
          name:'',
          password:"",
          access:0,/*0 admin 1 super*/
          password1:''
     },
     options:[{
          value: 1,
          label: '超级管理员'
     },{
       value:0,
       label:'普通管理员'
     }],
        rules:{
         name:[{
           required: true, message: '请输入管理员名称', trigger: 'blur' 
         },
         {  min:4,max: 16, message: '长度少于16 个字符', trigger: 'blur' }],
        password:[
          {required: true, message: '请输入密码', trigger: 'blur' },
          {min:8,max: 15, message: '长度在8至15 个字符', trigger: 'blur' },
        
        ],
        password1:[
          {required: true, message: '请输入密码', trigger: 'blur' },
          {min:8,max: 15, message: '长度在8至15 个字符', trigger: 'blur' },
          {
            validator:(rule,value,callback)=>{
                if(value != this.admin.password){
                    callback(new Error("请输入一致的密码"));
                }else{
                    callback();
                }
           },
           trigger:'blur'
          }
        ]
         
         
        }
   }
 },
 
 methods:{
   onSubmit:function(formName){
    this.$refs[formName].validate((valid) => {
          if (valid) {
            let newadmin={
               name:this.admin.name,
          password:this.admin.password,
          access:this.admin.access,/*0 admin 1 super*/
      
            }
           this.$http.post(this.servicerurl+'/user',newadmin,{
      headers:{},
      emulateJSON:true
    }).then(function(response){
       //getagain,save in total_localstorage
      this.$http.get(this.servicerurl+'/user',{},{
      headers:{},
      emulateJSON:true
    }).then(function(response){
      console.log(response.data); 
      this.$router.push({ path: '/system/adminMangerment'})
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
    this.$router.push({ path: '/system/adminMangerment' })
  }
 } 
}
</script>