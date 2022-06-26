import WxRequst from './wx-request/lib/index'

const API_BASE_URL = 'http://localhost:8013/mini'
const header = {
  Authorization: 'Bearer ' + wx.getStorageSync('token') || ''
}
const customRequest = new WxRequst({ baseURL: API_BASE_URL, header })

// 更新自定义请求对象
function updateRequest() {
  const header = { Authorization: 'Bearer ' + wx.getStorageSync('token') || '' }
  customRequest.defaults.header = header
}

// 请求拦截器
customRequest.interceptors.use({
  // 请求数据
  request(request) {
    console.log(request)
    wx.showNavigationBarLoading()
    return request
  },
  // 请求失败
  requestError(requestError) {
    wx.hideNavigationBarLoading()
    return new Promise(reject => reject(requestError))
  },
  // 响应数据
  response(response) {
    console.log(response)
    wx.hideNavigationBarLoading()
    return response
  },
  // 响应失败
  responseError(responseError) {
    wx.hideNavigationBarLoading()
    return new Promise(reject => reject(responseError))
  }
})

function request(info) {
  return new Promise((resole, reject) => {
    customRequest
      .request(info)
      .then(res => {
        const { statusCode, data, errMsg } = res
        if (statusCode === 200) {
          resole(data)
        } else {
          reject(errMsg)
        }
      })
      .catch(err => {
        reject(err.data)
      })
  })
}

module.exports = {
  updateRequest,
  request
}
