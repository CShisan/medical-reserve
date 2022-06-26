import Layout from '@/layout'

const orderRouter = {
  name: 'Order',
  path: '/order',
  component: Layout,
  hidden: false,
  alwaysShow: false,
  meta: {
    title: '订单管理',
    icon: 'el-icon-notebook-2',
    affix: false,
    noCache: true,
    parentNode: true,
    roles: ['AdminPlatform']
  },
  children: [{
    name: 'OrderReserve',
    path: '/order/reserve',
    component: () => import('@/views/order/reserve/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '预约信息管理',
      noCache: true,
      affix: false
    }
  }, {
    name: 'OrderEnquiry',
    path: '/order/enquiry',
    component: () => import('@/views/order/enquiry/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '问诊信息管理',
      noCache: true,
      affix: false
    }
  }, {
    name: 'OrderEnquiryForm',
    path: '/order/enquiry/form',
    component: () => import('@/views/order/enquiry/form'),
    hidden: true,
    alwaysShow: false,
    meta: {
      title: '问诊信息表单',
      noCache: true,
      affix: false
    }
  }]
}

export default orderRouter
