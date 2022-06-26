const app = getApp()
const { listBy, update } = require('../../api/reserve')
import Dialog from '@vant/weapp/dialog/dialog'

Page({
  data: {
    reserves: []
  },
  onLoad() {
    this.getReserves()
  },
  getReserves() {
    const patientId = app.globalData.userInfo.uid
    listBy({ patientId }).then(res => {
      const { data } = res
      data.map((item, index, data) => {
        data[index] = {
          reserveStatusType: this.calcReserveStatus(item.status),
          ...item
        }
      })
      this.setData({ reserves: data })
    })
  },
  swipeCellClose(e) {
    const { position, instance } = e.detail
    const { id } = e.target.dataset.value
    switch (position) {
      case 'left':
      case 'cell':
        instance.close()
        break
      case 'right':
        Dialog.confirm({ message: '确定取消预约吗？' })
          .then(() => {
            update({ id, status: 0 }).then(res => {
              const { data } = res
              const message = data ? '取消成功' : '取消失败'
              Dialog.alert({ title: '预约提示', message })
              instance.close()
              this.getReserves()
            })
          })
          .catch(() => {
            instance.close()
          })
        break
    }
  },
  calcReserveStatus(code) {
    let text = ''
    switch (code) {
      case 0:
        text = 'danger'
        break
      case 1:
        text = 'primary'
        break
      case 2:
        text = 'success'
        break
    }
    return text
  }
})
