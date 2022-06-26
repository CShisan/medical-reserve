const { request } = require('./request')
const baseUrl = '/schedule'

// 获取医生的本周可挂号时间
function listDoctorSchedule(data) {
  return request({
    url: baseUrl + '/doctor-schedule',
    method: 'post',
    data
  })
}

module.exports = {
  listDoctorSchedule
}
