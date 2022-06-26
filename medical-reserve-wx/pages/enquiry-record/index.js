const app = getApp()
const { listBy } = require('../../api/enquiry')

Page({
  data: {
    equirys: [],
    activeCollapse: -1
  },
  onLoad() {
    this.getEquirys()
  },
  getEquirys() {
    listBy({ patientId: app.globalData.userInfo.uid }).then(res => {
      const { data } = res
      this.setData({ equirys: data })
    })
  },
  collapseChange(e) {
    this.setData({ activeCollapse: e.detail })
  }
})
