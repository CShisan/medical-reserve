const { request } = require('./request')
const baseUrl = '/doctor'

// 根据科室id获取医生列表
function listBy(data) {
  return request({
    url: baseUrl + '/list/param',
    method: 'post',
    data
  })
}

function getOneById(id) {
  return request({
    url: baseUrl + `/doctor-id/${id}`,
    method: 'get'
  })
}

module.exports = {
  listBy,
  getOneById
}
