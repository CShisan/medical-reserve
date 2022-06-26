import Dialog from '@vant/weapp/dialog/dialog'

Page({
  data: {
    swipers: [
      'https://www.gzhosp.cn/html/images/home.jpg',
      'https://www.gzhosp.cn/Upload/202111/637716988876028191.jpg',
      'https://www.gzhosp.cn/Upload/202007/6373039943255595917679577.jpg',
      'https://www.gzhosp.cn/Upload/201909/6370424014114523069348898.jpg'
    ],
    pages: [
      '/pages/reserve-select/index',
      '/pages/reserve-record/index',
      '/pages/enquiry-record/index'
    ],
    markers: [
      {
        id: 1,
        width: 36,
        height: 36,
        longitude: 113.256345,
        latitude: 23.131267,
        iconPath: '/static/image/location.png'
      }
    ]
  },
  onLoad() {},
  onShow() {
    this.getTabBar().setData({
      active: 0,
      show: true
    })
  },
  onHide() {
    this.getTabBar().setData({
      active: -1,
      show: false
    })
  },
  toPage(e) {
    const { index } = e.currentTarget.dataset
    wx.navigateTo({ url: this.data.pages[index] })
  },
  showCallMe() {
    Dialog.alert({ title: '请致电', message: '0766-123456', zIndex: 99999 })
  }
})
