import Vue from 'vue'
import Router from 'vue-router'
import login from '@/components/login'
import vuehome from '@/components/common/vuehome.vue'
import orderTable from '@/components/orderTable.vue'
import salechart from '@/components/salesChart.vue'
import menberTable from '@/components/menberTable.vue'
import adminTable from '@/components/adminlist.vue'
import mypage from '@/components/modifypwd.vue'
import supply from '@/components/supplyList.vue'
import outstock from '@/components/outStock.vue'
import addorder from '@/components/addorder.vue'

Vue.use(Router)
export default new Router({
  mode: 'history',
  base: __dirname,
  routes: [
    {
      path: '/login',
      component: login
    },
    {
      path: '/',
      meta: {
        requiAuth: true
      },
      redirect: '/index'
    },
    {
      path: '/index',
      meta: {
        requiAuth: true
      },
      component: vuehome
    },
    {
      path: '/system/:id',
      meta: {
        requiAuth: true
      },
      children: [
        {
          path: '/system/adminMangerment',
          component: adminTable,
          name: '管理员管理-管理员列表'
        },
        {
          path: '/system/mypage',
          component: mypage,
          name: '管理员管理-我的设置'
        }
      ],
      component: vuehome
    },
    {
      path: '/menber',
      meta: {
        requiAuth: true
      },
      children: [
        {
          path: '',
          component: menberTable,
          name: '会员管理'
        }
      ],
      component: vuehome
    },
    {
      path: '/order/:id',
      meta: {
        requiAuth: true
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
          path: '/order/sale',
          component: salechart,
          name: '订单管理-客户销售分析'
        }
      ]
    },
    {
      path: '/product/:id',
      meta: {
        requiAuth: true
      },
      children: [
        {
          path: '/product/in',
          component: supply,
          name: '库存管理-入库管理'
        },
        {
          path: '/product/out',
          component: outstock,
          name: '库存管理-出库管理'
        }
      ],
      component: vuehome
    }
  ]
})

// Router.beforeEach((to, from, next) => {
//   if (to.meta.requiAuth) {
//     if (localStorage.getItem('st_admin') != null) {
//       next()
//     } else if (localStorage.getItem('st_admin') == null) {
//       console.log('跳转')
//       next({
//         path: '/login',
//         query: { redirect: to.fullPath }  // 将跳转的路由path作为参数，登录成功后跳转到该路由
//       })
//     }
//   } else {
//     next()
//   }
// })
