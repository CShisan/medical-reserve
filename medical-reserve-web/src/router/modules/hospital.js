import Layout from '@/layout'

const hospitalRouter = {
  name: 'Hospital',
  path: '/hospital',
  component: Layout,
  hidden: false,
  alwaysShow: false,
  meta: {
    title: '医院管理',
    icon: 'el-icon-school',
    affix: false,
    noCache: true,
    parentNode: true,
    roles: ['AdminPlatform', 'Doctor']
  },
  children: [{
    name: 'HospitalDepartment',
    path: '/hospital/department',
    component: () => import('@/views/hospital/department/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '科室管理',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalDepartmentForm',
    path: '/hospital/department/form',
    component: () => import('@/views/hospital/department/form'),
    hidden: true,
    alwaysShow: false,
    meta: {
      title: '科室表单',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalDoctor',
    path: '/hospital/doctor',
    component: () => import('@/views/hospital/doctor/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '医生管理',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalDoctorForm',
    path: '/hospital/doctor/form',
    component: () => import('@/views/hospital/doctor/form'),
    hidden: true,
    alwaysShow: false,
    meta: {
      title: '医生表单',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalSchedule',
    path: '/hospital/schedule',
    component: () => import('@/views/hospital/schedule/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '排班管理',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalScheduleTable',
    path: '/hospital/schedule/table',
    component: () => import('@/views/hospital/schedule/table'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '排班表',
      noCache: true,
      affix: false,
      roles: ['Doctor']
    }
  }, {
    name: 'HospitalJobTitle',
    path: '/hospital/job-title',
    component: () => import('@/views/hospital/job-title/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '职称管理',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalJobTitleForm',
    path: '/hospital/job-title/form',
    component: () => import('@/views/hospital/job-title/form'),
    hidden: true,
    alwaysShow: false,
    meta: {
      title: '职称表单',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalRotation',
    path: '/hospital/rotation',
    component: () => import('@/views/hospital/rotation/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '班次管理',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalRotationForm',
    path: '/hospital/rotation/form',
    component: () => import('@/views/hospital/rotation/form'),
    hidden: true,
    alwaysShow: false,
    meta: {
      title: '班次表单',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }, {
    name: 'HospitalVacation',
    path: '/hospital/vacation',
    component: () => import('@/views/hospital/vacation/index'),
    hidden: false,
    alwaysShow: false,
    meta: {
      title: '调休审批',
      noCache: true,
      affix: false,
      roles: ['AdminPlatform']
    }
  }]
}

export default hospitalRouter
