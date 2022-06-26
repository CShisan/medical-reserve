const { request } = require('./request')
const baseUrl = '/department'

// 获取全部科室
function listAll() {
  return request({
    url: baseUrl + '/list/all',
    method: 'get'
  })
}

module.exports = {
  listAll
}
