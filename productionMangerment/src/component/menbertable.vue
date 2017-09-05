
<template>
    <div class="clear">
        <el-input placeholder="请选择会员编号" icon="search" class="searchbox" v-model="seachbyId">
        </el-input>
        <el-button type="primary" class="addbtn" @click="addOrder">新 增 会 员</el-button>
        <el-table :data="filtermatchdata" border style="width: 100%;" class="ordertable" v-loading.body="loading">
            <el-table-column type="expand">
                <template scope="scope">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="联系方式">
                            <span>{{ scope.row.companycontact}}</span>
                        </el-form-item>
                        <el-form-item label="法人姓名">
                            <span>{{ scope.row.ownername}}</span>
                        </el-form-item>
                        <el-form-item label="公司地址">
                            <span>{{ scope.row.companyaddress}}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="会员编号">
                <template scope="scope">
                    <span style="margin-left: 10px;">{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column label="公司名称">
                <template scope="scope">
                    <p>{{ scope.row.companyname}}</p>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button @click="handleEdit(scope.$index, scope.row)" size="mini">编辑</el-button>
                    <el-button @click="handleDelete(scope.$index, scope.row)" size="mini" type="danger">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChange" v-on:current-change="pageIndexChange" class="pagination">
        </el-pagination>

        <el-dialog title="修改会员信息" v-model="dialogFormVisible">
            <el-form :model="selectTable" :rules="rules" ref="ruleForm">
                <el-form-item label="公司编号" label-width="100px">
                    <p>{{selectTable.id}} </p>
                </el-form-item>
                <el-form-item label="公司名称" label-width="100px" prop="companyname">
                    <el-input v-model="selectTable.companyname" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="详细地址" label-width="100px" prop="companyaddress">
                    <v-distpicker @selected="onSelected"></v-distpicker>
                    <br>
                    <el-input v-model="selectTable.companyaddress2" placeholder="请输入详细地址" style="width: 100%;"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" label-width="100px" prop="companycontact">
                    <el-input v-model="selectTable.companycontact"></el-input>
                </el-form-item>
                <el-form-item label="法人姓名" label-width="100px" prop="ownername">
                    <el-input v-model="selectTable.ownername"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updatemenber('ruleForm')">确 定</el-button>
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
var STORAGE_KEY = 'menberList';
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
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            totaldata: [],
            tableData: [],
            seachbyId: '',
            matchdata: [],
            id: '',
            loading: false,
            selectTable: [{
                name: '',
                companyaddress: '',
                companycontact: '',
                ownername: '',
                companyaddress1:'',
                companyaddress2:'',
            }],
            dialogFormVisible: false,
            dialogVisible: false,
            // offset: 0,
            // limit: 20,//每页行数
            // count: 0,//总条数
            // currentPage: 1,//当前页码
            rules: {
                companyname: [
                    { required: true, message: '请输入公司名称', trigger: 'blur' },
                    { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }
                ],
                companyaddress: [{ required: true, message: '请输入公司地址', trigger: 'blur' },
                { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }],
                ownername: [{ required: true, message: '请输入公司地址', trigger: 'blur' }],
                companycontact: [
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
                ]

            }
        }
    },
    mounted: function() {
        this.loading = true;
        this.$http.get(this.servicerurl + '/menber', {
            pageIndex: this.pageIndex,
            pageSize: this.pageSize
        }, {
                headers: {},
                emulateJSON: true
            }).then(function(response) {
                orderStorage.save(response.data);
                this.tableData = orderStorage.fetch();

                this.loading = false;
                //this.total=this.filtermatchdata.length;
                console.log(response.data)
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

    },
    methods: {
        onSelected: function(data) {
            this.selectTable.companyaddress1 = data.province.value + data.city.value + data.area.value
            console.log(this.selectTable.companyaddress1)
        },
        handleDelete: function($index, row) {
            this.selectTable = row;
            this.dialogVisible = true;

        },
        confirmdelete: function() {
            var id1 = this.selectTable.id;
            //resource
            var resource = this.$resource(this.servicerurl + '/menber/{id}');
            resource.delete({ id: id1 }).then(response => {
                // success callback
                this.$http.get(this.servicerurl + '/menber', {
                    pageIndex: this.pageIndex,
                    pageSize: this.pageSize
                }, {
                        headers: {},
                        emulateJSON: true
                    }).then(function(response) {
                        //get again
                        orderStorage.save(response.data);
                        this.tableData = response.data;
                        this.dialogVisible = false;
                        this.$message({
                            showClose: true,
                            message: '删除成功',
                            type: 'success'
                        })
                        console.log(response.data);
                    }, function(response) {
                        console.log(response)
                    })
                console.log(id1);
                console.log(response.data);
            }, response => {
                // error callback
                console.log(id1);
                console.log(response);
                this.$message({
                    showClose: true,
                    message: '删除失败',
                    type: 'error'
                })
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
            this.$router.push({ path: '/menber/add' })
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
            this.$http.get(this.servicerurl + '/menber', {
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
        handleEdit: function($index, row) {
            this.selectTable = row;
            this.dialogFormVisible = true;
        },
        updatemenber: function(formName, row) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.selectTable.companyaddress=this.selectTable.companyaddress1+this.selectTable.companyaddress2;
                    this.dialogFormVisible = false;
                    this.$http.put(this.servicerurl + '/menber' + '/' + this.selectTable.id, this.selectTable, {
                        headers: {},
                        emulateJSON: true
                    }).then(function(response) {
                        //getagain,save in total_localstorage
                        this.$http.get(this.servicerurl + '/menber', {
                            pageIndex: this.pageIndex,
                            pageSize: this.pageSize
                        }, {
                                headers: {},
                                emulateJSON: true
                            }).then(function(response) {
                                console.log(response.data);
                                // localStorage.setItem('orderList',JSON.stringify(response.data)); 
                                orderStorage.save(response.data);
                                console.log(this.selectTable.companyaddress)
                                this.$message({
                                    showClose: true,
                                    message: '更新成功',
                                    type: 'success'
                                })
                            })
                        console.log(response.data);
                    }, function(response) {
                        console.log(response);
                    })
                } else {
                    this.$message({
                        showClose: true,
                        message: '更新会员信息失败',
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
