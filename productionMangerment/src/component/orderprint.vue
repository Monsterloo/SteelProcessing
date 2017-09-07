<template>
    <div class="clear">
        <el-card class="box-card">
            <el-form v-model="tableData2" :inline="true">
                <el-form-item label="订单编号" >
                    <span style="margin-left: 10px;">{{ tableData2.id }}</span>
                </el-form-item>
                <el-form-item label="工程名称">
                    <span>{{ tableData2.workname}}</span>
                </el-form-item>
                <el-form-item label="公司名称">
                    <span> {{ tableData2.companyname}}</span>
                </el-form-item>
                <el-form-item label="到期日">
                    <span>{{ tableData2.targettime}}</span>
                </el-form-item>
                <el-form-item label="产品直径">
                    <span>{{ tableData2.dim}}</span>
                </el-form-item>
                <el-form-item label="简图">
                    <span class="imgstyle">
                        <img v-bind:src="tableData2.pic" alt="" srcset="" width="150">
                    </span>
                </el-form-item>
                <el-form-item label="总价">
                    <span>{{ tableData2.amount*tableData2.price}}</span>
                </el-form-item>
                <el-form-item label="数量">
                    <span>{{ tableData2.amount}}</span>
                </el-form-item>
                <el-form-item label="单价">
                    <span>{{ tableData2.price}}</span>
                </el-form-item>
               

            </el-form>
            <el-button @click="print" class="not-print" type="primary" style="margin:10px">确定打印</el-button>
            <el-button @click="back" class="not-print"  style="margin:10px">返回</el-button>

        </el-card>
    </div>
</template>



<script>
export default {
    data() {
        return {
            tableData2: {

            },
        }
    },
    mounted: function() {
        let test = [{
            matrialname: 'aaaa',
            diameter: 1,
            length: 1,
            weight: 1
        },{
            matrialname: 'aaaa',
            diameter: 1,
            length: 1,
            weight: 1}]

        this.tableData2 = JSON.parse(localStorage.getItem('printTemp') || []);
        this.tableData2.specs = test;
        console.log(this.tableData2)
        // console.log(this.tableData2.specs);
    },
    methods: {
        print: function() {
            window.print();
        },
        back:function(){
         this.$router.push({ path: '/order/listMangerment' })
        }

    }
}
</script>

<style scoped>

@media print {
    .not-print {
        opacity: 0
    }
}

.clear {
    clear: both
}
.tabelprint{
    margin: 0,auto
}
.box-card {
    width: 670px;
    margin: 10px auto;
}
</style>
