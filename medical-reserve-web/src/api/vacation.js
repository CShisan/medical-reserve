import request from '@/utils/request'

const baseUrl = '/vacation'

export function getList(data) {
  return request({
    url: baseUrl + '/list/page',
    method: 'post',
    data
  })
}

export function getListBy(data) {
  return request({
    url: baseUrl + '/list/param',
    method: 'post',
    data
  })
}

export function save(data) {
  return request({
    url: baseUrl + '/',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: baseUrl + '/',
    method: 'put',
    data
  })
}

