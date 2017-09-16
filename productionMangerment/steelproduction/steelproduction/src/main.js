// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
// import echarts from 'echarts'
import VueResource from 'vue-resource'
import 'element-ui/lib/theme-default/index.css'
import VDistpicker from 'v-distpicker'
// import distList from 'china-dist-data'
// import myDistPicker from 'vue-dist-picker'
import VueLazyload from 'vue-lazyload'
Vue.use(ElementUI)
Vue.use(VueLazyload, {
  lazyComponent: true
})
Vue.component('v-distpicker', VDistpicker)
// Vue.component('dist-picker', myDistPicker)
Vue.use(VueResource)

Vue.config.productionTip = false

Vue.http.options.emulateJSON = true
Vue.http.options.headers = {
  'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
Vue.prototype.$tooljs = require('./assets/js/tool.js')
Vue.prototype.$commonjs = require('./assets/js/common.js')

