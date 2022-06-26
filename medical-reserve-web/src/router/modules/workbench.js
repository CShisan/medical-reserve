import Layout from '@/layout'

const workbenchRouter = {
  name: 'Workbench',
  path: '/workbench',
  component: Layout,
  hidden: false,
  alwaysShow: false,
  meta: {
    title: '工作台',
    icon: 'el-icon-chat-line-round',
    affix: false,
    noCache: true,
    parentNode: true,
    roles: ['Doctor']
  },
  children: [{
    name: 'WorkbenchDoctor',
    path: '/workbench/doctor',
    component: () => import('@/views/workbench/doctor/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '医生工作台',
      noCache: true,
      affix: false
    }
  }]
}

export default workbenchRouter
