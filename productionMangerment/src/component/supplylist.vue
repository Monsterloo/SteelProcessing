<template>
    <el-tabs type="border-card" class='intab'>
        <el-tab-pane label=" 库存管理">
            <el-table :data="material" style="width: 100%">
                <el-table-column type="expand">
                <template scope="scope">
                    <el-form label-position="left" inline class="demo-table-expand">
                        <el-form-item label="材料直径">
                            <span>{{ scope.row.dim}}</span>
                        </el-form-item>
                        <el-form-item label="材料重量">
                            <span>{{ scope.row.weight}}</span>
                        </el-form-item>
                        <el-form-item label="材料长度">
                            <span>{{ scope.row.length}}</span>
                        </el-form-item>
                        
                    </el-form>
                </template>
            </el-table-column>
                <el-table-column label="材料编号">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.id }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="材料名称">
    
                    <template scope="scope">
                        <el-badge class="item" value="请及时进货" :hidden="Number(scope.row.amount)<Number(scope.row.limit)-Number(scope.row.order)?false:true">
                            <span style="margin-left: 10px">{{ scope.row.name }}</span>
                            <el-tag type="primary" v-if="scope.row.order>0">采购{{scope.row.order}}件中</el-tag>
                        </el-badge>
                    </template>
                </el-table-column>
                <el-table-column label="材料现存数量">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.amount}}</span>
                    </template>
    
                </el-table-column>
                <el-table-column label="安全库存">
                    <template scope="scope">
                        <span>{{ scope.row.limit }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="tag" label="材料类别" :filters="[{ text: '原材料', value: '原材料' }, { text: '半成品', value: '半成品' },{ text: '成品', value: '成品' }]" :filter-method="filterTag" filter-placement="bottom-end">
                    <template scope="scope">
                        <el-tag :type="scope.row.class[0] === '原材料' ? 'primary' : 'success'" close-transition>{{scope.row.class}}</el-tag>
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
    
            <el-dialog title="修改物料信息" v-model="dialogFormVisible">
                <el-form :model="selectTable" :rules="rules" ref="ruleForm">
                    <el-form-item label="材料编号" label-width="100px">
                        <p>{{selectTable.id}} </p>
                    </el-form-item>
                    <el-form-item label="材料名称" label-width="100px">
                        <p>{{selectTable.name}} </p>
                    </el-form-item>
                    <el-form-item label="材料数量" label-width="100px">
                        <p>{{selectTable.amount}}</p>
                    </el-form-item>
                    <el-form-item label="安全库存" label-width="100px" prop="limit">
                        <!-- <p>{{selectTable.limit}} </p> -->
                        <el-input-number v-model="selectTable.limit"></el-input-number>
                    </el-form-item>
                    <el-form-item label="材料类别" label-width="100px">
                        <p>{{selectTable.class}} </p>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="updatematrial('ruleForm')">确 定</el-button>
                </div>
            </el-dialog>
    
            <el-dialog title="申请采购物料信息" v-model="dialogFormVisiblein">
                <el-form :model="addtable">
                    <el-form-item label="材料编号" label-width="100px">
                        <p>{{addtable.id}} </p>
                    </el-form-item>
                    <el-form-item label="材料名称" label-width="100px">
                        <p>{{addtable.name}} </p>
                    </el-form-item>
                    <el-form-item label="采购数量" label-width="100px">
                        <!-- <p>{{selectTable.amount}}</p> -->
                        <el-input-number v-model="addtable.order"></el-input-number>
                    </el-form-item>
                    <el-form-item label="材料类别" label-width="100px">
                        <p>{{addtable.class}} </p>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisiblein= false">取 消</el-button>
                    <el-button type="primary" @click="addmatrial">确 定</el-button>
                </div>
            </el-dialog>
    
            <el-dialog title="提示" v-model="dialogVisible" size="tiny">
                <span>确认删除吗？</span>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="confirmdelete">确 定</el-button>
                </span>
            </el-dialog>
        </el-tab-pane>
    
        <el-tab-pane label="入库记录">
            <el-table :data="addrecord" style="width: 100%">
                <el-table-column label="记录编号">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.orderid }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="材料编号">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.id }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="材料名称">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.name }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="采购数量">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.order}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="tag" label="材料类别" :filters="[{ text: '原材料', value: '原材料' }, { text: '半成品', value: '半成品' }]" :filter-method="filterTag" filter-placement="bottom-end">
                    <template scope="scope">
                        <el-tag :type="scope.row.class[0] === '原材料' ? 'primary' : 'success'" close-transition>{{scope.row.class}}</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="采购状态">
                    <template scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.status}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作">
                    <template scope="scope">
                        <el-button type='text' @click="handleupdatestatus(scope.$index, scope.row)" v-if='scope.row.status=="待入库"'>确认入库</el-button>
                    </template>
                </el-table-column>
    
            </el-table>
            <el-pagination v-bind:current-Page="pageIndex" v-bind:page-size="pageSize" :total="total" layout="total,sizes,prev,pager,next,jumper" v-bind:page-sizes="pageSizes" v-on:size-change="sizeChangeroder" v-on:current-change="pageIndexChangeorder" class="pagination">
            </el-pagination>
        </el-tab-pane>
    
        <el-tab-pane label="新增材料">
    
            <el-form class="formStyle form" :rules="rule" ref="ruleFormnew" :model="newtable">
    
                <el-form-item label="材料名称" class="fontcolor" prop='name'>
                    <div class="forminput">
                        <el-input v-model="newtable.name" placeholder="材料名称"></el-input>
                    </div>
                </el-form-item>
                 <el-form-item label="材料长度" class="fontcolor" >
                    <div class="forminput">
                        <el-input v-model="newtable.length" placeholder="材料长度"></el-input>
                    </div>
                </el-form-item>
                 <el-form-item label="材料直径" class="fontcolor" >
                    <div class="forminput">
                        <el-input v-model="newtable.dim" placeholder="材料直径"></el-input>
                    </div>
                </el-form-item>
                 <el-form-item label="材料重量" class="fontcolor" >
                    <div class="forminput">
                        <el-input v-model="newtable.weight" placeholder="材料重量"></el-input>
                    </div>
                </el-form-item>
                <el-form-item label="材料入库数量" class="fontcolor">
                    <div class="forminput">
                        <el-input-number v-model="newtable.amount" :min="0"></el-input-number>
                    </div>
                </el-form-item>
                <el-form-item label="材料最低数量" class="fontcolor">
                    <div class="forminput">
                        <el-input-number v-model="newtable.limit" :min="0"></el-input-number>
                    </div>
                </el-form-item>
                <el-form-item label="材料类别" class="fontcolor" prop='class'>
                    <div class="forminput">
                        <el-select v-model="newtable.class" placeholder="请选择">
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

<script>
export default {
    data() {
        return {
            addrecord: [{
                orderid: '',
                id: '',
                class: '',
                name: '',
                status: '',
                order: 0,
            }],/*入库记录，待入库和已入库*/
            addtable: [],
            dialogVisible: false,
            dialogFormVisiblein: false,
            dialogFormVisible: false,
            selectTable: [

            ],
            pageIndex: 0,
            pageSize: 10,
            total: 0,
            pageSizes: [10, 20, 50, 100],
            material: [{
                id: '',
                class: '',/*['原材料','半成品','成品']*/
                amount: 0,
                limit: 0,
                name: '',
                order: 0,
                weight:0,
                length:0,
                dim:0,
            }],/*库存管理*/
            newtable: {
                class: '',
                amount: 0,
                limit: 0,
                name: '',
                weight:0,
                length:0,
                dim:0,
            },
            options: [{
                value: '原材料',
                label: '原材料'
            }, {
                value: '半成品',
                label: '半成品'
            }],
            rules: {
                limit: [
                    // { required: true, message: '请输入最低限量', trigger: 'blur' },
                    {
                        validator: (rule, value, callback) => {
                            if (/^\+?[1-9]\d*$/.test(value) == false) {
                                callback(new Error("请输入大于0的正整数"));
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur'
                    }
                ]
            },
            rule: {
                name: [{
                    required: true, message: '请输入材料名称', trigger: 'blur'

                }],
                class: [{
                    required: true, message: '请选择材料种类', trigger: 'blur'

                }]
            }
        };
    },
    mounted: function () {
        this.loading = true;
        this.fetchDatalist();
        this.fetchDataorder();


    },
    methods: {
        onSubmit: function (formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let newin = {
                        name: this.newtable.name,
                        class: this.newtable.class,
                        amount: Number(this.newtable.amount),
                        limit:Number(this.newtable.limit),
                        order:0,
                        dim:Number(this.newtable.dim),
                        weight:Number(this.newtable.weight),
                        length:Number(this.newtable.length),
                    }
                   
                    this.$http.post(this.servicerurl+'/material', newin, {
                        headers: {},
                        emulateJSON: true
                    }).then(function (response) {
                        this.$message({
                            showClose: true,
                            message: '添加成功',
                            type: 'success'
                        })
                        this.fetchDatalist();
                        this.newtable.name = "";
                        this.newtable.class = "";
                        this.newtable.amount = 0;
                        this.newtable.limit = 0;
                        console.log(response.data);
                    }, function (response) {
                        console.log(response);
                        this.$message({
                            showClose: true,
                            message: '添加失败',
                            type: 'success'
                        })
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        addmatrial: function () {
            //this.dialogFormVisible = false;
            //this.$http.put(this.servicerurl+'/material' + '/' + this.addtable.id, this.addtable, {
            let newaddtable = this.addtable;
            //记得改url
            // newaddtable.orderid = this.addtable.id;
            this.$http.post(this.servicerurl+'/ordermaterial', newaddtable, {
                headers: {},
                emulateJSON: true
            }).then(function (response) {

                //console.log(this.addrecord);
                //this.addrecord.push(this.addtable);
                //this.addrecord.push(this.addtable);
                this.fetchDataorder();
                console.log(this.addrecord);
                //getagain,save in total_localstorage
                this.$http.get(this.servicerurl+'/material', {
                    pageIndex: this.pageIndex,
                    pageSize: this.pageSize
                }, {
                        headers: {},
                        emulateJSON: true
                    }).then(function (response) {
                        console.log(response.data);
                        this.$message({
                            showClose: true,
                            message: '采购申请成功',
                            type: 'success'
                        })
                        console.log(this.addtable.order)
                        this.dialogFormVisiblein = false;
                    })
                console.log(response.data);


            }, function (response) {
                console.log(response);
            })
        },
        handleadd: function ($index, row) {
            this.dialogFormVisiblein = true;
            this.addtable = row;
            this.addtable.status = "待入库"
            //this.addrecord.push(this.addtable);
            console.log(this.addrecord);
        },
        confirmdelete: function () {
            var id1 = this.selectTable.id;
            //resource
            var resource = this.$resource(this.servicerurl+'/material/{id}');
            resource.delete({ id: id1 }).then(response => {
                // success callback
                this.$http.get(this.servicerurl+'/material', {
                    pageIndex: this.pageIndex,
                    pageSize: this.pageSize
                }, {
                        headers: {},
                        emulateJSON: true
                    }).then(function (response) {
                        //get again

                        this.material = response.data;
                        this.dialogVisible = false;
                        this.$message({
                            showClose: true,
                            message: '删除成功',
                            type: 'success'
                        })
                        console.log(response.data);
                    }, function (response) {
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
        handleDelete($index, row) {
            this.selectTable = row;
            this.dialogVisible = true;
        },
        handleClick(tab, event) {
            console.log(tab, event);
        },
        updatematrial(formName, row) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.dialogFormVisible = false;
                    this.$http.put(this.servicerurl+'/material' + '/' + this.selectTable.id, this.selectTable, {
                        headers: {},
                        emulateJSON: true
                    }).then(function (response) {
                        //getagain,save in total_localstorage
                        this.$http.get(this.servicerurl+'/material', {
                            pageIndex: this.pageIndex,
                            pageSize: this.pageSize
                        }, {
                                headers: {},
                                emulateJSON: true
                            }).then(function (response) {
                                console.log(response.data);
                                this.$message({
                                    showClose: true,
                                    message: '更新成功',
                                    type: 'success'
                                })
                                this.dialogFormVisible = false;
                            })
                        console.log(response.data);
                    }, function (response) {
                        console.log(response);
                    })
                } else {
                    this.$message({
                        showClose: true,
                        message: '更新信息失败',
                        type: 'warning'
                    })
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        filterTag(value, row) {
            return row.class === value;
        },
        handleEdit($index, row) {
            this.selectTable = row;
            this.dialogFormVisible = true;
        },
        fetchDatalist: function () {
            this.loading = true;
            //入库管理
            this.$http.get(this.servicerurl+'/material', {
                pageIndex: this.pageIndex,
                pageSize: this.pageSize
            }, {
                    headers: {},
                    emulateJSON: true
                }).then(function (response) {
                    this.material = response.data;
                    this.total = this.material.length;
                    this.loading = false;
                    console.log(response.data)
                }, function (response) {
                    console.log(response)
                })


        },
        fetchDataorder: function () {
            this.loading = true;
            //入库记录
            this.$http.get(this.servicerurl+'/ordermaterial', {
                pageIndex: this.pageIndex,
                pageSize: this.pageSize
            }, {
                    headers: {},
                    emulateJSON: true
                }).then(function (response) {
                    this.addrecord = response.data;
                    this.total = this.addrecord.length;
                    this.loading = false;
                    console.log(response.data)
                }, function (response) {
                    console.log(response)
                })


        },

        sizeChangelist: function (pageSize) {
            this.pageSize = pageSize;
            this.fetchDatalist();
        },
        pageIndexChangelist: function (pageIndex) {
            this.pageIndex = pageIndex;
            this.fetchDatalist();
        },
        sizeChangeroder: function (pageSize) {
            this.pageSize = pageSize;
            this.fetchDataorder();
        },
        pageIndexChangeorder: function (pageIndex) {
            this.pageIndex = pageIndex;
            this.fetchDataorder();
        },
        handleupdatestatus: function ($index, row) {
            //update amout=+order
            this.$http.get(this.servicerurl+'/material' + '?id=' + row.id, {}, {
                headers: {},
                emulateJSON: true
            }).then(function (response) {
                let newamount = Number(response.data[0].amount) + Number(row.order);
                //更新库存管理数据
                let newmatiral = {
                    id: row.id,
                    name: row.name,
                    amount: newamount,
                    order: 0,
                    class: row.class,
                    limit: row.limit
                }

                console.log(newmatiral)
                console.log(response.data[0].amount)
                //更新订单数据
                let orderdetail = {
                    id: row.id,
                    name: row.name,
                    order: 0,
                    class: row.class,
                    status: '已入库'
                }
                //确认入库
                this.$http.put(this.servicerurl+'/ordermaterial' + '/' + row.id, orderdetail, {
                    headers: {},
                    emulateJSON: true
                }).then(function (response) {
                    this.$http.put(this.servicerurl+'/material' + '/' + row.id, newmatiral, {
                        headers: {},
                        emulateJSON: true
                    }).then(function (response) {
                        console.log(response.data)
                        this.fetchDatalist();
                        this.fetchDataorder();
                        this.$message({
                            showClose: true,
                            message: '入库成功',
                            type: 'success'
                        })
                    }, function (response) {
                        console.log(response.data);
                    })
                    console.log(response.data)

                }, function (response) {
                    console.log(response)
                })
            }, function (response) {
                console.log(response)
            })
            //update order=0 ,status="已入库"
        }
    }
}
</script>

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
