<template>
    <el-tabs type="border-card" class='outtable'>
        <el-tab-pane label="订单出库">
            <el-table :data="orderData" border style="width: 100%">
                <el-table-column label="订单批号">
                    <template scope="scope">
                        <p>{{ scope.row.wid}}</p>
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

            <!-- <el-dialog title="提示" v-model="disvisibity2" size="tiny">
                <span>确认出库完成</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible2 = false">取 消</el-button>
                    <el-button type="primary" @click="confirmoutorder">确 定</el-button>
                </span>
            </el-dialog> -->

              <!-- <el-dialog title="订单详情" v-model="disvisibity_detail" >
                
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
            </el-dialog> -->
        </el-tab-pane>
    </el-tabs>
</template>

<style>
.outtable {
    margin-left: 20px;
    margin-top: 20px;
}
</style>
<script>
export default {
    data() {
        return {
            orderData: [],
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
        };
    },
    mounted: function() {
        this.fetchDataorder();
    },
    methods: {
        sizeChangeo: function(pageSize) {
            this.pageSize = pageSize;
            this.fetchDataorder();
        },
        pageIndexChangeo: function(pageIndex) {
            this.pageIndex = pageIndex;
            this.fetchDataorder();
        },
        fetchDataorder: function() {
            this.loading = true;
            this.$http.get('/sp/warehouseRecord/listPage', {
                pageIndex: this.pageIndex,
                pageSize: this.pageSize
            }, {
                headers: {},
                emulateJSON: true
            }).then(function(response) {
                this.orderData = response.data.recordList;
                this.total = this.orderData.length;
                this.loading = false;
                    
            }, function(response) {
                  
            });
        }
    }
};
</script>