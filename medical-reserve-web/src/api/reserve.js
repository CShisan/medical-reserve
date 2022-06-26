import request from '@/utils/request'

const baseUrl = '/reserve'

export function getWaitPatients(data) {
  return request({
    url: baseUrl + '/wait-patients',
    method: 'post',
    data
  })
}

export function getCompletedPatients(data) {
  return request({
    url: baseUrl + '/completed-patients',
    method: 'post',
    data
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
