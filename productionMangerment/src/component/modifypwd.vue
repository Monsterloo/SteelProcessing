<template>
    <div>
        <el-form class="formStyle1" :model="admininfo">
    
            <el-form-item label="管理员名字" class="fontcolor">
                <div class="forminput">
                    <!-- <el-input v-model="admininfo.name" placeholder="管 理 员 名 字"></el-input> -->
                    <p>{{admininfo.name}}</p>
                </div>
            </el-form-item>
    
            <el-form-item label="管理员联系方式" class="fontcolor">
                <div class="forminput">
                    <!-- <el-input v-model="admininfo.name" placeholder="管 理 员 名 字"></el-input> -->
                    <p>{{admininfo.contact}}</p>
                </div>
            </el-form-item>
    
            <el-form-item label="管理员权限级别" class="fontcolor">
                <div class="forminput">
                    <p v-if='admininfo.access==0'>一般管理员</p>
                    <p v-if='admininfo.access==1'>超级管理员</p>
                </div>
            </el-form-item>
    
            <el-form-item  class="fontcolor" prop='name'>
                <el-button type="primary" class="btnp" @click="handleEdit">修改</el-button>
            </el-form-item>
        </el-form>
        <el-dialog title="修改" v-model="dialogVisible" size="tiny">
            <el-form :model="admininfo" :rules="rules" ref="ruleForm">
                <el-form-item prop='password'>
                    <el-input v-model="admininfo.password" type='password' class="inputp" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item prop='password1'>
                    <el-input v-model="admininfo.password1" type='password' class="inputp" placeholder="请再次输入密码"></el-input>    
                </el-form-item>
              
                <el-button type="primary" class="btnp" @click="confirmmodify('ruleForm')">修改密码</el-button>
                <el-button class="btnp" @click="dialogVisible=false">取消</el-button>
            </el-form>
        </el-dialog>
    </div>
</template>

<style>
.fontcolor>label {
    color: black !important;
}

.btnp {
    margin-left: 139px;
}

.inputp {
    margin-bottom: 10px;
}

.formStyle1 {
    width: 40%;
    margin-left: 30%;
    background-color: white;
    padding: 10px;
    border-radius: 15px;
    margin-top: 40px;
    padding-left: 30px !important;
}
</style>

<script>
export default {
    data() {
        return {

            dialogVisible: false,
            rules: {
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 8, max: 15, message: '长度在8至15 个字符', trigger: 'blur' },

                ],
                password1: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 8, max: 15, message: '长度在8至15 个字符', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            if (value != this.admininfo.password) {
                                callback(new Error("请输入一致的密码"));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur'
                    }
                ]
            },
            admininfo: {
                name: "",
                password: "",
                access: 1,
             
            }
        }
    },
    mounted: function () {


        var code = JSON.parse(localStorage.getItem('st_admin'));
        code[0].password1 = "";
        console.log(code[0]);
        this.admininfo = code[0];


    },
    methods: {
        handleEdit: function () {
            this.dialogVisible = true;
            this.admininfo.password = '';
        },
        confirmmodify: function (formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.dialogFormVisible = false;
                    let newadmin = {
                        name: this.admininfo.name,
                        password: this.admininfo.password,
                        access: this.admininfo.access,/*0 admin 1 super*/

                    }
                    this.$http.put(this.servicerurl+'/user' + '/' + this.admininfo.id, newadmin, {
                        headers: {},
                        emulateJSON: true
                    }).then(function (response) {
                        this.$message({
                            showClose: true,
                            message: '修改密码成功',
                            type: 'success'
                        })
                        //getagain,save in total_localstorage
                        // this.$http.get(this.servicerurl+'/user' + '/' + this.admininfo.id, {}, {
                        //   headers: {},
                        // emulateJSON: true
                        // }).then(function (response) {
                        //   console.log(response.data);
                        //localStorage.setItem('st_admin',JSON.stringify(response.data))
                        this.dialogVisible = false;
                        //})
                        console.log(response.data);
                    }, function (response) {
                        console.log(response);
                    })
                }
                else {
                    this.$message({
                        showClose: true,
                        message: '修改密码失败',
                        type: 'warning'
                    })
                    console.log('error submit!!');
                    return false;
                }
            });
        }
    }
}
</script>
