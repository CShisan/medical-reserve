import request from '@/utils/request'

const baseUrl = '/enquiry'

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
