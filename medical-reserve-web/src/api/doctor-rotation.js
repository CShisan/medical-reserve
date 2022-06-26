import request from '@/utils/request'

const baseUrl = '/doctor-rotation'

export function getDoctorList(data) {
  return request({
    url: baseUrl + '/list/param',
    method: 'post',
    data
  })
}

export function saveDr(data) {
  return request({
    url: baseUrl + '/',
    method: 'post',
    data
  })
}

export function delDr(id) {
  return request({
    url: baseUrl + `/${id}`,
    method: 'delete'
  })
}
