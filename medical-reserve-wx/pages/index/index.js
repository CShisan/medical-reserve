const app = getApp()

Page({
  data: {
    user: {}
  },
  onLoad() {},
  onShow() {
    this.setData({ user: app.globalData.userInfo })
    this.getTabBar().setData({
      active: 1,
      show: true
    })
  },
  onHide() {
    this.getTabBar().setData({
      active: -1,
      show: false
    })
  }
})
