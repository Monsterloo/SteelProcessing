<template>
    <div class="clear">
        <el-input placeholder="请选择关键字" icon="search" class="searchbox" v-model="seachbyId">
        </el-input>
         <el-button type="primary" class="addbtn" @click="addAdmin">新 增 管 理 员</el-button> 
        <el-table :data="totaldata" border style="width: 100%;" class="ordertable">
            <el-table-column label="管理员编号">
                <template scope="scope">
                    <span style="margin-left: 10px;">{{ scope.row.aid }}</span>
                </template>
            </el-table-column>
            <el-table-column label="管理员名称">
                <template scope="scope">
                    <span> {{ scope.row.aname}}</span>
                </template>
            </el-table-column>
            <el-table-column label="联系方式">
                <template scope="scope">
                    <span> {{ scope.row.acontact}}</span>
                </template>
            </el-table-column>
            <el-table-column label='权限级别'>
                <template scope="scope">
                    <p>{{scope.row.aRoleName}}</p>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template scope="scope">
                    <el-button  size='mini' @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button  type="danger" size="mini" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChange" v-on:current-change="pageIndexChange" class="pagination">
        </el-pagination>
         <el-dialog title="修改管理员信息" v-model="dialogFormVisible_update">
                <el-form :model="selectTable" :rules="rules" ref="ruleForm">
                    <el-form-item label="管理员编号" label-width="100px">
                        <p>{{selectTable.aid}} </p>
                    </el-form-item>
                    <el-form-item label="管理员名称" label-width="100px" prop="aname">
                        <el-input v-model="selectTable.aname" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" label-width="100px" prop="contact">
                        <el-input v-model="selectTable.acontact"></el-input>
                    </el-form-item>
                    <el-form-item label="权限级别" label-width="100px" v-if='selectTable.aRoleName=="普通管理员"'>
                        <el-switch v-model="selectTable.arole" on-text="超级" off-text="普通" on-value='1' off-value='0'>
                        </el-switch>
                    </el-form-item>
                      <el-form-item label="权限级别" label-width="100px" v-if='selectTable.aRoleName=="超级管理员"'>
                       <p >超级管理员</p>
                    </el-form-item> 
                     
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_update = false">取 消</el-button>
                    <el-button type="primary" @click="updateadmin('ruleForm')">确 定</el-button>
                </div>
            </el-dialog>
             <el-dialog title="增加管理员" v-model="dialogFormVisible_add">
                <el-form :model="adminTable" :rules="rules_add" ref="ruleForm_add">
                    <el-form-item label="管理员账号" label-width="100px" prop="account">
                        <el-input v-model="adminTable.account" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="管理员名称" label-width="100px" prop="name">
                        <el-input v-model="adminTable.name" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="请输入密码" class="fontcolor" prop='password'>
                        <el-input v-model="adminTable.password" placeholder="请输入密码" type='password'></el-input>
                    </el-form-item>
                    <el-form-item label="请再次输入密码" class="fontcolor" prop='password1'>
                        <el-input v-model="adminTable.password1" placeholder="请再次输入密码" type='password'></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" prop="contact">
                        <el-input v-model="adminTable.contact"></el-input>
                    </el-form-item>
                    <el-form-item label="权限级别" class="fontcolor">
                        <!-- <el-select v-model="adminTable.access" placeholder="请选择" >
                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select> -->
                        <select v-model="adminTable.access" class="mySelect">
                            <option v-for="item in options" v-bind:value="item.value" v-bind:key="item.value" placeholder="请选择" >{{item.label}}</option>
                        </select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_add= false">取 消</el-button>
                    <el-button type="primary" @click="onSubmit('ruleForm_add')">确 定</el-button>
                </div>
            </el-dialog> 
 
            <el-dialog title="提示" v-model="dialogVisible_delete" size="tiny">
                <span>确认删除吗？</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible_delete= false">取 消</el-button>
                    <el-button type="primary" @click="confirmdelete">确 定</el-button>
                </span>
            </el-dialog> 
    </div>
</template>
<style>
.mySelect{
    width: 172px;
    height: 36px;
    border-color: #bfcbd9;
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
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            totaldata: [],
            tableData: [],
            seachbyId: '',
            matchdata: [],
            dialogFormVisible_add: false,
            dialogFormVisible_update:false,
            dialogVisible_delete: false,
            adminTable: [{
                account: '',
                name: '',
                contact: '',
                access: '',/*0 admin 1 super*/
                password1: '',
                password: '',
            }],
            selectTable: [{
                name: '',
                contact: '',
                access: 0,
            }],
            options: [{
                value: '1',
                label: '超级管理员'
            }, {
                value: '0',
                label: '普通管理员'
            }],
            rules_add: {
                account: [{required: true, message: '请输入管理员账号', trigger: 'blur'
                },
                { min: 4, max: 16, message: '长度4至16 个字符', trigger: 'blur' }],
                name: [{
                    required: true, message: '请输入管理员名称', trigger: 'blur'
                },
                { min: 4, max: 16, message: '长度4至16 个字符', trigger: 'blur' }],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 8, max: 15, message: '长度在8至15 个字符', trigger: 'blur' },

                ],
                contact: [
                    { required: true, message: '请输入管理员联系方式', trigger: 'blur' },
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
                ],
                password1: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 8, max: 15, message: '长度在8至15 个字符', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            if (value != this.adminTable.password) {
                                callback(new Error('请输入一致的密码'));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur'
                    }
                ]
            },
            rules: {
                acontact: [
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
                ],
                aname: [{ required: true, message: '请输入管理员姓名', trigger: 'blur' }]

            }
        };
    },
    mounted :function(){
        this.fetchData();
    },
    methods: {
        test: function() {
            console.log(this.adminTable.access);
        },
        fetchData: function() {
            this.loading = true;
            this.$http.get('/api/admin/listPage' + '/' + this.pageIndex + '/' + this.pageSize).then((response) => {
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
        addAdmin: function(){
            this.dialogFormVisible_add=true;
        },
        onSubmit: function(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    // let newadmin = {
                    let aaccount = this.adminTable.account;
                    let aname = this.adminTable.name;
                    console.log(aname);
                    let apwd = this.adminTable.password;
                    let arole = this.adminTable.access;/*0 admin 1 super*/
                    let aRoleName = '';
                    let acontact=this.adminTable.contact;
                    if(this.adminTable.access=='1'){
                        aRoleName = '超级管理员';
                    }else{
                        aRoleName = '普通管理员';
                    }
                    // };
                    this.$http.post('/api/admin/create', {aaccount:aaccount,aname:aname,apwd:apwd,arole:arole,aRoleName:aRoleName,acontact:acontact}, {
                        headers: {},
                        emulateJSON: true
                    }).then(function(response) {
                        this.fetchData();
                        this.$message({
                            showClose: true,
                            message: '添加成功',
                            type: 'success'
                        });
                        this.dialogFormVisible_add=false;
                        this.adminTable.account = '';
                        this.adminTable.name = '';
                        this.adminTable.password = '';
                        this.adminTable.password1 = '';
                        this.adminTable.contact = '';
                        this.adminTable.access = '';
                        console.log(response.data);
                    }, function(response) {
                        console.log(response);
                    });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleEdit: function($index, row) {
            this.selectTable = row;
            this.dialogFormVisible_update = true;
        },
        handleDelete: function($index, row) {
            this.selectTable = row;
            this.dialogVisible_delete = true;
        },
        confirmdelete: function() {
            this.dialogVisible_delete = false;
            this.$http.delete('/api/admin/delete/'+ this.selectTable.aid).then((response) => {
                console.log(response.data);
                this.$message({
                    showClose: true,
                    message: '删除成功',
                    type: 'success'
                });
                this.fetchData();
            }).then((response) => {
                console.log(response);
            });
        },
        updateadmin: function(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.dialogFormVisible = false;
                    let id = this.selectTable.aid;
                    let name = this.selectTable.aname;
                    let contact = this.selectTable.acontact;
                    let arole = this.selectTable.arole;
                    // let aRoleName = this.selectTable.aRoleName;
                    let aRoleName = '';
                    if(arole == '0'){
                        aRoleName='普通管理员';
                    }else if(arole =='1'){
                        aRoleName='超级管理员';
                    }
                    this.$http.patch('/api/admin/update?aid='+ id +'&aname='+ name +'&acontact='+ contact +'&arole='+ arole +'&aRoleName='+ aRoleName , {
                        headers: {},
                        emulateJSON: true
                    }).then(function(response) {
                        this.fetchData();
                        this.dialogFormVisible_update=false;
                        this.$message({
                            showClose: true,
                            message: '更新成功',
                            type: 'success'
                        });
                        
                    }, function(response) {
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
        }
    }
};
</script>
