
<template>
    <div class="clear">
        <el-input placeholder="请选择关键字" icon="search" class="searchbox" v-model="seachbyId">
        </el-input>
        <el-table :data="filtermatchdata" border style="width: 100%;" class="ordertable">
            <el-table-column label="管理员编号" width="180">
                <template scope="scope">
                    <span style="margin-left: 10px;">{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column label="管理员名称">
                <template scope="scope">
                    <span> {{ scope.row.name}}</span>
                </template>
            </el-table-column>
            <el-table-column label="联系方式">
                <template scope="scope">
                    <span> {{ scope.row.contact}}</span>
                </template>
            </el-table-column>
    
            <el-table-column label='权限级别'>
                <template scope="scope">
                    <!-- <p>{{ scope.row.access}}</p> -->
                    <p v-if='scope.row.access==0'>一般管理员</p>
                    <p v-if='scope.row.access==1'>超级管理员</p>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button @click="handleEdit(scope.$index, scope.row)" size='mini'>编辑</el-button>
                    <el-button @click="handleDelete(scope.$index, scope.row)" type="danger" size="mini">删除</el-button>
                </template>
            </el-table-column>
    
        </el-table>
        <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChange" v-on:current-change="pageIndexChange" class="pagination">
    
        </el-pagination>
    
        <el-dialog title="修改管理员信息" v-model="dialogFormVisible">
            <el-form :model="selectTable" :rules="rules" ref="ruleForm">
                <el-form-item label="管理员编号" label-width="100px">
                    <p>{{selectTable.id}} </p>
                </el-form-item>
                <el-form-item label="管理员名称" label-width="100px" prop="name">
                    <el-input v-model="selectTable.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" label-width="100px" prop="contact">
                    <el-input v-model="selectTable.contact"></el-input>
                </el-form-item>
                <el-form-item label="权限级别" label-width="100px" >
                    <el-switch v-model="selectTable.access" on-text="超级" off-text="普通" on-value=1 off-value=0 :change="test" v-if='selectTable.access==0'>
                    </el-switch>
                    <p v-if='selectTable.access==1'>超级管理员</p>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateadmin('ruleForm')">确 定</el-button>
            </div>
        </el-dialog>
    
        <el-dialog title="提示" v-model="dialogVisible" size="tiny">
            <span>确认删除吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="confirmdelete">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
var STORAGE_KEY = 'userList';
var orderStorage = {
    save: function (data) {
        localStorage.setItem(STORAGE_KEY, JSON.stringify(data))
    },
    fetch: function () {
        return JSON.parse(localStorage.getItem(STORAGE_KEY) || []);
    }
};


export default {
    data() {
        return {
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            selectTable: [{
                name: '',
                contact: '',
                access: 0,

            }],
            totaldata: [],
            tableData: [],
            seachbyId: '',
            matchdata: [],
            id: '',
            dialogFormVisible: false,
            dialogVisible: false,
            rules: {
                contact: [
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
                ],
                name: [{ required: true, message: '请输入管理员姓名', trigger: 'blur' }]

            }
        }
    },

    mounted: function () {
        this.$http.get(this.servicerurl+'/user', {
            pageIndex: this.pageIndex,
            pageSize: this.pageSize
        }, {
                headers: {},
                emulateJSON: true
            }).then(function (response) {
                orderStorage.save(response.data);
                this.tableData = orderStorage.fetch();
                console.log(response.data)
            }, function (response) {
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
        filtermatchdata: function () {
            let matchdata = this.searchFilter('id', this.seachbyId, this.tableData);
            return matchdata;
        },

    },
    methods: {
        test: function () {
            console.log(this.selectTable.access)
        },
        handleDelete: function ($index, row) {
            
            this.selectTable = row;
            this.dialogVisible = true;
        },
        confirmdelete:function(){
       
            //resource
            var resource = this.$resource(this.servicerurl+'/user/{id}');
            resource.delete({ id: this.selectTable.id }).then(response => {
                // success callback
                this.$http.get(this.servicerurl+'/user', {pageIndex: this.pageIndex,
                    pageSize: this.pageSize}, {
                    headers: {},
                    emulateJSON: true
                }).then(function (response) {
                    //get again
                    this.dialogVisible=false;
                    this.$message({
                        showClose: true,
                        message: '删除成功',
                        type: 'success'
                    })
                    orderStorage.save(response.data);
                    this.tableData = response.data;
                    console.log(response.data);
                }, function (response) {
                    console.log(response)
                })
                
                console.log(response.data);
            }, response => {
                // error callback
                this.$message({
                        showClose: true,
                        message: '删除失败',
                        type: 'error'
                    })
                console.log(response);
            })
        },
        sizeChange: function (pageSize) {
            this.pageSize = pageSize;
            this.fetchData();
        },
        pageIndexChange: function (pageIndex) {
            this.pageIndex = pageIndex;
            this.fetchData();
        },
        fetchData: function () {
            this.loading = true;
            this.$http.get(this.servicerurl+'/user', {
                pageIndex: this.pageIndex,
                pageSize: this.pageSize
            }, {
                    headers: {},
                    emulateJSON: true
                }).then(function (response) {
                    orderStorage.save(response.data);
                    this.tableData = orderStorage.fetch();
                    this.loading = false;
                    console.log(response.data)
                }, function (response) {
                    console.log(response)
                })
        },
        searchFilter: function (prop, key, arr) {
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

        handleEdit: function ($index, row) {
            this.selectTable = row;
            this.dialogFormVisible = true;
        },
        updateadmin: function (formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.dialogFormVisible = false;
                    this.$http.put(this.servicerurl+'/user' + '/' + this.selectTable.id, this.selectTable, {
                        headers: {},
                        emulateJSON: true
                    }).then(function (response) {
                        //getagain,save in total_localstorage
                        this.$http.get(this.servicerurl+'/user', {
                            pageIndex: this.pageIndex,
                            pageSize: this.pageSize
                        }, {
                                headers: {},
                                emulateJSON: true
                            }).then(function (response) {
                                console.log(response.data);
                                // localStorage.setItem('orderList',JSON.stringify(response.data)); 
                                orderStorage.save(response.data);

                                this.$message({
                                    showClose: true,
                                    message: '更新成功',
                                    type: 'success'
                                })
                            })
                        console.log(response.data);
                    }, function (response) {
                        console.log(response);
                    })
                } else {
                    this.$message({
                        showClose: true,
                        message: '更新管理员信息失败',
                        type: 'warning'
                    })
                    console.log('error submit!!');
                    return false;
                }
            });
        }

    }
}
</script>
<style>
.ordertable {
    top: 20px !important;
    font-size: 12px;
    margin-left: 20px;
}

.searchbox {
    margin-top: 20px;
    width: 30%;
    margin-left: 20px !important;
}

.addbtn {
    background-color: #ff9800 !important;
    float: right;
    margin-top: 20px;
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
</style>
