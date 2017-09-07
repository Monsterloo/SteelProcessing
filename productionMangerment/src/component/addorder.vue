<template>
  <div>
    <el-form class="form food_form" :rules="rules" ref="ruleForm" :model="orderdetail" :inline="true">
      <el-form-item label="工 程 名 字" class="fontcolor temipt" prop="workname">
        <el-input v-model="orderdetail.workname" placeholder="工 程 名 字" required=true class="forminput"></el-input>
      </el-form-item>
      <el-form-item label="公 司 名 字" class="fontcolor temipt" required>
        <el-select v-model="companystring" placeholder="请选择" filterable @change="changemethod">
          <el-option v-for="item in options4" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="钢 筋 直 径" class="fontcolor temipt" required>
       
          <el-select v-model="orderdetail.dim" placeholder="请选择">
            <el-option v-for="item in optionsDim" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
  
      </el-form-item>
      <el-form-item label="钢 筋 简 图" class="fontcolor temipt" prop="picid" >
        <el-select v-model="orderdetail.picid" placeholder="请选择" @change="changeSelection">
          <el-option v-for="item in pics" :key="item.id" :label="item.id" :value="item.id">
            <img class="avatar" :src="item.src" style="height:36px">
          </el-option>
        </el-select>
        <img class="avatar" :src='orderdetail.picsrc' style="height:36px">
      </el-form-item>

      <el-form-item label="单 价" class="fontcolor temipt " prop="price">
        <div class="forminput pricewidth">
          <el-input v-model="orderdetail.price"></el-input>
        </div>
      </el-form-item>

      <el-form-item label="到 期 日" class="fontcolor temipt" prop="date2" required>
        <el-date-picker v-model="orderdetail.date1" align="right" type="date" placeholder="选择日期" :picker-options="pickerOptions0">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="部 件 长 度" class="fontcolor temipt" required>
        <div v-if="varNum==1">
          <span class="length_input">A</span>
          <el-input style="width:70px" v-model="orderdetail.A" ></el-input>
        </div>
        <div v-if="varNum==2">
          <span class="length_input">A</span>
          <el-input style="width:70px" v-model="orderdetail.A" ></el-input>
          <span class="length_input">B</span>
          <el-input style="width:70px" v-model="orderdetail.B" ></el-input>
        </div>
        <div v-if="varNum==3">
          <span class="length_input">A</span>
          <el-input style="width:70px" v-model="orderdetail.A" ></el-input>
          <span class="length_input">B</span>
          <el-input style="width:70px" v-model="orderdetail.B" ></el-input>
          <span class="length_input">C</span>
          <el-input style="width:70px" v-model="orderdetail.C" ></el-input>
        </div>
        <div v-if="varNum==4">
          <span class="length_input">A</span>
          <el-input style="width:70px" v-model="orderdetail.A" ></el-input>
          <span class="length_input">B</span>
          <el-input style="width:70px" v-model="orderdetail.B" ></el-input>
          <span class="length_input">C</span>
          <el-input style="width:70px" v-model="orderdetail.C" ></el-input>
          <span class="length_input">D</span>
          <el-input style="width:70px" v-model="orderdetail.D" ></el-input>
        </div>
      </el-form-item>
      <el-form-item label="数 量" class="fontcolor temipt" required>
        <el-input-number v-model="orderdetail.amount" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item label="总 长 度" class="fontcolor temipt">
        <!--cm-->
        <p>{{ totalLength }}cm</p>
       
      </el-form-item>
      <el-form-item label="总 重 量" class="fontcolor temipt">
        <p>{{orderdetail.weight}}kg</p>
      </el-form-item>
      <el-form-item label="总 价" class="fontcolor temipt">
        <p>¥{{totalPrice}}</p>
      </el-form-item>
      <el-form-item label-width="80px" class="btnblock">
        <el-button type="primary" @click="onSubmit('ruleForm')">立即创建</el-button>
        <el-button @click="cancelAdd">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      options4: [],
      optionsDim: [{
        value: 0.261,/*cm*/
        label: 'ø6.5'
      }, {
        value: 0.425,
        label: 'ø8.3'
      }, {
        value: 0.617,
        label: 'ø10'
      }, {
        value: 0.888,
        label: 'ø12'
      }, {
        value: 1.21,
        label: 'ø14'
      }, {
        value: 1.58,
        label: 'ø16'
      }, {
        value: 2,
        label: 'ø18'
      }, {
        value: 2.47,
        label: 'ø20'
      }, {
        value: 3,
        label: 'ø22'
      }, {
        value: 3.85,
        label: 'ø25'
      }, {
        value: 4.83,
        label: 'ø28'
      }, {
        value: 6.32,
        label: 'ø32'
      }, {
        value: 0.395,
        label: 'ø8'
      }, {
        value: 2.98,
        label: 'ø22.'
      }],
      /*pics*/
      picsrcroot: '../assetes/addimg',
      pics: [{
        src: require('../assets/addimg/000010.jpg'),
        id: '000010-3',
      },
      {
        src: require('../assets/addimg/000011.jpg'),
        id: '000011-3'
      },
      {
        src: require('../assets/addimg/000012.jpg'),
        id: '000012-4'
      },
      {
        src: require('../assets/addimg/000013.jpg'),
        id: '000013-4'
      },
      {
        src: require('../assets/addimg/000020.jpg'),
        id: '000020-1'
      },
      {
        src: require('../assets/addimg/000030.jpg'),
        id: '000030-3'
      },

      {
        src: require('../assets/addimg/000021.jpg'),
        id: '000021-2'
      },
      {
        src: require('../assets/addimg/000031.jpg'),
        id: '000031-1'
      },
      {
        src: require('../assets/addimg/000032.jpg'),
        id: '000032-1'
      },
      {
        src: require('../assets/addimg/000035.jpg'),
        id: '000035-2'
      },
      {
        src: require('../assets/addimg/000040.jpg'),
        id: '000040-2'
      },
      {
        src: require('../assets/addimg/000041.jpg'),
        id: '000041-3'
      },
      ],


      varNum: 0,
      value: '',
      companystring:'',
      orderdetail: {
        workname: '',
        companyid:'',
        companyname: '',
        picid: '',
        picsrc: '',
        amount: 0,
        price: '',
        date1: '',
        totalPrice: '',
        dim: 0,
        weight: 0,
          A: 0,
          B: 0,
          C: 0,
          D: 0
      },
      date2: '',
      account1: '',
      pickerOptions0: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7;
        },
      },

      rules: {
        workname: [
          { required: true, message: '请输入工程名称', trigger: 'blur' },
          { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }
        ],
        companyname: [
          { required: true, message: '请输入公司名称', trigger: 'blur' },
          { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }
        ],
        amount: [
          { required: true, message: '请输入数量', trigger: 'blur' }
        ],
        price: [{
          required: true, message: '请输入单价', trigger: 'blur'
        }, {
          validator: (rule, value, callback) => {
            if (/^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/.test(value) == false) {
              // if(Number(value).isNAN()==true){
              callback(new Error("请输入正确的数字"));
            } else {
              callback();
            }
          },
          trigger: 'blur'
        }],
        dim: [{ required: true, message: '请选择直径', trigger: 'blur' }],
        picid: [{
          required: true, message: '请选择图片', trigger: 'blur'
        }],
        date1: [{ required: true, message: '请选择到期时间', trigger: 'change' }],
        // speclength: [{ required: true, message: '请输入各部件长度', trigger: 'change' }]
      }
    }
  },
 
  mounted() {
    //fetchcompany
    this.$http.get(this.servicerurl + '/menber', {
      headers: {},
      emulateJSON: true
    }).then(function(response) {
      let opttemp = [{ label: '', value: '' }];
      for (var i = 0; i < response.data.length; i++) {
        opttemp[i] = { label: response.data[i].companyname, value: response.data[i].companyname+"|"+response.data[i].id }
      }
      this.options4 = opttemp
      console.log(this.options4);
    }, function(response) {
      console.log(response)
    })

 


  },

  computed: {
    totalPrice: function() {
      this.orderdetail.totalPrice = Number(this.orderdetail.amount) * Number(this.orderdetail.price);
      return this.orderdetail.totalPrice.toString();
    },

    totalLength: function() {
      console.log(this.orderdetail.picid);
      let id = this.orderdetail.picid.split('-')[0];
      let speclength = this.orderdetail
      let A = Number(speclength.A);
      let B = Number(speclength.B);
      let C = Number(speclength.C);
      let D = Number(speclength.D);
      console.log(id)
      let formulaList = [{
        id: "000010",
        formula: (A + B + C) * 2
      },
      {
        id: "000011",
        formula: (A + (B + C) / 2) * 2 + 10
      },
      {
        id: "000012",
        formula: A + B + C + D + 12
      },
      {
        id: "000013",
        formula: A + B + C + D + 10
      },
      {
        id: "000020",
        formula: A
      },
      {
        id: "000021",
        formula: (A + B) / 2
      },
      {
        id: "000030",
        formula: A + B + C
      },
      {
        id: "000031",
        formula: A + 10
      },
      {
        id: "000032",
        formula: A + 14
      },
      {
        id: "000035",
        formula: (A + B) / 2 + 14
      },
      {
        id: "000040",
        formula: A + B
      },
      {
        id: "000041",
        formula: A + B + C
      },
      ]
      for (var i = 0; i < formulaList.length; i++) {
        if (id == formulaList[i].id) {
          console.log(formulaList[i].formula)
          this.orderdetail.totalLength = Number(formulaList[i].formula)* Number(this.orderdetail.amount);
          console.log(this.orderdetail.totalLength);
          return this.orderdetail.totalLength;
        }
      }
    }
  },
  methods: {
    changemethod:function(){
      let value=this.companystring
       let id=value.split("|")[1];
       let name=value.split("|")[0];
      this.orderdetail.companyid=id
      this.orderdetail.companyname=name
    
    },
    changeSelection: function() {
      this.orderdetail.picsrc = require('../assets/addimg/' + this.orderdetail.picid.split('-')[0] + '.jpg')
      this.varNum = this.orderdetail.picid.split('-')[1]
    },
    onSubmit: function(formName) {

      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.orderdetail.totalPrice = this.totalPrice;
          this.orderdetail.totalLength = this.totalLength;
         
          this.$http.post(this.servicerurl + '/order', this.orderdetail, {
            headers: {},
            emulateJSON: true
          }).then(function(response) {
            //getagain,save in total_localstorage
            this.$http.get(this.servicerurl + '/order', {}, {
              headers: {},
              emulateJSON: true
            }).then(function(response) {
              console.log(response.data);
              localStorage.setItem('orderList', JSON.stringify(response.data));

              this.$router.push({ path: '/order/listMangerment' })
            })
            console.log(response.data);
          }, function(response) {
            console.log(response);
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    cancelAdd: function() {
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

.length_input {
  display: inline;
}
</style>
