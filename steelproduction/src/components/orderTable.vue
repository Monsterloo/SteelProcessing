<template>
    <div class="clear ">
       <!-- 根据工程名字 -->
        <el-input placeholder="请选择关键字" icon="search" class="searchbox not-print" v-model="seachbyId">
        </el-input>
        <el-button type="primary" class="addbtn not-print" @click="addOrder">新 增 订 单</el-button>
        <el-table :data="totaldata" border style="width: 100%;" class="ordertable not-print">
             <el-table-column type="expand">
                 <template scope="scope">
                     <el-table :data="scope.row.orderDetailList" stripe style="width: 100%">
                            <el-table-column  label="钢 筋 直 径">
                                <template scope="scope">
                                    <span style="margin-left: 10px;">{{ scope.row.diametre}}</span>
                                </template>
                            </el-table-column>
                            <!-- <el-table-column   label="钢 筋 简 图" >
                                <span class="imgstyle">
                                 
                                       <img v-bind:src="scope.row.pic" alt="" srcset="">   -->
                                      <!-- <img :src="reqiure('../assets/addimg/'+scope.row.sid+'.jpg')" alt="" srcset="">                                       -->
                                     <!-- <img src='../assets/addimg/'+'000010'+'.jpg' alt="" srcset="">                                      
                                    <template scope="scope">
                                    <img class="avatar" :src='scope.row.pic' style="height:36px;">
                                    </template>
                                
                            </span>
                            </el-table-column> -->
                            <el-table-column  label="钢 筋 种 类">
                                <template scope="scope">
                                    <!-- <span style="margin-left: 10px;">{{ scope.row.type}}</span> -->
                                        <span v-if="scope.row.steelCategory=='1'">光圆钢筋</span>
                                        <span v-if="scope.row.steelCategory=='2'">带肋钢筋</span>
                                        <span v-if="scope.row.steelCategory=='3'">扭转钢筋</span>
                                        <span v-if="scope.row.steelCategory=='4'">变形钢筋(直)</span>
                                        <span v-if="scope.row.steelCategory=='5'">变形钢筋(圆)</span>

                                </template>
                            </el-table-column>
                             <el-table-column  label="部 件 长 度">
                                <el-table-column  label="A">
                                <template scope="scope">
                                    <span style="margin-left: 10px;">{{ scope.row.A}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column  label="B">
                                <template scope="scope">
                                    <span style="margin-left: 10px;">{{ scope.row.B}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column  label="C">
                                <template scope="scope">
                                    <span style="margin-left: 10px;">{{ scope.row.C}}</span>
                                </template>
                            </el-table-column>
                            <el-table-column  label="D">
                                <template scope="scope">
                                    <span style="margin-left: 10px;">{{ scope.row.D}}</span>
                                </template>
                            </el-table-column>
                            </el-table-column>
                            <el-table-column  label="重量">
                                <template scope="scope">
                                    <span style="margin-left: 10px;">{{ scope.row.weight}}</span>
                                </template>
                            </el-table-column>
                    </el-table>
                </template> 
            </el-table-column> 
            <el-table-column label="订单编号">
                <template scope="scope">
                    <span style="margin-left: 10px;">{{ scope.row.oid }}</span>
                </template>
            </el-table-column>
            <el-table-column label="工程名称">
                <template scope="scope">
                    <span style="margin-left: 10px;">{{ scope.row.projectName}}</span>
                </template>
            </el-table-column>
            <el-table-column label="公司名称">
                <template scope="scope">
                    <span> {{ scope.row.client.cname}}</span>
                </template>
            </el-table-column>
            <el-table-column label="订单总价">
                <template scope="scope">
                    <span> {{ scope.row.totalPrice}}</span>
                </template>
            </el-table-column>
             <el-table-column label="订单定金">
                <template scope="scope">
                    <span> {{ scope.row.deposit}}</span>
                </template>
            </el-table-column>
            <el-table-column label="到期日">
                <template scope="scope">
                    <span>{{ scope.row.dueDate}}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <!--确认订单生产完成-->
                    <el-button @click="updatestatus(scope.$index, scope.row)" type="text" v-if="scope.row.orderState!='2'&&scope.row.orderState!='3'">更新进度</el-button>
                    <span v-if="scope.row.orderState=='3'">订单已出库</span>
                    <span v-if="scope.row.orderState=='2'">订单待出库</span>
                    <el-button @click="printEdit(scope.$index, scope.row)" type="text">订单打印</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChange" v-on:current-change="pageIndexChange" class="pagination not-print">

        </el-pagination>

        <!--订单进度-->
         <el-dialog title="订单进度" :visible.sync="dialogFormVisible">
            <el-steps :space="100" :active="active" finish-status="success">
                <el-step title="订单下达"></el-step>
                <el-step title="生产完成"></el-step>
                <el-step title="订单出库"></el-step>
            </el-steps>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="update" v-if="this.selectedTable.orderState!='2'&&this.selectedTable.orderState!='3'">确认生产完成</el-button>
            </div>
        </el-dialog> 

    </div>
</template>
<style>
@media print {
    .not-print {
        opacity: 0
    }
}

.pdf-dom {
    display: none;
    position: absolute;
    top: 0;
    height: 500px;
}
.demo-table-expand {
    font-size: 0;
}

.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}

.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
}

.ordertable {

    top: 20px !important;
    font-size: 12px;
    margin-left: 20px;
}

.searchbox {

    width: 30%;
    margin-left: 20px !important;
}

.addbtn {
    background-color: #ff9800 !important;
    float: right;

    margin-right: 20px;
}

.clear {
    clear: both
}

.dangeraction {
    color: red !important;
}

.pagination {
    margin-top: 30px;
    margin-left: 40%;
}

.imgstyle img {
    width: 95px;
}
.table-boder {}
</style>

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
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            totaldata: [],
            selectedTable:[],
            active: 1,
            dialogFormVisible:false
        };
    },
    mounted :function(){
        this.fetchData();
    },
    methods: {
        addOrder: function(){
            this.$router.push('/order/add');
        },
        fetchData: function() {
            this.loading = true;
            this.$http.get('/sp/order/listPage' + '/' + this.pageIndex + '/' + this.pageSize).then((response) => {
                console.log(response.data);
                console.log(typeof(response.data.recordList[0].dueDate));
                this.totaldata=response.data.recordList;
                console.log(this.totaldata.length);
                for(let j=0;j<this.totaldata.length;j++){
                    this.totaldata[j].dueDate = formatDateTime(new Date(this.totaldata[j].dueDate));
                    if(this.totaldata[j].orderDetailList.length>0){
                        for (var i = 0; i < this.totaldata[j].orderDetailList.length; i++) {
                // 拼接路径
                            // if(this.totaldata[j].orderDetailList[i].sid){
                            //     this.totaldata[j].orderDetailList[i].pic=require('../assets/addimg/' + this.totaldata[j].orderDetailList[i].sid + '.jpg');
                            // }
                            // this.totaldata[j].orderDetailList[i].pic=require('../assets/addimg/' + this.totaldata[j].orderDetailList[i].sid + '.jpg');
                            
                            let partLength = this.totaldata[j].orderDetailList[i].partsLength.split(',');
                            this.totaldata[j].orderDetailList[i].A = partLength[0];
                            this.totaldata[j].orderDetailList[i].B = partLength[1];
                            this.totaldata[j].orderDetailList[i].C = partLength[2];
                            this.totaldata[j].orderDetailList[i].D = partLength[3]; 
                        
                        }
                    }
                }
                
                this.total=response.data.total;
            }).then((response) => {
                console.log(response);
            });
        },
        sizeChange: function(pageSize) {
            this.pageSize = pageSize;
            this.fetchData();
        },
        pageIndexChange: function(pageIndex) {
            this.pageIndex = pageIndex-1;
            this.fetchData();
        },
        updatestatus: function($index, row) {
            if (row.orderState == '2') { this.active = 2; }
            else if (row.orderState == '3') { this.active = 3; }
            else this.active = 1;
            this.selectedTable = row;
            this.dialogFormVisible = true;
            console.log(this.selectedTable);
        },
        update: function() {

            // this.selectedTable.orderState='2';
            this.$http.post('/sp/order/completeProducted' + '/' + this.selectedTable.oid, this.selectedTable, {
                headers: {},
                emulateJSON: true
            }).then(function(response) {
                this.dialogFormVisible = false;
                this.$message({
                    message: '更新成功',
                    showClose: true,
                    type: 'success'
                });
                this.fetchData();
                console.log(response.data);

            }, function(response) {
                console.log(response);
            });

        },
        printEdit: function($index, row) {
            let ordertemp = {};
            ordertemp = row;
            localStorage.setItem('printTemp', JSON.stringify(ordertemp));
            this.$router.push({ path: '/print' });

        },
        
    }
};
</script>