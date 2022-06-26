const { request } = require('./request')
const baseUrl = '/user'

function getInfo() {
  return request({
    url: baseUrl + '/info',
    method: 'get'
  })
}

function update(data) {
  return request({
    url: baseUrl + '/',
    method: 'put',
    data
  })
}

module.exports = {
  getInfo,
  update
}
