const app = getApp()
const { login } = require('../../api/auth.js')
import watch from '../../utils/watch.js'
let that

Component({
  options: {
    multipleSlots: true
  },
  properties: {},
  data: {
    show: !app.globalData.isLogin
  },
  created() {
    // 监听回调时用(不这样用会有bug)
    that = this
    // 注入监听值和回调方法
    watch('isLogin', this.isLogin)
  },
  methods: {
    // 监听是否登录回调
    isLogin() {
      that.setData({
        show: !app.globalData.isLogin
      })
    },
    login(detail) {
      login(detail)
    }
  }
})
