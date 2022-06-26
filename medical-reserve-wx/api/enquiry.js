const { request } = require('./request')
const baseUrl = "/enquiry"

// 获取问诊记录
function listBy(data){
  return request({
    url: baseUrl + '/list/param',
    method: 'post',
    data
  })
}



module.exports = {
  listBy
}