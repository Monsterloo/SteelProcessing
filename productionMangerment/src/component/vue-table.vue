
<template id="vuetable">
<el-table :data="people">
<el-table-column prop="id" label="ID" width=180> 
</el-table-column>
<el-table-column prop="name" label="name" > 
</el-table-column>
</el-table>
</template>

<script>

export default {
  name: 'app',
  data () {
    return {
    people:[],
    }
  },
  mounted:function(){
    this.$http.get(this.servicerurl+'/people',{},{
      headers:{},
      emulateJSON:true
    }).then(function(response){
     this.people=response.data;
   
    },function(response){
      console.log(response)
    })
  }
}
</script>