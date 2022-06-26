const { listAll } = require('../../api/department')
const { listBy } = require('../../api/doctor')

Page({
  data: {
    depts: [],
    deptIndex: 0,
    doctorIndex: -1
  },
  async onLoad() {
    await this.getDepts()
    await this.getDoctors(0)
  },
  // 获取全部科室
  async getDepts() {
    const that = this
    await listAll().then(res => {
      const { data } = res
      data.map((item, index, arr) => {
        arr[index] = {
          id: index,
          text: item.deptName,
          deptId: item.deptId,
          children: []
        }
      })
      that.setData({
        depts: data
      })
    })
  },
  async getDoctors(index) {
    const that = this
    const depts = this.data.depts
    const dept = depts[index]
    await listBy({ deptId: dept.deptId }).then(res => {
      const { data } = res
      data.map((item, index, arr) => {
        arr[index] = { id: index, text: item.realName, doctorId: item.doctorId }
      })
      dept.children = data
      depts[index] = dept
      that.setData({
        depts: depts
      })
    })
  },
  handleSelectDept(e) {
    const index = e.detail.index
    this.setData({ deptIndex: index })
    this.getDoctors(index)
  },
  handleSelectDoctor(e) {
    const { id, doctorId } = e.detail
    this.setData({ doctorIndex: id })
    wx.navigateTo({
      url: '/pages/reserve-doctor/index?doctorId=' + doctorId
    })
  }
})
