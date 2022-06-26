import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data,
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function statistics() {
  return request({
    url: '/statistics',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}
