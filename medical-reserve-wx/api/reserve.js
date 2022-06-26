const { request } = require('./request')
const baseUrl = "/reserve"

// 预约记录
function listBy(data){
  return request({
    url: baseUrl + '/list/param',
    method: 'post',
    data
  })
}

// 预约
function save(data){
  return request({
    url: baseUrl + '/',
    method: 'post',
    data
  })
}

// 更新
function update(data){
  return request({
    url: baseUrl + '/',
    method: 'put',
    data
  })
}

module.exports = {
    save,
    update,
    listBy
}