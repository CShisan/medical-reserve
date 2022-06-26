const app = getApp()
const { getInfo, update } = require('../../api/user')

import Dialog from '@vant/weapp/dialog/dialog'

Page({
  data: {
    isEdit: false,
    user: {},
    form: {}
  },
  onLoad() {
    this.getInfo()
  },
  inputChange(e) {
    const { key } = e.currentTarget.dataset
    const value = e.detail
    this.data.form[key] = value
  },
  submit() {
    update(this.data.form).then(res => {
      this.changeEditStatus()
      this.getInfo()
      const { data } = res
      const message = data ? '编辑成功' : '编辑失败'
      Dialog.alert({ message })
    })
  },
  getInfo() {
    getInfo().then(res => {
      const { data } = res
      this.setData({ user: data })
      app.globalData.userInfo = data,
      wx.setStorageSync('user', data)
    })
  },
  changeEditStatus() {
    this.setData({
      form: this.data.user,
      isEdit: !this.data.isEdit
    })
  }
})
