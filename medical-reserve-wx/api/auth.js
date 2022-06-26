const app = getApp()
const { updateRequest, request } = require('../api/request.js')

// 服务端登录
async function login(info) {
  const code = await wxLogin()
  const { encryptedData, iv } = info.detail
  const param = { code, encryptedData, iv }
  return new Promise((resolve, rejects) => {
    wx.request({
      url: 'http://localhost:8013/mini/wx-login',
      method: 'post',
      data: param,
      success: res => {
        const { code, data } = res.data
        if (code === 200) {
          // 登录成功
          wx.setStorageSync('token', data.token)
          wx.setStorageSync('user', data.user)
          wx.setStorageSync('isCert', data.isHasIdCard)
          updateRequest()
          app.globalData.userInfo = data.user
          app.globalData.isCert = data.isHasIdCard
          app.globalData.isLogin = true
        }
        resolve(res.data)
      },
      fail: err => {
        app.globalData.isLogin = false
        rejects(err)
      }
    })
  })
}

// 微信静默登录
function wxLogin() {
  return new Promise(resolve => {
    wx.login({
      success: res => {
        resolve(res.code)
      },
      fail: () => {
        resolve(wxLogin())
      }
    })
  })
}

//实名认证
function cert(data) {
  return request({
    url: '/user/',
    method: 'put',
    data
  })
}

module.exports = {
  login,
  cert
}
