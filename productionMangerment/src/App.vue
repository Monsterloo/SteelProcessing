<template>
  <div id="app">
  <router-view></router-view>
  </div>
</template>

<script>


export default {
  name: 'app',
  data () {
    return {
     
    }
  },
  created:function(){
    document.body.setAttribute("class","bodyindex");
    
  },
 
}
</script>

<style >


body{
  margin: 0;

}



</style>
