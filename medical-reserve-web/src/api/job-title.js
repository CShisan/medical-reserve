import request from '@/utils/request'

const baseUrl = '/job-title'

export function getList(data) {
  return request({
    url: baseUrl + '/list/page',
    method: 'post',
    data
  })
}

export function getAllTitles() {
  return request({
    url: baseUrl + '/list/all',
    method: 'get'
  })
}

export function getOne(id) {
  return request({
    url: baseUrl + `/${id}`,
    method: 'get'
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

export function del(id) {
  return request({
    url: baseUrl + `/${id}`,
    method: 'delete'
  })
}
