import request from '@/utils/request'

const baseUrl = '/user'

export function getInfo() {
  return request({
    url: baseUrl + '/info',
    method: 'get'
  })
}

export function getList(data) {
  return request({
    url: baseUrl + '/list/page',
    method: 'post',
    data
  })
}

export function getOne(id) {
  return request({
    url: baseUrl + `/${id}`,
    method: 'get'
  })
}

export function update(data) {
  return request({
    url: baseUrl + '/',
    method: 'put',
    data
  })
}

export function updatePassword(data) {
  return request({
    url: baseUrl + '/update/password',
    method: 'post',
    data
  })
}
