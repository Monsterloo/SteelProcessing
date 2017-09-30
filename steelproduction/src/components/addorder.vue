<template>
  <div>
    <el-form class="form food_form" :rules="rules" ref="ruleForm" :model="orderdetail" :inline="true">
      <el-form-item label="工 程 名 字" class="fontcolor temipt" prop="projectName">
        <el-input v-model="orderdetail.projectName" placeholder="工 程 名 字" required=true class="forminput"></el-input>
      </el-form-item>
      <el-form-item label="公 司 名 字" class="fontcolor temipt" required>
        <el-select v-model="companystring" placeholder="请选择" filterable @change="changemethod">
          <el-option v-for="item in options4" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      

      <el-form-item label="到 期 日" class="fontcolor temipt" prop="date2" required>
        <el-date-picker v-model="targettime" type="datetime" placeholder="选择日期时间" :picker-options="pickerOptions0" @change="changdate">
        </el-date-picker>
      </el-form-item>
   
   
      
      <el-button type="primary" @click="dialogFormVisible = true" style="margin-bottom: 10px;">添加产品</el-button>

       <el-table :data="orderdetail.details" style="margin-bottom: 20px; width:800px">
          <el-table-column label="直径">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.diametre}}cm</span>
            </template>
          </el-table-column>
          <el-table-column label="数量">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.count}}</span>
            </template>
          </el-table-column>
          
        
          <el-table-column label="重量">
            <template scope="scope">
              <span style="margin-left: 10px;">{{ scope.row.weight}}cm/kg</span>
            </template>
          </el-table-column>

           <el-table-column label="钢筋种类">
            <template scope="scope">
              <span v-if='scope.row.steelCategory=="1"'> 光 圆 钢 筋</span>
              <span v-if='scope.row.steelCategory=="2"'> 带 肋 钢 筋</span>
              <span v-if='scope.row.steelCategory=="3"'> 扭 转 钢 筋</span>
              <span v-if='scope.row.steelCategory=="4"'> 变 形 钢 筋 (直)</span>
              <span v-if='scope.row.steelCategory=="5"'> 变 形 钢 筋 (圆)</span>
            </template>
          </el-table-column>
          <el-table-column label="简图" width="180">
            <template scope="scope">
             <img class="avatar" :src='scope.row.picsrc' style="height:36px;">
            </template>
          </el-table-column>
          <el-table-column label="总价">
            <template scope="scope">
            <span>{{scope.row.totalPrice}}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template scope="scope">
              <el-button size="small" type="danger" @click="handleDelete(scope.$index)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-form-item>
      <el-form-item label="总 价" class="fontcolor temipt">
          
        <p>¥{{this.orderdetail.totalPrice}}</p>
       
      </el-form-item>
      <el-form-item label-width="80px" class="btnblock">
        <el-button type="primary" @click="onSubmit('ruleForm')">立即创建</el-button>
        <el-button @click="cancelAdd">取消</el-button>
      </el-form-item>
      
    </el-form>

    <el-dialog title="添加产品" v-model="dialogFormVisible">
      <el-form :model="productform">
         <el-form-item label="钢 筋 直 径" class="fontcolor temipt" required>
        <el-select v-model="productform.diametre" placeholder="请选择">
          <el-option v-for="item in optionsDim" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item> 
       <el-form-item label="钢 筋 简 图" class="fontcolor temipt" prop="picid">
        <el-select v-model="productform.picid" placeholder="请选择" @change="changeSelection">
          <el-option v-for="item in pics" :key="item.id" :label="item.id" :value="item.id">
            <img class="avatar" :src="item.src" style="height:36px">
          </el-option>
        </el-select>
        <img class="avatar" :src='productform.picsrc' style="height:36px">
      </el-form-item>
      <el-form-item label="单 价" class="fontcolor temipt " prop="price">
        <div class="forminput pricewidth">
          <el-input v-model="productform.price"></el-input>
        </div>
      </el-form-item> 
       <el-form-item label="钢 筋 种 类" class="fontcolor temipt " prop="price">
        <div class="forminput pricewidth">
        <select v-model="productform.steelCategory" class="mySelect">
               <option v-for="item in optiontype" v-bind:value="item.value" v-bind:key="item.value" placeholder="请选择" >{{item.label}}</option>
        </select>
        </div>
      </el-form-item> 
          <el-form-item label="部 件 长 度" class="fontcolor temipt" required>
        <div v-if="varNum==1">
          <span class="length_input">A</span>
          <el-input style="width:70px" v-model="productform.A"></el-input>
        </div>
        <div v-if="varNum==2">
          <span class="length_input">A</span>
          <el-input style="width:70px" v-model="productform.A"></el-input>
          <span class="length_input">B</span>
          <el-input style="width:70px" v-model="productform.B"></el-input>
        </div>
        <div v-if="varNum==3">
          <span class="length_input">A</span>
          <el-input style="width:70px" v-model="productform.A"></el-input>
          <span class="length_input">B</span>
          <el-input style="width:70px" v-model="productform.B"></el-input>
          <span class="length_input">C</span>
          <el-input style="width:70px" v-model="productform.C"></el-input>
        </div>
        <div v-if="varNum==4">
          <span class="length_input">A</span>
          <el-input style="width:70px" v-model="productform.A"></el-input>
          <span class="length_input">B</span>
          <el-input style="width:70px" v-model="productform.B"></el-input>
          <span class="length_input">C</span>
          <el-input style="width:70px" v-model="productform.C"></el-input>
          <span class="length_input">D</span>
          <el-input style="width:70px" v-model="productform.D"></el-input>
        </div>
      </el-form-item>
      <el-form-item label="数 量" class="fontcolor temipt" required>
        <el-input-number v-model="productform.amount" :min="0"></el-input-number>
      </el-form-item>
      <el-form-item label="长 度" class="fontcolor temipt">
        <p>{{ totalLength }}cm</p>
      </el-form-item> 
      <el-form-item label="重量" class="fontcolor temipt">
        <p>{{ totalWeight }}cm/kg</p>
      </el-form-item> 
      <el-form-item label="总价" class="fontcolor temipt">
        <p>{{totalPrice }}元</p>
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
function formatDateTime(inputTime) {
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
}
export default {
    data() {
        return {
            productform: {
                amount: 0,
                picid: '',
                picsrc: '',
                price: '',
                spec: '',
                steelCategory: '0',
                diametre: 0,
                A: 0,
                B: 0,
                C: 0,
                D: 0,
            },
            optiontype: [{
                value: '1',
                label: '光圆钢筋'
            },
            {
                value: '2',
                label: '带肋钢筋'
            },
            {
                value: '3',
                label: '扭转钢筋'
            },
            {
                value: '4',
                label: '变形钢筋(直)'
            },
            {
                value: '5',
                label:'变形钢筋(圆)'
            }],
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
            companystring: '',
            orderdetail: {
                projectName: '',
                companyid: '',
                companyname: '',
                totalPrice: 0,
                details:[{
                    count: 0,
                    picid: '',
                    picsrc: '',
                    price: '',
                    diametre: 0,
                    A: 0,
                    B: 0,
                    C: 0,
                    D: 0,
                    length:0,
                    weight:0,
                    steelCategory: '0',
                }]
            },
            dialogFormVisible: false,
            targettime: '',
            date2: '',
            account1: '',
            pickerOptions0: {
                disabledDate(time) {
                    return time.getTime() < Date.now() - 8.64e7;
                },
            },

            rules: {
                projectName: [
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
                            callback(new Error('请输入正确的数字'));
                        } else {
                            callback();
                        }
                    },
                    trigger: 'blur'
                }],
                diametreim: [{ required: true, message: '请选择直径', trigger: 'blur' }],
                picid: [{
                    required: true, message: '请选择图片', trigger: 'blur'
                }],
                targettime: [{ required: true, message: '请选择到期时间', trigger: 'change' }],
                // speclength: [{ required: true, message: '请输入各部件长度', trigger: 'change' }]
            }
        };
    },

    mounted() {
    //fetchcompany
        this.$http.get('/sp/client/listPage/0/1000', {
            headers: {},
            emulateJSON: true
        }).then(function(response) {
            let opttemp = [{ label: '', value: '' }];
            for (var i = 0; i < response.data.recordList.length; i++) {
                opttemp[i] = { label: response.data.recordList[i].cname, value: response.data.recordList[i].cname + '|' + response.data.recordList[i].cid };
            }
            this.options4 = opttemp;
     
        }, function(response) {
     
        });



    },

    computed: {
        totalPrice: function() {
            this.productform.totalPrice = Number(this.productform.amount) * Number(this.productform.price);
            return Number(this.productform.totalPrice);
        },
        totalWeight: function(){
            /*cm*/
            let lenght = Number(this.productform.length);
            let diametre = Number(this.productform.diametre); 
            let weightper = diametre * lenght * 0.00617;
            return this.productform.totalWeight = weightper * this.productform.amount;
           
        },

        totalLength: function() {
            let id = this.productform.picid.split('-')[0];
            let speclength = this.productform;
            let A = Number(speclength.A);
            let B = Number(speclength.B);
            let C = Number(speclength.C);
            let D = Number(speclength.D);
     
            let formulaList = [{
                id: '000010',
                formula: (A + B + C) * 2
            },
            {
                id: '000011',
                formula: (A + (B + C) / 2) * 2 + 10
            },
            {
                id: '000012',
                formula: A + B + C + D + 12
            },
            {
                id: '000013',
                formula: A + B + C + D + 10
            },
            {
                id: '000020',
                formula: A
            },
            {
                id: '000021',
                formula: (A + B) / 2
            },
            {
                id: '000030',
                formula: A + B + C
            },
            {
                id: '000031',
                formula: A + 10
            },
            {
                id: '000032',
                formula: A + 14
            },
            {
                id: '000035',
                formula: (A + B) / 2 + 14
            },
            {
                id: '000040',
                formula: A + B
            },
            {
                id: '000041',
                formula: A + B + C
            },
            ];
            for (var i = 0; i < formulaList.length; i++) {
                if (id == formulaList[i].id) {
          
                    this.productform.length = Number(formulaList[i].formula);
         
                    return this.productform.length;
                }
            }
        }
    },
    methods: {
        handleDelete(index) {
            this.orderdetail.products.splice(index, 1);
        },
        addspecs: function() {
            let objtemp = {};
            objtemp.sid =this.productform.picid;
            objtemp.count = this.productform.amount;
            objtemp.picsrc = this.productform.picsrc;
            objtemp.price = Number(this.productform.price);
            objtemp.diametre = Number(this.productform.diametre);
            objtemp.length=Number(this.productform.length);
            objtemp.steelCategory=Number(this.productform.steelCategory);
            objtemp.A = Number(this.productform.A);
            objtemp.B = Number(this.productform.B);
            objtemp.C = Number(this.productform.C);
            objtemp.D = Number(this.productform.D);
            objtemp.partsLength = objtemp.A + ',' +objtemp.B + ',' + objtemp.C + ',' +objtemp.D;
            objtemp.weight = Number(this.productform.totalWeight).toFixed(2);
            objtemp.totalPrice=Number(this.productform.totalPrice);
            this.orderdetail.details.push(objtemp);
            this.dialogFormVisible = false;
            this.orderdetail.totalPrice+=this.productform.totalPrice;
            for(var i=0;i<this.orderdetail.details.length;i++){
                if(this.orderdetail.details[i].count == 0){
                    this.orderdetail.details.splice(i,1);
                }
            }
            this.productform.picid = '';
            this.productform.amount = 0;
            this.productform.picsrc = '';
            this.productform.price = 0;
            this.productform.diametre= 0;
            this.productform.length=0;
            this.productform.steelCategory= 0;
            this.productform.A=0;
            this.productform.B=0;
            this.productform.C=0;
            this.productform.D=0;
            this.productform.totalWeight=0;
            this.productform.totalPrice=0;

        },
        changdate: function() { 
            var timestring = formatDateTime(this.targettime);
            this.orderdetail.targettime=timestring;
        },
        changemethod: function() {
            let value = this.companystring;
            let id = value.split('|')[1];
            let name = value.split('|')[0];
            this.orderdetail.companyid = id;
            this.orderdetail.companyname = name;

        },
        changeSelection: function() {
            this.productform.picsrc = require('../assets/addimg/' + this.productform.picid.split('-')[0] + '.jpg');
            this.varNum = this.productform.picid.split('-')[1];
        },
        onSubmit: function(formName) { 
            delete this.orderdetail.companyname;
            delete this.orderdetail.weight;
            for(let i =0;i<this.orderdetail.details.length;i++){
                delete this.orderdetail.details[i].picsrc;
                delete this.orderdetail.details[i].A;
                delete this.orderdetail.details[i].B;
                delete this.orderdetail.details[i].C;
                delete this.orderdetail.details[i].D;
                
            }  
            console.log(this.orderdetail);
            console.log(typeof(this.orderdetail.details));
            let details = JSON.stringify(this.orderdetail.details);
            console.log(JSON.parse(details));
            // this.$refs[formName].validate((valid) => {
            //     if (valid) {         
            //         // this.$http.post('/sp/order/create', this.orderdetail, {
            //         this.$http.post('/sp/order/create', {projectName:this.orderdetail.projectName,cid:this.orderdetail.companyid,dueDate:this.orderdetail.targettime,details:JSON.parse(details),totalPrice:this.orderdetail.totalPrice}, {
            //             headers: {},
            //             emulateJSON: true
            //         }).then(function(response) {
       
            //             this.$http.get('/sp/order/listPage/0/10', {}, {
            //                 headers: {},
            //                 emulateJSON: true
            //             }).then(function(response) {
            //                 localStorage.setItem('orderList', JSON.stringify(response.data));

            //                 this.$router.push({ path: '/order/listMangerment' });
            //             });
           
            //         }, function(response) {
          
            //         });
            //     } else {
            //         this.$message({
            //             showClose: true,
            //             message: '添加失败',
            //             type: 'warning'
            //         });
          
            //         return false;
            //     }
            // });
        },

        cancelAdd: function() {
            this.$router.push({ path: '/order/listMangerment' });
        }
    }
};
</script>
<style>
.mySelect{
    width: 172px;
    height: 36px;
    border-color: #bfcbd9;
}
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
