<template>
  <div>
    <el-form class="form food_form" :rules="rules" ref="ruleForm" :model="orderdetail" :inline="true">
      <el-form-item label="工 程 名 字" class="fontcolor temipt" prop="workName">
  
        <el-input v-model="orderdetail.workName" placeholder="工 程 名 字" required=true class="forminput"></el-input>
  
      </el-form-item>
      <el-form-item label="公 司 名 字" class="fontcolor temipt" prop="companyName">
        <div class="forminput">
          <el-input v-model="orderdetail.companyName" placeholder="公 司 名 字"></el-input>
        </div>
      </el-form-item>
      <el-form-item label="钢 筋 简 图" class="fontcolor temipt">
        <el-select v-model="value" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="数 量" class="fontcolor temipt" prop="account1">
        <el-input-number v-model="orderdetail.account" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item label="单 价" class="fontcolor temipt " prop="price">
        <div class="forminput pricewidth">
          <el-input v-model="orderdetail.price"></el-input>
        </div>
      </el-form-item>
      <el-form-item label="总 价" class="fontcolor temipt">
        <p>{{totalPrice}}</p>
      </el-form-item>
      <el-form-item label="到 期 日" class="fontcolor temipt" prop="date2" required>
        <el-date-picker v-model="orderdetail.date1" align="right" type="date" placeholder="选择日期" :picker-options="pickerOptions0">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true" style="margin-bottom: 10px;">添加规格</el-button>
        <el-table :data="orderdetail.specs" style="margin-bottom: 20px; width:800px">
          <el-table-column label="名称">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.matrialname }}</span>
            </template>
          </el-table-column>
          <el-table-column label="直径">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.diameter}}</span>
            </template>
          </el-table-column>
          <el-table-column label="数量">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.amount}}</span>
            </template>
          </el-table-column>
          <el-table-column label="长度">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.length}}</span>
            </template>
          </el-table-column>
          <el-table-column label="重量">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.weight}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template scope="scope">
              <el-button size="small" type="danger" @click="handleDelete(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item label-width="80px" class="btnblock">
        <el-button type="primary" @click="onSubmit('ruleForm')">立即创建</el-button>
        <el-button @click="cancelAdd">取消</el-button>
      </el-form-item>
  
    </el-form>
  
    <el-dialog title="添加规格" v-model="dialogFormVisible">
      <el-form :model="specsForm">
        <el-form-item label="名称">
          <el-input v-model="specsForm.matrialname" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="直径">
          <el-input-number v-model="specsForm.diameter" :min="0" :max="100"></el-input-number>
        </el-form-item>
        <el-form-item label="数量">
          <el-input-number v-model="specsForm.amount" :min="0" :max="10000"></el-input-number>
        </el-form-item>
        <el-form-item label="长度">
          <el-input-number v-model="specsForm.length" :min="0" :max="10000"></el-input-number>
        </el-form-item>
        <el-form-item label="重量">
          <el-input-number v-model="specsForm.weight" :min="0" :max="10000"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addspecs">确 定</el-button>
      </div>
    </el-dialog>
  
  </div>
</template>
<script>
export default {
  data() {
    return {
      /*test*/
      options: [{
        value: '选项1',
        label: '黄金糕'
      }, {
        value: '选项2',
        label: '双皮奶'
      }, {
        value: '选项3',
        label: '蚵仔煎'
      }, {
        value: '选项4',
        label: '龙须面'
      }, {
        value: '选项5',
        label: '北京烤鸭'
      }],
      value: '',
      dialogFormVisible: false,
      specsForm: {
        matrialname: '',
        diameter: 0,
        amount: 0,
        weight: 0,
        length: 0,
      },
      orderdetail: {
        workName: '',
        companyName: '',
        pic: '',
        account: '',
        price: '',
        date1: '',
        totalPrice: '',
        specs: [{
          matrialname: '',
          diameter: 0,
          amount: 0,
          weight: 0,
          length: 0,
        }
        ],
      },
      date2: '',
      account1: '',
      // workName:'',
      pickerOptions0: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;

        },
      },

      rules: {
        workName: [
          { required: true, message: '请输入工程名称', trigger: 'blur' },
          { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }
        ],
        companyName: [
          { required: true, message: '请输入公司名称', trigger: 'blur' },
          { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }
        ],
        account: [
          { required: true, message: '请输入数量', trigger: 'blur' }
        ],
        price: [{
          required: true, message: '请输入单价', trigger: 'blur'
        }],
        date1: [{ required: true, message: '请选择到期时间', trigger: 'change' }]
      }
    }
  },
  computed: {
    totalPrice: function () {
      this.orderdetail.totalPrice = Number(this.orderdetail.account) * Number(this.orderdetail.price);
      return this.orderdetail.totalPrice.toString();
    }
  },
  methods: {
    handleDelete(index){
				this.orderdetail.specs.splice(index, 1);
			},
    addspecs: function () {
      //缺货提醒
      this.$notify.warning({
          title: '警告',
          message: '材料库存不足，请及时补充',
          offset: 100
        });
      //查库存记录是否足够
      let url=""
      this.$http.get(url+'?matrialname='+this.specsForm.name+'&diameter='+this.specsForm.diameter+'&=length'+this.specsForm.length,{
                    headers: {},
                    emulateJSON: true
                }).then(function (response) {
                   console.log(response.data);
                   if (response.body != null & response.body.length > 0) {
                   if(this.specsForm.amout>response.data[0].amount){
                    
                    
                   }
            
           
                }
                 else {
                    // alert('请输入正确的用户名和密码！！！');
   
         
                   
                   
                    
                }
                }, function (response) {
                    console.log(response)
                })
               
      let objtemp={};
       objtemp.matrialname=this.specsForm.matrialname,
        objtemp.diameter=this.specsForm.diameter,
        objtemp.amount=this.specsForm.amount,
        objtemp.weight=this.specsForm.weight,
        objtemp.length=this.specsForm.length,
      this.orderdetail.specs.push(objtemp);
      console.log(this.orderdetail.specs)

      this.dialogFormVisible = false;

    },
    onSubmit: function (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http.post(servicerurl+'/order', this.orderdetail, {
            headers: {},
            emulateJSON: true
          }).then(function (response) {
            //getagain,save in total_localstorage
            this.$http.get(servicerurl+'/order', {}, {
              headers: {},
              emulateJSON: true
            }).then(function (response) {
              console.log(response.data);
              localStorage.setItem('orderList', JSON.stringify(response.data));

              this.$router.push({ path: '/order/listMangerment' })
            })
            console.log(response.data);
          }, function (response) {
            console.log(response);
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    cancelAdd: function () {
      this.$router.push({ path: '/order/listMangerment' })
    }
  }
}
</script>
<style>
.btnblock {
  display: block !important;
}

.pricewidth {
  width: 200px !important;
  display: inline-block;
}

.form {
  min-width: 350px;
  margin-bottom: 30px;
  margin-left: 30px;
  background-color: white;
  padding: 10px;

  margin-top: 40px;
}

.food_form {
  border: 1px solid #eaeefb;
  padding: 10px 10px 0;
}

.forminput {
  width: 400px;
  display: inline-block;
  margin-top: 10px;
}

.fontcolor>label {
  color: #537298 !important;
}

.itemipt {
  display: inline-block !important;
}

.formStyle {
  margin-left: 30px;
  background-color: white;
  padding: 10px;
  border-radius: 15px;
  margin-top: 40px;
}
</style>
