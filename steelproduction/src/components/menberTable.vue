
<template>
    <div class="clear">
        <el-input placeholder="请选择会员编号" icon="search" class="searchbox" v-model="seachbyId">
        </el-input>
        <el-button type="primary" class="addbtn" @click="addOrder">新 增 会 员</el-button>
        <el-table :data="totaldata" border style="width: 100%;" class="ordertable" >
            <el-table-column type="expand">
                <template scope="scope">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="联系方式">
                            <span>{{ scope.row.ccontact}}</span>
                        </el-form-item>
                        <el-form-item label="法人姓名">
                            <span>{{ scope.row.clegalperson}}</span>
                        </el-form-item>
                        <el-form-item label="公司地址">
                            <span>{{ scope.row.caddress}}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column label="会员编号">
                <template scope="scope">
                    <span style="margin-left: 10px;">{{ scope.row.cid }}</span>
                </template>
            </el-table-column>
            <el-table-column label="公司名称">
                <template scope="scope">
                    <p>{{ scope.row.cname}}</p>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    
                    <el-button  size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    
                    <el-button  size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChange" v-on:current-change="pageIndexChange" class="pagination">
        </el-pagination>
         <el-dialog title="修改会员信息" v-model="dialogFormVisible_update">
            <el-form :model="selectTable" :rules="rules" ref="ruleForm">
                <el-form-item label="公司编号" label-width="100px">
                    <p>{{selectTable.cid}} </p>
                </el-form-item>
                <el-form-item label="公司名称" label-width="100px" prop="cname">
                    <el-input v-model="selectTable.cname" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="详细地址" label-width="100px" prop="companyaddress2">
                    <v-distpicker @selected="onSelected_update"></v-distpicker>
                    <br>
                     <el-input v-model="selectTable.companyaddress2" placeholder="请输入详细地址" style="width: 100%;"></el-input> 
                    <!-- <el-input  placeholder="请输入详细地址" style="width: 100%;"></el-input> -->

                </el-form-item>
                <el-form-item label="联系电话" label-width="100px" prop="companycontact">
                    <el-input v-model="selectTable.ccontact"></el-input>
                </el-form-item>
                <el-form-item label="法人姓名" label-width="100px" prop="clegalperson">
                    <el-input v-model="selectTable.clegalperson"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible_update = false">取 消</el-button>
                <el-button type="primary" @click="updatemenber('ruleForm')">确 定</el-button>
            </div>
        </el-dialog> 

          <el-dialog title="新增会员" v-model="dialogFormVisible_add">
            <el-form :model="clientInfo" :rules="rules_add" ref="ruleForm">
                <el-form-item label="公司名称" label-width="100px" prop="companyname">
                    <el-input v-model="clientInfo.companyname" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="详细地址" label-width="100px" prop="companyaddress2">
                      <v-distpicker @selected="onSelected_add"></v-distpicker>  
                     <!-- <v-distpicker :placeholders="placeholders"></v-distpicker> -->
                    <br>
                    <el-input v-model="clientInfo.companyaddress2" placeholder="请输入详细地址" style="width: 100%;"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" label-width="100px" prop="companycontact">
                    <el-input v-model="clientInfo.companycontact"></el-input>
                </el-form-item>
                <el-form-item label="法人姓名" label-width="100px" prop="ownername">
                    <el-input v-model="clientInfo.ownername"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible_add = false">取 消</el-button>
                <el-button type="primary" @click="addmenber('ruleForm')">确 定</el-button>
            </div>
        </el-dialog> 

         <el-dialog title="提示" v-model="dialogVisible_delete" size="tiny">
            <span>确认删除吗？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_delete = false">取 消</el-button>
                <el-button type="primary" @click="confirmdelete">确 定</el-button>
            </span>
        </el-dialog> 
    </div>
</template>
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
<script>
export default {
    data (){
        return{
            //地址暂存
            provincetemp: '',
            citytemp: '',
            area: '',
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            totaldata: [],
            clientInfo:[],
            selectTable: [],
            dialogFormVisible_add:false,
            dialogFormVisible_update:false,
            dialogVisible_delete:false,
            rules_add: {
                companyname: [
                    { required: true, message: '请输入公司名称', trigger: 'blur' },
                    { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }
                ],
                companyaddress2: [{ required: true, message: '请输入公司详细地址', trigger: 'blur' },
                { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }],
                ownername: [{ required: true, message: '请输入法人姓名', trigger: 'blur' }],
                companycontact: [
                    { required: true, message: '请输入负责人联系方式', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            if (/^1[34578]\d{9}$/.test(value) == false) {
                                callback(new Error('请输入正确的手机号码'));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur'
                    }
                ]
            },
            rules: {
                cname: [
                    { required: true, message: '请输入公司名称', trigger: 'blur' },
                    { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }
                ],
                companyaddress2: [{ required: true, message: '请输入公司地址', trigger: 'blur' },
                { min: 3, max: 25, message: '长度少于25 个字符', trigger: 'blur' }],
                clegalperson: [{ required: true, message: '请输入公司地址', trigger: 'blur' }],
                ccontact: [
                    { required: true, message: '请输入负责人联系方式', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            if (/^1[34578]\d{9}$/.test(value) == false) {
                                callback(new Error('请输入正确的手机号码'));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur'
                    }
                ]

            }
        };
    },
    mounted :function(){
        this.fetchData();
    },
    methods: {
        fetchData: function() {
            this.$http.get('/sp/client/listPage' + '/' + this.pageIndex + '/' + this.pageSize).then((response) => {
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
            console.log(this.pageIndex);
            this.fetchData();
        },
        handleEdit :function($index, row) {
            this.dialogFormVisible_update=true;
            let arrtemp=row.caddress.split('-');
            this.provincetemp=arrtemp[0];
            this.citytemp=arrtemp[1];
            this.arrtemp=arrtemp[2];
            this.selectTable = row;
            console.log(this.selectTable);
        },
        handleDelete: function($index, row) {
            this.selectTable = row;
            this.dialogVisible_delete = true;
        },
        addOrder: function() {
            this.dialogFormVisible_add=true;
        },
        onSelected_add: function(data) {
            this.clientInfo.companyaddress1 = data.province.value + '-' + data.city.value + '-' + data.area.value;
            console.log(this.clientInfo.companyaddress1);
        },
        onSelected_update: function(data) {
            this.selectTable.companyaddress1 = data.province.value + '-' + data.city.value + '-' + data.area.value;
        },
        addmenber: function(formName) {
            // this.selectTable.companyaddress1 = data.province.value + data.city.value + data.area.value;
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.$http.post('/sp/client/create', {cname: this.clientInfo.companyname,
                        caddress:this.clientInfo.companyaddress1+ '-' + this.clientInfo.companyaddress2,
                        ccontact:this.clientInfo.companycontact,
                        clegalperson:this.clientInfo.ownername}, {
                            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                            emulateJSON: true,
                            emulateHttp: true
                        }).then(function(response) {
                            console.log(response);
                            this.fetchData();
                            this.$message({
                                showClose: true,
                                message: '添加成功',
                                type: 'success'
                            });
                            this.dialogFormVisible_add=false;
                        }, function(response) {
                            console.log(response);
                        });
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        updatemenber: function(formName) {
            console.log(this.selectTable.companyaddress1);
            console.log(this.selectTable.companyaddress2);
            if(this.selectTable.companyaddress1==undefined){
                this.selectTable.companyaddress1 = this.provincetemp + '-' + this.citytemp + '-' + this.arrtemp;
            }
            console.log(this.selectTable.companyaddress1);
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.selectTable.caddress = this.selectTable.companyaddress1 + this.selectTable.companyaddress2;
                    console.log(this.selectTable);
                    this.dialogFormVisible_update = false;                  
                    this.$http.post('/sp/client/update',this.selectTable,{
                        headers: {},
                        emulateJSON: true,   
                    }).then(function(response) {
                        this.fetchData();
                        this.dialogFormVisible_update=false;
                        this.$message({
                            showClose: true,
                            message: '更新成功',
                            type: 'success'
                        });
                        
                    }, function(response) {
                        this.fetchData();
                        this.$message({
                            showClose: true,
                            message: '更新管理员信息失败',
                            type: 'warning'
                        });
                    });
                } else {
                    this.$message({
                        showClose: true,
                        message: '更新管理员信息失败',
                        type: 'warning'
                    });
                    
                    return false;
                }
            });
        },
        confirmdelete: function() {
            this.dialogVisible_delete = false;
            this.$http.delete('/sp/client/delete/'+ this.selectTable.cid).then((response) => {
                console.log(response.data);
                this.$message({
                    showClose: true,
                    message: '删除成功',
                    type: 'success'
                });
                this.fetchData();
            }).then((response) => {
                this.fetchData();
                console.log(response);
            });
        },
        
    }
};
</script>
