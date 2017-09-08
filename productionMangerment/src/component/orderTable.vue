
<template>
    <div class="clear ">
        <el-input placeholder="请选择关键字" icon="search" class="searchbox not-print" v-model="seachbyId">
        </el-input>
        <el-button type="primary" class="addbtn not-print" @click="addOrder">新 增 订 单</el-button>
        <el-table :data="filtermatchdata" border style="width: 100%;" class="ordertable not-print">
            <el-table-column type="expand">
                <template scope="scope">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="工程名称">
                            <span>{{ scope.row.workname}}</span>
                        </el-form-item>
                        <el-form-item label="直径规格">
                            <span>{{ scope.row.dim}}</span>
                        </el-form-item>
                        <el-form-item label="总价">
                            <span>{{ scope.row.amount*scope.row.price}}</span>
                        </el-form-item>
                        <el-form-item label="数量">
                            <span>{{ scope.row.amount}}</span>
                        </el-form-item>
                        <el-form-item label="单价">
                            <span>{{ scope.row.price}}</span>
                        </el-form-item>
                        <el-form-item label="总长度">
                            <span>{{ scope.row.totalLength}}</span>
                        </el-form-item>
                        <el-form-item label="总重量">
                            <span>{{ scope.row.weight}}</span>
                        </el-form-item>
                        <el-form-item label="简图">
                            <span class="imgstyle">
                                <lazy-component @show="handler">
                                    <img v-bind:src="scope.row.pic" alt="" srcset="">
                                </lazy-component>
                            </span>
                        </el-form-item>
                        <el-form-item>

                        </el-form-item>

                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="订单编号">
                <template scope="scope">
                    <span style="margin-left: 10px;">{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column label="公司名称">
                <template scope="scope">
                    <span> {{ scope.row.companyname}}</span>
                </template>
            </el-table-column>

            <el-table-column label="到期日">
                <template scope="scope">
                    <span>{{ scope.row.targettime}}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button @click="updatestatus(scope.$index, scope.row)" type="text" v-if="scope.row.status!='订单出库'&&scope.row.status!='生产完成'">更新进度</el-button>
                    <span v-if="scope.row.status=='订单出库'">订单已出库</span>
                    <span v-if="scope.row.status=='生产完成'">订单待出库</span>
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
                <el-button type="primary" @click="update" v-if="this.selectedTable.status!='订单出库'&&this.selectedTable.status!='生产完成'">下一步</el-button>
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

.table-boder {}
</style>

<script>
// import this.servicerurl from '../js/host.js'
var STORAGE_KEY = 'orderList';
var orderStorage = {
    save: function(data) {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(data))
    },
    fetch: function() {
        return JSON.parse(localStorage.getItem(STORAGE_KEY) || []);
    }
};


export default {
    data() {
        return {

            dialogFormVisible: false,
            selectedTable: [],
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            totaldata: [],
            tableData: [],
            seachbyId: '',
            matchdata: [],
            id: '',
            active: 1

        }
    },
    mounted: function() {
        this.$http.get(this.servicerurl + '/order', {}, {
            headers: {},
            emulateJSON: true
        }).then(function(response) {
            for (var i = 0; i < response.data.length; i++) {
                // 拼接路径
                if(response.data[i].picid){
               response.data[i].pic=require('../assets/addimg/' + response.data[i].picid.split('-')[0] + '.jpg')
               console.log(response.data[i].pic)
                console.log(response.data[i].picid.split('-')[0])
                }
            }
            orderStorage.save(response.data);
            this.tableData = orderStorage.fetch();
            console.log(response.data.pic)
            
        }, function(response) {
            console.log(response)
        })

    },
    watch: {
        tableData: {
            handler() {
                orderStorage.save(this.tableData);
                this.total = this.filtermatchdata.length;
            },
            deep: true
        }
    },
    computed: {
        filtermatchdata: function() {
            let matchdata = this.searchFilter('id', this.seachbyId, this.tableData);
            return matchdata;
        },
        totalPrice: function() {
            console.log(this.orderdetail1);
            row.totalprice = Number(row.amount1) * Number(row.price1);
            return this.orderdetail1.totalprice.toString();
        }
    },
    methods: {
        updatestatus: function($index, row) {
            if (row.status == "生产完成") { this.active = 2; }
            else if (row.status == "订单出库") { this.active = 3; }
            else this.active = 1;
            this.selectedTable = row;
            this.dialogFormVisible = true;
            console.log(this.selectedTable)
        },
        update: function() {
            //更改订单状态

            if (this.selectedTable.status == "生产完成") {
                this.selectedTable.status = "订单出库"
            }
            else { this.selectedTable.status = "生产完成" }
            //    console.log(this.selectedTable)
            this.$http.put(this.servicerurl + '/order' + '/' + this.selectedTable.id, this.selectedTable, {
                headers: {},
                emulateJSON: true
            }).then(function(response) {
                this.dialogFormVisible = false;
                this.$http.get(this.servicerurl + '/order', {
                    pageIndex: this.pageIndex,
                    pageSize: this.pageSize
                }, {
                        headers: {},
                        emulateJSON: true
                    }).then(function(response) {
                        console.log(response.data);
                        this.$message({
                            showClose: true,
                            message: '更新成功',
                            type: 'success'
                        })
                        this.fetchData();
                    })
                console.log(response.data);

            }, function(response) {
                console.log(response);
            })

        },
        handler: function() {
            console.log('this component is showing')
        },

        sizeChange: function(pageSize) {
            this.pageSize = pageSize;
            this.fetchData();
        },
        pageIndexChange: function(pageIndex) {
            this.pageIndex = pageIndex;
            this.fetchData();
        },
        fetchData: function() {
            this.loading = true;
            this.$http.get(this.servicerurl + '/order', {
                pageIndex: this.pageIndex,
                pageSize: this.pageSize
            }, {
                    headers: {},
                    emulateJSON: true
                }).then(function(response) {
                    orderStorage.save(response.data);
                    this.tableData = orderStorage.fetch();
                    this.loading = false;
                    console.log(response.data)
                }, function(response) {
                    console.log(response)
                })
        },

        searchFilter: function(prop, key, arr) {
            if (!key) {
                return arr;
            }
            arr = arr.filter((row) => {
                if (row[prop].toString().indexOf(key) !== -1) {
                    return true
                }
            })
            if (key !== this[prop]) {
                this.currentPage = 0
                this[prop] = key
            }
            return arr;
        },
        addOrder: function() {
            this.$router.push({ path: '/order/add' })
        },
        printEdit: function($index, row) {
            let ordertemp = {};
            ordertemp = row;
            var printStorage = localStorage.setItem('printTemp', JSON.stringify(ordertemp))
            this.$router.push({ path: '/print' })

        },

        s4: function() {
            return Math.floor((1 + Math.random()) * 0x10000)
                .toString(16)
                .substring(1);
        },
        JsGuid: function() {
            return this.s4() + this.s4() + '-' + this.s4() + '-' + this.s4() + '-' +
                this.s4() + '-' + this.s4() + this.s4() + this.s4();
        }

    }
}
</script>
<style>
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
</style>
