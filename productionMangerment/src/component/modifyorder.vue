<template>
<el-form class="formStyle" :rules="rules" ref="ruleForm" :model="orderdetail1">
    <el-form-item label="工 程 名 字" class="fontcolor" >
      
    <!-- <el-input v-model="orderdetail.workname1" placeholder="工 程 名 字" required=true class="forminput" ></el-input> -->
   <p>{{orderdetail1.workname}}</p>
</el-form-item>
    <el-form-item label="公 司 名 字" class="fontcolor" >
     <div class="forminput">
    <p>{{orderdetail1.companyname}}</p>
    </div> 
  </el-form-item>
   <el-form-item label="钢 筋 简 图" class="fontcolor" >
    <!-- <el-input v-model="orderdetail.pic" placeholder="公 司 名 字"></el-input> -->
 </el-form-item>
   <el-form-item label="数 量" class="fontcolor" prop="account1">
 <el-input-number v-model="orderdetail1.amount" :min="0"></el-input-number>  </el-form-item>
   <el-form-item label="单 价" class="fontcolor" prop="price2">
     <div class="forminput">
    <el-input v-model="orderdetail1.price"></el-input>
     </div>       
  </el-form-item>
   <el-form-item label="总 价" class="fontcolor" >
    <p>{{totalPrice}}</p>
  </el-form-item>
   <el-form-item label="到 期 日" class="fontcolor" prop="date2" required>
<el-date-picker
      v-model="orderdetail1.targettime"
      align="right"
      type="date"
      placeholder="选择日期"
      :picker-options="pickerOptions0"
      >
    </el-date-picker>
      </el-form-item>
        <el-form-item label-width="80px">
    <el-button type="primary" @click="onModify('ruleForm')">修改</el-button>
    <el-button @click="cancelAdd">取消</el-button>
  </el-form-item>
  </el-form>  
</template>

<script>
var STORAGE_KEY='orderTemp';
var orderStorageMo={
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
          orderdetail1:orderStorageMo.fetch(),
          price2:'',
           rules:{
          amount:[
            {required: true, message: '请输入数量', trigger: 'blur' }
          ],
          price1:[{
            required: true, message: '请输入单价', trigger: 'blur' 
          }],
         date1:[{required: true, message: '请选择到期时间', trigger: 'change' }]
        },

        pickerOptions0:{
          disabledDate(time) { 
          return time.getTime() < Date.now() - 8.64e7;
           
          },
        },

      }
  },

 methods:{
   onModify:function(formName){
                 this.$refs[formName].validate((valid) => {
          if (valid) {
           this.$http.put(this.servicerurl+'/order'+'/'+this.orderdetail1.id,this.orderdetail1,{
      headers:{},
      emulateJSON:true
    }).then(function(response){
      localStorage.setItem('orderTemp',JSON.stringify(response.data));
      //getagain,save in total_localstorage
      this.$http.get(this.servicerurl+'/order',{},{
      headers:{},
      emulateJSON:true
    }).then(function(response){
      console.log(response.data);
       localStorage.setItem('orderList',JSON.stringify(response.data));
      
      this.$router.push({ path: '/order/listMangerment'})
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
    this.$router.push({ path: '/order/listMangerment' })
  },
 },
   
   computed: {
      totalPrice:function(){
          console.log(this.orderdetail1);
       this.orderdetail1.totalprice=Number(this.orderdetail1.amount)*Number(this.orderdetail1.price);
       return this.orderdetail1.totalprice.toString();
     }
  }
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