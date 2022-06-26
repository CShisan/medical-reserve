App({
  onLaunch() {
    const user = wx.getStorageSync('user')
    const isCert = wx.getStorageSync('isCert')
    this.globalData.userInfo = user
    this.globalData.isLogin = user ? true : false
    this.globalData.isCert = isCert ? isCert : false
  },
  globalData: {
    userInfo: null,
    isLogin: false,
    isCert: false
  }
})
