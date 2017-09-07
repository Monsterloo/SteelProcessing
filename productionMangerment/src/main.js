import Vue from 'vue'
import App from './App.vue'
import VueRouter from "vue-router";
import echarts from 'echarts';
import VueResource from "vue-resource"
import {
  Table, TableColumn, Menu, Submenu, MenuItem, MenuItemGroup, Icon, Col, Row, Form, FormItem, Button, Input, Dropdown,
  DropdownMenu,
  DropdownItem, DatePicker, InputNumber, Select, Pagination, Card,
  Option, Message, Breadcrumb,
  BreadcrumbItem, Loading, Dialog, Switch, Tabs,
  TabPane, Tag, Badge, Notification, Steps,
  Step,
} from 'element-ui';
import VDistpicker from 'v-distpicker'
import VueLazyload from 'vue-lazyload'


//debug
Vue.config.debug = true;

//引用
// Vue.use(echarts);
Vue.use(VueLazyload, {
  lazyComponent: true
});
Vue.use(Step);
Vue.use(Steps);
Vue.use(Badge);
Vue.use(Tag);
Vue.use(Tabs);
Vue.use(TabPane);
Vue.use(Switch);
Vue.use(Dialog);
Vue.use(Loading);
Vue.use(Breadcrumb);
Vue.use(BreadcrumbItem);
Vue.use(Option);
Vue.use(Card);
Vue.use(Pagination);
Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(Table);
Vue.use(TableColumn);
Vue.use(Menu);
Vue.use(Submenu);
Vue.use(MenuItem);
Vue.use(MenuItemGroup);
Vue.use(Col);
Vue.use(Row);
Vue.use(Icon);
Vue.use(Form);
Vue.use(FormItem);
Vue.use(Input);
Vue.use(Button);
Vue.use(DropdownItem);
Vue.use(DropdownMenu);
Vue.use(Dropdown);
Vue.use(DatePicker);
Vue.use(InputNumber);
Vue.use(Select);

Vue.component('v-distpicker', VDistpicker)

Vue.prototype.$message = Message
Vue.prototype.$notify = Notification
Vue.prototype.servicerurl = 'http://localhost:3000'
//router
import login from './component/login.vue'
import vuetable from './component/vue-table.vue'
import vuehome from './component/common/vuehome.vue'
import vueTopNav from './component/vue-topNav.vue'
import orderTable from './component/orderTable.vue'
import addorder from './component/addorder.vue'
import modifyorder from './component/modifyorder.vue'
import salechart from './component/saleschart.vue'
import incomecard from './component/incomecard.vue'
import menbertable from './component/menbertable.vue'
import addmenber from './component/addmenber.vue'
import modifymenber from './component/menbermodify.vue'
// import addadmin from './component/addadmin.vue'
import adminlist from './component/adminlist.vue'
import mypage from './component/modifypwd.vue'
import supply from './component/supplylist.vue'
import outstock from './component/outstock.vue'
import print from './component/orderprint.vue'

const router = new VueRouter({
  mode: 'history',
  base: __dirname,
  routes: [
    {
      path: '/',
      meta: {
        requiAuth: true,
      },
      redirect: '/index'
    },
    {
      path: '/login',
      component: login
    },
    {
      path: '/print',
      component:print
    },
    {
      path: '/index',
      meta: {
        requiAuth: true,
      },
      component: vuehome,

    },
    {
      path: '/order/:id',
      meta: {
        requiAuth: true,

      },
      component: vuehome,

      children: [
        {
          path: '/order/listMangerment',
          component: orderTable,
          name: '订单管理-订单列表'
        },
        {
          path: '/order/add',
          component: addorder,
          name: '订单管理-添加订单'
        },
        {
          path: '/order/modify',
          component: modifyorder,
          name: '订单管理-修改订单',
        },
        {
          path: '/order/sale',
          component: salechart,
          name: '订单管理-客户销售分析'
        },
        {
          path: '/order/income',
          component: incomecard,
          name: '订单管理-客户应收查询'
        }
      ]
    },
    {
      path: '/menber',
      meta: {
        requiAuth: true,
      },
      children: [
        {
          path: '',
          component: menbertable,
          name: '会员管理'
        },
        {
          path: '/menber/add',
          component: addmenber,
          name: '会员管理-添加会员'
        },
        {
          path: '/menber/modify',
          component: modifymenber,
          name: '会员管理-修改会员'
        }
      ],
      component: vuehome
    },
    {
      path: '/product/:id',
      meta: {
        requiAuth: true,
      },
      children: [
        {
          path: "/product/in",
          component: supply,
          name: '库存管理-入库管理'
        },
        {
          path: "/product/out",
          component: outstock,
          name: '库存管理-出库管理'
        }
      ],
      component: vuehome,
    },
    {
      path: '/system/:id',
      meta: {
        requiAuth: true,
      },
      children: [
        {
          path: '/system/adminMangerment',
          component: adminlist,
          name: '管理员管理-管理员列表'
        },
        {
          path: '/system/mypage',
          component: mypage,
          name: '管理员管理-我的设置'
        },
      ],
      component: vuehome,
    },

  ]
})

router.beforeEach((to, from, next) => {
  if (to.meta.requiAuth) {
    if (localStorage.getItem('st_admin') != null) {
      next()
    }
    else if (localStorage.getItem('st_admin') == null) {
      Message({
        showClose: true,
        message: '请先登录！',
        type: 'warning'
      })
      next({
        path: '/login',
        query: { redirect: to.fullPath }  // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  }
  else {
    next();
  }


})



const aapp = new Vue({
  router: router,
  render: h => h(App)
}).$mount(app)