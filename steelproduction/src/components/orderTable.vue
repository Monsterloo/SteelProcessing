<template>
    <div class="clear ">
        <el-input placeholder="请选择关键字" icon="search" class="searchbox not-print" v-model="seachbyId">
        </el-input>
        <el-button type="primary" class="addbtn not-print" @click="addOrder">新 增 订 单</el-button>
        <el-table :data="totaldata" border style="width: 100%;" class="ordertable not-print">
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
        <!-- <el-dialog title="订单进度" :visible.sync="dialogFormVisible">
            <el-steps :space="100" :active="active" finish-status="success">
                <el-step title="订单下达"></el-step>
                <el-step title="生产完成"></el-step>
                <el-step title="订单出库"></el-step>
            </el-steps>

            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="update" v-if="this.selectedTable.status!='订单出库'&&this.selectedTable.status!='生产完成'">下一步</el-button>
            </div>
        </el-dialog> -->

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
export default {
    data() {
        return {
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            totaldata: [],
        };
    },
    mounted :function(){
        this.fetchData();
    },
    methods: {
        fetchData: function() {
            this.loading = true;
            this.$http.get('/sp/order/listPage' + '/' + this.pageIndex + '/' + this.pageSize).then((response) => {
                console.log(response.data);
                this.totaldata=response.data.recordList;
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
    }
};
</script>