<template>
    <el-tabs type="border-card" class='outtable'>
        <!-- <el-tab-pane label="材料出库">
            <el-table :data="materialData" border style="width: 100%">
                <el-table-column label="出货批号">
                    <template scope="scope">
                        <p>{{ scope.row.id}}</p>
                    </template>
                </el-table-column>
                <el-table-column label="材料名称">
                    <template scope="scope">
                        <p>{{ scope.row.name}}</p>
                    </template>
                </el-table-column>
                <el-table-column label="材料数量">
                    <template scope="scope">
                        <p>{{ scope.row.amount}}</p>
                    </template>
                </el-table-column>
                <el-table-column prop="tag" label="材料类别" :filters="[{ text: '原材料', value: '原材料' }, { text: '半成品', value: '半成品' }]" :filter-method="filterTag" filter-placement="bottom-end">
                    <template scope="scope">
                        <el-tag :type="scope.row.class[0] === '原材料' ? 'primary' : 'success'" close-transition>{{scope.row.class}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="状态">
                    <template scope="scope">
                        <p>{{ scope.row.status}}</p>
                    </template>
                </el-table-column>
                <el-table-column label="日期">
                    <template scope="scope">
                        <el-icon name="time"></el-icon>
                        <span style="margin-left: 10px">{{ scope.row.date }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button @click="handleEdit(scope.$index, scope.row)" v-if="scope.row.status!='已出库'">确认出库</el-button>
                    </template>
                </el-table-column>

            </el-table> -->
            <!-- <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChangem" v-on:current-change="pageIndexChangem" class="pagination">

            </el-pagination> -->
            <el-dialog title="提示" v-model="disvisibity1" size="tiny">
                <span>确认出库完成</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="confirmout">确 定</el-button>
                </span>
            </el-dialog>
        </el-tab-pane>
        <el-tab-pane label="订单出库">
            <el-table :data="orderData" border style="width: 100%">
                <el-table-column label="订单批号">
                    <template scope="scope">
                        <p>{{ scope.row.id}}</p>
                    </template>
                </el-table-column>
                <el-table-column label="到期日">
                    <template scope="scope">
                        <p>{{ scope.row.targettime}}</p>
                    </template>
                </el-table-column>
                <el-table-column label="状态">
                    <template scope="scope">
                        <p>{{ scope.row.status}}</p>
                    </template>
                </el-table-column>

                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button type='text' @click="handleEditorder(scope.$index, scope.row)" v-if="scope.row.status!='订单出库'">确认出库</el-button>
                        <el-button type='text' @click="handlecheck(scope.$index, scope.row)">订单详情</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChangeo" v-on:current-change="pageIndexChangeo" class="pagination">

            </el-pagination>

            <el-dialog title="提示" v-model="disvisibity2" size="tiny">
                <span>确认出库完成</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible2 = false">取 消</el-button>
                    <el-button type="primary" @click="confirmoutorder">确 定</el-button>
                </span>
            </el-dialog>

              <el-dialog title="订单详情" v-model="disvisibity_detail" >
                
              <el-form v-model="selectTable" >
                <el-form-item label="订单编号" style="color:#0085ef!important">
                    <span style="margin-left: 10px;">{{ selectTable.id }}</span>
                </el-form-item>
                <el-form-item label="工程名称" style="color:#0085ef!important">
                    <span>{{ selectTable.workname}}</span>
                </el-form-item>
                <el-form-item label="公司名称" style="color:#0085ef!important">
                    <span> {{ selectTable.companyname}}</span>
                </el-form-item>
                <el-form-item label="到期日" style="color:#0085ef!important">
                    <span>{{ selectTable.targettime}}</span>
                </el-form-item>
                <el-form-item label="产品直径" style="color:#0085ef!important">
                    <span>{{ selectTable.dim}}</span>
                </el-form-item>
                <el-form-item label="简图">
                    <span class="imgstyle">
                        <img v-bind:src="selectTable.pic" alt="" srcset="" width="150">
                    </span>
                </el-form-item>
                <el-form-item label="总价" style="color:#0085ef!important">
                    <span>{{ selectTable.amount*selectTable.price}}</span>
                </el-form-item>
                <el-form-item label="数量" style="color:#0085ef!important">
                    <span>{{ selectTable.amount}}</span>
                </el-form-item>
                <el-form-item label="单价" style="color:#0085ef!important">
                    <span>{{ selectTable.price}}</span>
                </el-form-item>
            </el-form>
            </el-dialog>
        </el-tab-pane>
    </el-tabs>
</template>

<script>

export default {
    data() {
        return {
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            materialData: [],
            orderData: [],
            selectTable: [],
            disvisibity1: false,
            disvisibity2: false,
            disvisibity_detail:false,
        }
    },
    mounted: function() {
        this.loading = true;

        this.fetchDatamaterial();
       this.fetchDataorder()

    },
    methods: {
        handleEdit(index, row) {
            this.selectTable = row;
            this.disvisibity1 = true;
        },
        handleEditorder(index, row) {
            this.selectTable = row;
            this.disvisibity2 = true;
        },
        handlecheck(index,row){
            this.selectTable=row;
            this.disvisibity_detail=true
        },
        confirmoutorder: function() {
            this.disvisibity2 = false;
            this.selectTable.status = "订单出库"
            this.$http.put(this.servicerurl + '/order' + '/' + this.selectTable.id, this.selectTable, {
                headers: {},
                emulateJSON: true
            }).then(function(response) {
                //getagain,save in total_localstorage
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
                            message: '确认成功',
                            type: 'success'
                        })
                        this.dialogFormVisible = false;
                    })
                console.log(response.data);
            }, function(response) {
                console.log(response);
            })
        },
        confirmout: function() {
            this.disvisibity1 = false;
            this.selectTable.status = "已出库"
            this.$http.put(this.servicerurl + '/materialout' + '/' + this.selectTable.id, this.selectTable, {
                headers: {},
                emulateJSON: true
            }).then(function(response) {
                //getagain,save in total_localstorage
                this.$http.get(this.servicerurl + '/materialout', {
                    pageIndex: this.pageIndex,
                    pageSize: this.pageSize
                }, {
                        headers: {},
                        emulateJSON: true
                    }).then(function(response) {
                        console.log(response.data);
                        this.$message({
                            showClose: true,
                            message: '确认成功',
                            type: 'success'
                        })
                        this.dialogFormVisible = false;
                    })
                console.log(response.data);
            }, function(response) {
                console.log(response);
            })
        },
        handleDelete(index, row) {
            console.log(index, row);
        },
        filterTag(value, row) {
            return row.class === value;
        },
        sizeChangem: function(pageSize) {
            this.pageSize = pageSize;
            this.fetchDatamaterial();
        },
        pageIndexChangem: function(pageIndex) {
            this.pageIndex = pageIndex;
            this.fetchDatamaterial();
        },
        sizeChangeo: function(pageSize) {
            this.pageSize = pageSize;
            this.fetchDataorder();
        },
        pageIndexChangeo: function(pageIndex) {
            this.pageIndex = pageIndex;
            this.fetchDataorder();
        },
        fetchDatamaterial: function() {
            this.loading = true;
            //材料出库
            this.$http.get(this.servicerurl + '/materialout', {
                pageIndex: this.pageIndex,
                pageSize: this.pageSize
            }, {
                    headers: {},
                    emulateJSON: true
                }).then(function(response) {
                    this.materialData = response.data;
                    this.total = this.materialData.length;
                    this.loading = false;
                    console.log(response.data)
                }, function(response) {
                    console.log(response)
                })


        },
        fetchDataorder: function() {
            this.loading = true;
            //订单出库

            
            this.$http.get(this.servicerurl + '/order?status=订单出库&&status=生产完成', {
                pageIndex: this.pageIndex,
                pageSize: this.pageSize
            }, {
                    headers: {},
                    emulateJSON: true
                }).then(function(response) {
                    this.orderData = response.data;
                    this.total = this.orderData.length;
                    this.loading = false;
                    console.log(response.data)
                }, function(response) {
                    console.log(response)
                })


        },
    }
}
</script>

<style>
.outtable {
    margin-left: 20px;
    margin-top: 20px;
}
</style>
