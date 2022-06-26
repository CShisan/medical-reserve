const app = getApp()
const { cert } = require('../../api/auth')
import Notify from '@vant/weapp/notify/notify'
import watch from '../../utils/watch.js'
let that

Component({
  options: {
    multipleSlots: true
  },
  properties: {},
  data: {
    show: app.globalData.isLogin ? !app.globalData.isCert : false,
    realName: '',
    idCard: '',
    realnameValid: true,
    idcardValid: true,
    beforeClose: () => {
      return false
    }
  },
  created() {
    // 监听回调时用(不这样用会有bug)
    that = this
    // 注入监听值和回调方法
    watch('isLogin', this.isLogin)
    watch('isCert', this.isCert)
  },
  methods: {
    // 监听是否登录回调
    isLogin() {
      const isShow = app.globalData.isLogin ? !app.globalData.isCert : false
      // 不延迟会出问题
      setTimeout(function () {
        that.setData({
          show: isShow
        })
      }, 33)
    },
    // 监听是否实名认证回调
    isCert() {
      const isShow = app.globalData.isLogin ? !app.globalData.isCert : false
      that.setData({
        show: isShow
      })
    },
    submit() {
      const data = {
        id: app.globalData.userInfo.id,
        realName: this.data.realName,
        idCard: this.data.idCard
      }
      cert(data)
        .then(res => {
          const { code, msg } = res
          if (code === 200) {
            Notify({
              type: 'success',
              message: '认证成功',
              context: that,
              selector: '#external-custom-notify'
            })
            that.isShow(false)
            app.globalData.isCert = true
            wx.setStorageSync('isCert', true)
          } else {
            Notify({
              type: 'danger',
              message: msg,
              context: that,
              selector: '#custom-notify'
            })
            that.isShow(true)
          }
        })
        .catch(err => {
          Notify({
            type: 'danger',
            message: err.msg,
            context: that,
            selector: '#custom-notify'
          })
          that.isShow(true)
        })
    },
    isShow(show) {
      this.setData({
        show: show
      })
    }
  }
})
