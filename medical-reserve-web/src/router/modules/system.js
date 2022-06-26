import Layout from '@/layout'

const systemRouter = {
  name: 'System',
  path: '/system',
  component: Layout,
  hidden: false,
  alwaysShow: false,
  meta: {
    title: '用户管理',
    icon: 'el-icon-user',
    affix: false,
    noCache: true,
    parentNode: true,
    roles: ['Admin']
  },
  children: [{
    name: 'SystemUser',
    path: '/system/user',
    component: () => import('@/views/system/user/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '用户信息管理',
      noCache: true,
      affix: false,
      roles: ['Admin']
    }
  }, {
    name: 'SystemUserForm',
    path: '/system/user/form',
    component: () => import('@/views/system/user/form'),
    hidden: true,
    alwaysShow: false,
    meta: {
      title: '用户信息表单',
      noCache: true,
      affix: false,
      roles: ['Admin']
    }
  }, {
    name: 'SystemRole',
    path: '/system/role',
    component: () => import('@/views/system/role/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '角色信息管理',
      noCache: true,
      affix: false,
      roles: ['Admin']
    }
  }, {
    name: 'SystemRoleForm',
    path: '/system/role/form',
    component: () => import('@/views/system/role/form'),
    hidden: true,
    alwaysShow: false,
    meta: {
      title: '角色信息表单',
      noCache: true,
      affix: false,
      roles: ['Admin']
    }
  }]
}

export default systemRouter
