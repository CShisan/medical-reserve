import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/* Router Modules */

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   如果设置为true，则项目不会显示在侧边栏中（默认为false）
 * alwaysShow: true               如果设置为true，将始终显示根菜单
 *                                如果未设置alwaysShow，则当项具有多个子路线时，
 *                                它将变为嵌套模式，否则不显示根菜单
 * redirect: noRedirect           如果设置noRedirect，则不会在面包屑中重定向
 * name:'router-name'             该名称由<keep-alive>使用（必须设置！！！）
 * meta : {
    roles: ['admin','editor']    控制页面角色（您可以设置多个角色）
    title: 'title'               名称显示在侧边栏和面包屑中（推荐设置）
    icon: 'svg-name'/'el-icon-x' 侧栏中的图标显示
    noCache: true                如果设置为true，将不缓存页面（默认为false）
    affix: true                  如果设置为true，则标签将固定在标签视图中(不可关闭)
    breadcrumb: false            如果设置为false，则该项在面包屑中不显示（默认为true）
    activeMenu: '/example/list'  如果设置了路径，则侧边栏将突出显示您设置的路径
    parentNode: true             如果设置为true，则面包屑中不会跳转路径
  }
 */

import systemRouter from './modules/system'
import orderRouter from './modules/order'
import hospitalRouter from './modules/hospital'
import workbenchRouter from './modules/workbench'

export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: {
          title: 'dashboard',
          icon: 'el-icon-data-line',
          affix: true
        }
      }, {
        name: 'Profile',
        path: '/profile',
        component: () => import('@/views/system/profile/index'),
        hidden: true,
        alwaysShow: false,
        meta: {
          title: '个人信息',
          noCache: true,
          affix: false
        }
      }
    ]
  }
]

export const asyncRoutes = [
  systemRouter,
  orderRouter,
  hospitalRouter,
  workbenchRouter
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
  console.log(newRouter)
}

export default router
