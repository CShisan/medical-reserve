import request from '@/utils/request'

const baseUrl = '/schedule'

export function getList(data) {
  return request({
    url: baseUrl + '/list',
    method: 'post',
    data
  })
}

export function autoSchedule(data) {
  return request({
    url: baseUrl + '/auto-schedule',
    method: 'post',
    data
  })
}

