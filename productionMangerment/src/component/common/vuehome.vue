<template>
<div>
  <el-row style="height:100%">
  <vueSideNav></vueSideNav>
  <div id='content' class="clear">
    <vueTopNav></vueTopNav>
<router-view></router-view>
  </div>
  </el-row>
  </div>
</template>

<script>
import vueTopNav from '../vue-topNav.vue';
import vueSideNav from './vue-sidenav.vue'

export default{
   
    components:{
        vueTopNav,
      vueSideNav
    },
      created:function(){
           document.body.setAttribute("class","bodylogin");
    },
}
</script>
<style>
.clear{
  clear: both
}
.bodylogin{
    background-color: #3891f6;
}
#content{
   width: 80%;
  display: inline-block;
}
</style>
