
<template id="#login" class="clear ">
    <el-form :model="form" :rules="rules" label-width="0px" class="center loginForm background clear" >
        <el-form-item class="loginleft fl"><p class="loginfontTit marginTop30">welcome！</p> <p class="loginfontSub">钢筋订单管理系统</p></el-form-item>
        <el-form-item  prop="name" class="fr logininput marginTop30">
            <el-input v-model="form.name"  placeholder="请输入账号" ></el-input>
        </el-form-item>
        <el-form-item  prop="password" class="logininput fr">
            <el-input v-model="form.password"  type="password" placeholder="请输入密码" @keyup.enter="submitForm(form)"></el-input>
        </el-form-item>
        <el-form-item  class="loginbtn fr ">
            <el-button type="primary" class="btnWidth" @click="submitForm(form)"  size="large">登 录</el-button>
       </el-form-item>
    </el-form>
</template>



<script>
export default {
    data() {
        return {
            form: {
                name: '',
                password: '',
                access:0,/*0 admin 1 super*/
            },
            rules:{
                name:{
                    required: true, message: '请输入用户名', trigger: 'blur'
                },
                password:{
                    required: true, message: '请输入密码', trigger: 'blur'
                }
            }
        }
    },
    created:function(){
           document.body.setAttribute("class","bodylogin");
    },
    methods: {
        submitForm: function (formname) {
             let loginparam={
                 name:this.form.name,
                 password:this.form.password
             }
            console.log(this.form.password);
            this.$http.get(this.servicerurl+'/user?name='+this.form.name+'&password='+this.form.password).then((response) => {
           //this.$http.post(this.servicerurl+'/user',loginparam).then((response)=>{
             const self=this;
                //注意这里是个难点，Vuex与Vue-Resource结合使用。 
                if (response.body != null & response.body.length > 0) {
                //localStorage.setItem('st_username',self.form.name);
                 this.$message({
                 showClose: true,
                 message: '登录成功',
                 type: 'success'
                 })
                self.form=response.data;
                localStorage.setItem('st_admin',JSON.stringify(self.form));
                    this.name = ''
                    this.password = ''
                    this.$router.push({ path: '/index' })
                }
                 else {
                    // alert('请输入正确的用户名和密码！！！');
                    
                 this.$message({
                 showClose: true,
                 message: '请输入正确的用户名和密码',
                type: 'error'
                 })
                    this.name = ''
                    this.password = ''
                    this.access=0
                }
            }).then((response) => {
                console.log(response)
            })
        }
    }
}
</script>

<style>
.loginfontSub{
    color: white;
    font-size: 2em;
    font-weight: bold;
    font-family: "Hiragino Sans GB";
    margin-left: 50px;
}
.loginfontTit{
    color: white;
    font-size: 3em;
    font-weight: bold;
    font-family: "Arial";
    margin-left: 53px;
}
.bodylogin{
    background-color: #3891f6;
}
.loginleft{
    display: inline-block;
background-image: -webkit-linear-gradient(top, #4b88f6, #f2f2f2);
background-image: -moz-linear-gradient(top, #4b88f6, #f2f2f2);
background-image: linear-gradient(-45deg, #4b88f6, #f2f2f2);
width: 48% !important;
height: 400px !important;
margin-bottom: 0px !important;
}
.marginTop30{
    margin-top: 100px;
}
.background{
    box-shadow: 4px 4px 10px #dadbdc;
  background-color: white !important;
  
}
.loginbtn{
    width: 40% !important;
   
}
.center{
    margin: 0 auto;
}
.loginForm{
    width: 58%;
    margin-top: 100px;
    height: 400px;
    
}
.logininput{
    width: 50%;
    margin-right: 10px;
  
}
.btnWidth{
    width: 75% !important;
    margin-right: 100px;
    
}

.fl {
    float: left !important;
}
.fr{
    float: right !important;
}
.clear {
    clear: both;
}
</style>
