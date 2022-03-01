import Vue from 'vue'
import VueRouter from 'vue-router'
import { getToken } from '@/request/token'
import { getRole } from '@/api/role'

import Home from '../views/Home.vue'
import Index from '@/views/Index'
import GoodsDetail from '@/views/GoodsDetail'
import Car from '@/views/Car'
import CategoryGoods from '@/views/CategoryGoods'
import GoodsList from '@/views/GoodsList'
import Login from '@/views/Login'
import Profile from '@/views/Profile'
import History from '@/views/History'
import Order from '@/views/Order'

import AdminHome from '@/views/admin/AdminHome'
import AdminIndex from '@/views/admin/AdminIndex'
import AdminGoods from '@/views/admin/AdminGoods'
import AdminOrder from '@/views/admin/AdminOrder'
import AddGoods from '@/views/admin/AddGoods'
import EditGoods from '@/views/admin/EditGoods'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    redirect: '/index',
    children: [
      {
        path: '/index',
        component: Index
      },
      {
        path: '/detail/:id',
        component: GoodsDetail
      },
      {
        path: '/car',
        component: Car
      },
      {
        path: '/category/:id',
        component: CategoryGoods
      },
      {
        path: '/goods/:type',
        component: GoodsList
      },
      {
        path: '/profile',
        component: Profile
      },
      {
        path: '/history',
        component: History
      },
      {
        path: '/order',
        component: Order
      },
    ]
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/admin',
    component: AdminHome,
    beforeEnter: async (to, from, next) => {
      let {data: res} = await isAdmin()
      console.log(res)
      if (!getToken()) next('/login')
      if (res.name != 'admin') next('/login')
      next()
    },
    redirect: '/admin/index',
    children: [
      {
        path: '/admin/index',
        component: AdminIndex
      },
      {
        path: '/admin/goods',
        component: AdminGoods
      },
      {
        path: '/admin/order',
        component: AdminOrder
      },
      {
        path: '/admin/goods/add',
        component: AddGoods
      },
      {
        path: '/admin/goods/edit/:id',
        component: EditGoods
      }
      
    ]
  }
]

async function isAdmin() {
  var {data: flag} = await getRole()
  return flag
}

const router = new VueRouter({
  routes
})

export default router
