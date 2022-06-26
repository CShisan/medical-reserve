import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// axios实例
const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  withCredentials: false, // 跨域请求不发送cookie
  timeout: 5000 // 请求超时
})

// 请求头处理
service.interceptors.request.use(
  config => {
    // 设置请求头
    if (store.getters.token) {
      config.headers['Authorization'] = 'Bearer ' + getToken()
    }
    return config
  },
  error => {
    // 请求错误处理
    console.log(error)
    return Promise.reject(error)
  }
)

// 请求返回处理
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
  */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    // token登录放行，自定义code不为200则为异常请求
    if (!res.token && res.code !== 200) {
      Message({
        message: res.msg || '系统异常，可能是远程服务器繁忙，请稍后尝试',
        type: 'error',
        duration: 5 * 1000
      })

      return Promise.reject(res.msg || '系统异常，可能是远程服务器繁忙，请稍后尝试')
    } else {
      return res
    }
  },
  error => {
    const response = error.response
    // 根据不同状态码作出不同响应
    if (response.status === 401) {
      // 跳转登录页面
      MessageBox.alert('登录凭证异常或已过期，请重新登录', '系统提示', {
        confirmButtonText: '重新登录',
        type: 'warning'
      }).then(() => {
        store.dispatch('user/resetToken').then(() => {
          location.reload()
        })
      })
    } else {
      Message({
        message: response.data.error_description || '系统异常，可能是远程服务器繁忙，请稍后尝试',
        type: 'error',
        duration: 5 * 1000
      })
    }
    return Promise.reject(error)
  }
)

export default service
