const app = getApp()
Component({
  data: {
    active: -1,
    list: [
      {
        icon: 'wap-home',
        text: '首页',
        pagePath: '/pages/home/index'
      },
      {
        icon: 'manager',
        text: '个人信息',
        pagePath: '/pages/index/index'
      }
    ]
  },
  methods: {
    onChange(e) {
      const index = e.detail
      const url = this.data.list[index].pagePath
      wx.switchTab({ url })
    }
  }
})
