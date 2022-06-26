import request from '@/utils/request'

const baseUrl = '/doctor'

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

export function getOne(id) {
  return request({
    url: baseUrl + `/${id}`,
    method: 'get'
  })
}

export function getOneByDoctorId(doctorId) {
  return request({
    url: baseUrl + `/doctor-id/${doctorId}`,
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
