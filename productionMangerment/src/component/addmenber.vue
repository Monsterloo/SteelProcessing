<template>
  <el-form class="formStyle" :rules="rules" ref="ruleForm" :model="menbertail">
  
    <el-form-item label="公 司 名 字" class="fontcolor" prop="companyname">
      <div class="forminput">
        <el-input v-model="menbertail.companyname" placeholder="公 司 名 字"></el-input>
      </div>
    </el-form-item>
  
    <el-form-item label="联 系 方 式" class="fontcolor" prop="companycontact">
      <div class="forminput">
        <el-input v-model="menbertail.companycontact" placeholder="联 系 方 式"></el-input>
      </div>
    </el-form-item>
        <el-form-item label="公 司 地 址" class="fontcolor" prop="companyaddress2">
      <div class="forminput">
        <v-distpicker @selected="onSelected"></v-distpicker>
         <br>
        <el-input v-model="menbertail.companyaddress2" placeholder="公 司 地 址"></el-input>
      </div>
    </el-form-item>
         <el-form-item label="法 人 姓 名" class="fontcolor" prop="ownername">
      <div class="forminput">
        <el-input v-model="menbertail.ownername" placeholder="法 人 姓 名"></el-input>
      </div>
    </el-form-item>
  
    <el-form-item label-width="80px">
      <el-button type="primary" @click="onSubmit('ruleForm')">立即创建</el-button>
      <el-button @click="cancelAdd">取消</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      menbertail: {
        companyname: '',
        companycontact: '',
        companyaddress: '',
        ownername: '',
        companyaddress1:'',
        companyaddress2: '',
      },
      rules: {
        companyname: [
                    { required: true, message: '请输入公司名称', trigger: 'blur' },
                    { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }
                ],
                companyaddress2: [{ required: true, message: '请输入公司详细地址', trigger: 'blur' },
                { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }],
                ownername: [{ required: true, message: '请输入公司地址', trigger: 'blur' }],
                companycontact: [
                    { required: true, message: '请输入负责人联系方式', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            if (/^1[34578]\d{9}$/.test(value) == false) {
                                callback(new Error("请输入正确的手机号码"));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur'
                    }
        ]

      }
    }
  },

  methods: {
     onSelected: function(data) {
            this.menbertail.companyaddress1 = data.province.value + data.city.value + data.area.value
            console.log(this.menbertail.companyaddress1)
        },
    onSubmit: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.menbertail.companyaddress=this.menbertail.companyaddress1+this.menbertail.companyaddress2;
          this.$http.post(this.servicerurl+'/menber', this.menbertail, {
            headers: {},
            emulateJSON: true
          }).then(function (response) {
            //getagain,save in total_localstorage
            this.$http.get(this.servicerurl+'/menber', {}, {
              headers: {},
              emulateJSON: true
            }).then(function (response) {
              console.log(response.data);
              localStorage.setItem('menberList', JSON.stringify(response.data));
              this.$message({
                                showClose: true,
                                message: '添加成功',
                                type: 'success'
                            })
              this.$router.push({ path: '/menber' })
            })
            console.log(response.data);
          }, function (response) {
            console.log(response);
          })
        } else {
          console.log('error submit!!');
          this.$message({
                                showClose: true,
                                message: '添加失败',
                                type: 'warning'
                            })
          return false;
        }
      });
    },
    cancelAdd: function () {
      this.$router.push({ path: '/menber' })
    }
  }
}
</script>
<style>
.forminput {
  width: 500px;
  display: inline-block;
  margin-top: 10px;
}

.fontcolor>label {
  color: black !important;
}

.formStyle {
  margin-left: 30px;
  background-color: white;
  padding: 10px;
  border-radius: 15px;
  margin-top: 40px;
}
</style>
