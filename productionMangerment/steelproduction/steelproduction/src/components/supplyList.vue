<template>
    <el-tabs type="border-card" class='intab'>
        <el-tab-pane label=" 库存管理">
            <el-table :data="totaldata" style="width: 100%">
                <el-table-column type="expand">
                <template scope="scope">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="材料直径">
                            <span>{{ scope.row.mdiameter}}</span>
                        </el-form-item>
                        <el-form-item label="材料重量">
                            <span>{{ scope.row.mweight}}</span>
                        </el-form-item>
                        <el-form-item label="材料长度">
                            <span>{{ scope.row.mlength}}</span>
                        </el-form-item>  
                    </el-form>
                </template>
            </el-table-column>
                <el-table-column label="材料编号">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.mid }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="材料名称">
    
                    <template scope="scope">
                        <el-badge class="item" value="请及时进货" :hidden="Number(scope.row.mexistCount)<Number(scope.row.mminCount)-Number(scope.row.purchaseCount)?false:true">
                            <span style="margin-left: 10px">{{ scope.row.mname}}</span>
                            <el-tag type="primary" v-if="scope.row.order>0">采购{{scope.row.purchaseCount}}件中</el-tag>
                        </el-badge>
                    </template>
                </el-table-column>
                <el-table-column label="材料现存数量">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.mexistCount}}</span>
                    </template>
    
                </el-table-column>
                <el-table-column label="安全库存">
                    <template scope="scope">
                        <span>{{ scope.row.mminCount }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="tag" label="材料类别" :filters="[{ text: '原材料', value: 0 }, { text: '半成品', value: 1 },{ text: '成品', value: 2 }]" :filter-method="filterTag" filter-placement="bottom-end">
                    <template scope="scope">
                        <el-tag :type="scope.row.mcategory[0] === 0 ? 'primary' : 'success'" close-transition>
                             {{scope.row.mcategory_label}} 
                            </el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button type='text' @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                        <el-button type='text' @click="handleadd(scope.$index, scope.row)">采购</el-button>
                        <el-button type='text' class='dangeraction' @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                    </template>
                </el-table-column>
    
            </el-table>
            <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChangelist" v-on:current-change="pageIndexChangelist" class="pagination">
            </el-pagination>
    
             <el-dialog title="修改物料信息" v-model="dialogFormVisible_update">
                <el-form :model="selectTable"  ref="ruleForm">
                    <el-form-item label="材料编号" label-width="100px">
                        <p>{{selectTable.mid}} </p>
                    </el-form-item>
                    <el-form-item label="材料名称" label-width="100px">
                        <p>{{selectTable.mname}} </p>
                    </el-form-item>
                    <el-form-item label="材料数量" label-width="100px">
                        <p>{{selectTable.mexistCount}}</p>
                    </el-form-item>
                    <el-form-item label="安全库存" label-width="100px" prop="limit">
                         <p>{{selectTable.mminCount}} </p>
                        <el-input-number v-model="selectTable.mminCount"></el-input-number>
                    </el-form-item>
                    <el-form-item label="材料类别" label-width="100px">
                        <p>{{selectTable.mcategory_label}} </p>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_update = false">取 消</el-button>
                    <el-button type="primary" @click="updatematrial('ruleForm')">确 定</el-button>
                </div>
            </el-dialog> 
     
              <el-dialog title="申请采购物料信息" v-model="dialogFormVisible_in">
                <el-form :model="selectTable">
                    <el-form-item label="材料编号" label-width="100px">
                        <p>{{selectTable.mid}} </p>
                    </el-form-item>
                    <el-form-item label="材料名称" label-width="100px">
                        <p>{{selectTable.mname}} </p>
                    </el-form-item>
                    <el-form-item label="采购数量" label-width="100px">
                        <el-input-number v-model="selectTable.purchaseCount"></el-input-number>
                    </el-form-item>
                    <el-form-item label="材料类别" label-width="100px">
                        <p>{{selectTable.mcategory_label}} </p>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_in= false">取 消</el-button>
                    <el-button type="primary" @click="confirmadd">确 定</el-button>
                </div>
            </el-dialog>
    
            <el-dialog title="提示" v-model="dialogFormVisible_delte" size="tiny">
                <span>确认删除吗？</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_delte = false">取 消</el-button>
                    <el-button type="primary" @click="confirmdelete">确 定</el-button>
                </span>
            </el-dialog>  
        </el-tab-pane>
    
        <el-tab-pane label="入库记录">
             <el-table :data="addrecord" style="width: 100%">
                <el-table-column label="记录编号">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.wid}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="材料编号">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.mid }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="材料名称">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.mname }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="采购数量">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.purchaseCount}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="tag" label="材料类别" :filters="[{ text: '原材料', value: 0 }, { text: '半成品', value: 1 }]" :filter-method="filterTag" filter-placement="bottom-end">
                    <template scope="scope">
                        <el-tag :type="scope.row.materialCategory[0] === 0 ? 'primary' : 'success'" close-transition>{{scope.row.mcategory_label}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="采购状态">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.purchaseStateIndex}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button type='text' @click="handleupdatestatus(scope.$index, scope.row)" v-if='scope.row.purchaseStateIndex=="未入库"'>确认入库</el-button>
                    </template>
                </el-table-column>
                 
    
            </el-table>
            <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total_in" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChangeroder" v-on:current-change="pageIndexChangeorder" class="pagination">
            </el-pagination>


             <el-dialog title="提示" v-model="dialogFormVisible_confirmIn" size="tiny">
                <span>确认入库吗？</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible_confirmIn = false">取 消</el-button>
                    <el-button type="primary" @click="confirmconfirmIn">确 定</el-button>
                </span>
            </el-dialog> 
        </el-tab-pane>
    
        <el-tab-pane label="新增材料">
    
             <el-form class="formStyle form" :rules="rule" ref="ruleFormnew" :model="newtable">
    
                <el-form-item label="材料名称" class="fontcolor" prop='mname'>
                    <div class="forminput">
                        <el-input v-model="newtable.mname" placeholder="材料名称"></el-input>
                    </div>
                </el-form-item>
                 <el-form-item label="材料长度" class="fontcolor" >
                    <div class="forminput">
                         <el-input-number v-model="newtable.mlength" placeholder="材料长度" ></el-input-number> 
                    </div>
                </el-form-item>
                 <el-form-item label="材料直径" class="fontcolor" >
                    <div class="forminput">
                        <el-input-number v-model="newtable.mdiameter" placeholder="材料直径" ></el-input-number>
                    </div>
                </el-form-item>
                 <el-form-item label="材料重量" class="fontcolor" >
                    <div class="forminput">
                        <el-input-number v-model="newtable.mweight" placeholder="材料重量"></el-input-number>
                    </div>
                </el-form-item>
                <el-form-item label="材料入库数量" class="fontcolor">
                    <div class="forminput">
                        <el-input-number v-model="newtable.mexistCount" ></el-input-number>
                    </div>
                </el-form-item>
                <el-form-item label="材料最低数量" class="fontcolor">
                    <div class="forminput">
                        <el-input-number v-model="newtable.mminCount" ></el-input-number>
                    </div>
                </el-form-item>
                <el-form-item label="材料类别" class="fontcolor" prop='class'>
                    <div class="forminput">
                        <el-select v-model="newtable.mcategory" placeholder="请选择">
                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                            </el-option>
                        </el-select>
                    </div>
                </el-form-item>
                <el-form-item label-width="80px">
                    <el-button type="primary" @click="onSubmit('ruleFormnew')">立即添加</el-button>
                </el-form-item>
            </el-form> 
        </el-tab-pane>
    
    </el-tabs>
</template>

<style>
.form{
		min-width: 400px;
		margin-bottom: 30px;
		
		
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
.intab {
    margin-left: 20px;
    margin-top: 20px;
}

.item {
    margin-top: 10px;
    margin-right: 40px;
}

.dangeraction {
    color: red !important;
}

.forminput {
    width: 500px;
    display: inline-block;
    margin-top: 10px;
}

.fontcolor>label {
    color: black !important;
}

.formStyle {
    margin-left: 30px;
    background-color: white;
    padding: 10px;
    border-radius: 15px;
    margin-top: 40px;
}
</style>

<script>
export default {
    data (){
        return{
            totaldata:[],
            addrecord:[],
            mcategory_label: '',
            Materialist: [],
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            total_in: 0,
            pageSizes: [10, 20, 50, 100],
            dialogFormVisible_delte: false,
            dialogFormVisible_update:false,
            dialogFormVisible_in: false,
            dialogFormVisible_confirmIn: false,
            selectTable: [],
            newtable:{},
            options: [{
                value: 0,
                label: '原材料'
            }, {
                value: 1,
                label: '半成品'
            }],
            rule: {
                mname: [{
                    required: true, message: '请输入材料名称', trigger: 'blur'

                }],
                class: [{
                    required: true, message: '请选择材料种类', trigger: 'blur'

                }]
            }
        };
    },
    mounted :function(){
        this.fetchData_material();
        this.fetchData_warehouse();
    },
    methods: {
        fetchData_material: function() {
            this.$http.get('/api/material/listPage' + '/' + this.pageIndex + '/' + this.pageSize).then((response) => {
                console.log(response.data);
                this.totaldata=response.data.recordList;
                //务必提交去除label属性
                for(let i =0;i<this.totaldata.length;i++){
                    if(this.totaldata[i].mcategory == 0){
                        this.totaldata[i].mcategory_label='原材料 ';
                    }else if(this.totaldata[i].mcategory== 1){
                        this.totaldata[i].mcategory_label='半成品';
                    }else if(this.totaldata[i].mcategory==2){
                        this.totaldata[i].mcategory_label='成品';
                    }
                }
                this.total=response.data.total;
            }).then((response) => {
                console.log(response);
            });
        },
        fetchData_warehouse: function() {
            this.$http.get('/api/warehouseRecord/listPage' + '/' + this.pageIndex + '/' + this.pageSize).then((response) => {
                console.log(response.data);
                this.addrecord=response.data.recordList;
                //务必提交去除label属性
                for(let i =0;i<this.addrecord.length;i++){
                    if(this.addrecord[i].materialCategory == 0){
                        this.addrecord[i].mcategory_label='原材料 ';
                    }else if(this.addrecord[i].materialCategory== 1){
                        this.addrecord[i].mcategory_label='半成品';
                    }else if(this.addrecord[i].materialCategory==2){
                        this.addrecord[i].mcategory_label='成品';
                    }
                    this.$http.get('/api/material/getInfo/'+this.addrecord[i].mid).then((response) => {
                        this.addrecord[i].mname =  response.data.mname;
                    }).then((response) => {
                        console.log(response);
                    });
                }
                
                this.total_in=response.data.total;
            }).then((response) => {
                console.log(response);
            });
        },
        sizeChangelist: function(pageSize) {
            this.pageSize = pageSize;
            this.fetchData_material();
        },
        pageIndexChangelist: function(pageIndex) {
            this.pageIndex = pageIndex-1;
            console.log(this.pageIndex);
            this.fetchData_material();
        },
        sizeChangeorder: function(pageSize) {
            this.pageSize = pageSize;
            this.fetchData_warehouse();
        },
        pageIndexChangeorder: function(pageIndex) {
            this.pageIndex = pageIndex-1;
            console.log(this.pageIndex);
            this.fetchData_warehouse();
        },
        handleDelete: function($index, row) {
            this.selectTable = row;
            this.dialogFormVisible_delte = true;
        },
        handleupdatestatus: function($index, row) {
            this.selectTable = row;
            this.dialogFormVisible_confirmIn = true;
        },
        handleEdit: function($index, row) {
            this.selectTable = row;
            console.log(this.selectTable);
            this.dialogFormVisible_update = true;
        },
        handleadd: function($index, row) {
            this.selectTable = row;
            this.selectTable.purchaseCount=0;
            console.log(this.selectTable);
            this.dialogFormVisible_in = true;
        },
        filterTag(value, row) {
            return row.mcategory === value;
        },
        updatematrial: function(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    delete this.selectTable.mcategory_label;
                    this.dialogFormVisible_update = false;
                    let id = this.selectTable.mid;
                    let mminCount = this.selectTable.mminCount;
                    // this.$http.put('/api/material/update?mid='+ id +'&mminCount='+ mminCount , {
                    this.$http.post('/api/material/update',{mid:id,mminCount:mminCount},{
                        headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                        emulateJSON: true,
                        emulateHttp: true
                    }).then(function(response) {
                        this.fetchData_material();
                        this.dialogFormVisible_update=false;
                        this.$message({
                            showClose: true,
                            message: '更新成功',
                            type: 'success'
                        });
                        
                    }, function(response) {
                        this.fetchData_material();
                        this.$message({
                            showClose: true,
                            message: '更新材料信息失败',
                            type: 'warning'
                        });
                    });
                } else {
                    this.$message({
                        showClose: true,
                        message: '更新材料信息失败',
                        type: 'warning'
                    });
                    
                    return false;
                }
            });
        },
        confirmadd: function() {
            this.dialogFormVisible_delte = false;
            this.$http.post('/api/material/purchase?mid='+ this.selectTable.mid +'&mexistCount='+this.selectTable.purchaseCount).then((response) => {
                console.log(response.data);
                this.$message({
                    showClose: true,
                    message: '采购成功',
                    type: 'success'
                });
                this.fetchData_warehouse();
            }).then((response) => {
                this.fetchData_warehouse();
                console.log(response);
            });
        },
        confirmconfirmIn: function(){
            this.dialogFormVisible_confirmIn = false;
            this.$http.post('/api/warehouseRecord/confirm/'+ this.selectTable.wid).then((response) => {
                console.log(response.data);
                this.$message({
                    showClose: true,
                    message: '入库成功',
                    type: 'success'
                });
                this.fetchData_material();
            }).then((response) => {
                this.fetchData_material();
                console.log(response);
            });
        },
        confirmdelete: function() {
            this.dialogFormVisible_delte = false;
            this.$http.delete('/api/material/delete/'+ this.selectTable.mid).then((response) => {
                console.log(response.data);
                this.$message({
                    showClose: true,
                    message: '删除成功',
                    type: 'success'
                });
                this.fetchData_material();
            }).then((response) => {
                this.fetchData_material();
                console.log(response);
            });
        },
        onSubmit: function (formName) {
            this.$http.post('/api/material/create', {mname: this.newtable.mname,
                mlength:this.newtable.mlength,
                mdiameter:this.newtable.mdiameter,
                mexistCount:this.newtable.mexistCount,
                mminCount:this.newtable.mminCount,
                mcategory:this.newtable.mcategory,
                mweight:this.newtable.mweight}, {
                    headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'},
                    emulateJSON: true,
                    emulateHttp: true
                }).then(function(response) {
                    console.log(response);
                    this.fetchData_material();
                    this.$message({
                        showClose: true,
                        message: '添加成功',
                        type: 'success'
                    });
                }, function(response) {
                    console.log(response);
                    this.fetchData_material();
                });
        }
    }
};
</script>
