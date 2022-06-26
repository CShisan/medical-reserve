const app = getApp()
const { getOneById } = require('../../api/doctor')
const { listDoctorSchedule } = require('../../api/schedule')
const { save } = require('../../api/reserve')
const dayjs = require('dayjs')

import Dialog from '@vant/weapp/dialog/dialog'

Page({
  data: {
    calendarShow: false,
    calendarMaxDate: dayjs(new Date()).startOf('day').add(6, 'day').valueOf(),
    date: dayjs(new Date()).format('YYYY年MM月DD日'),
    currentDate: '',
    doctorId: '',
    doctorName: '',
    titleName: '',
    avatar: '',
    charge: 0,
    schedules: [],
    current: {
      interval: -1,
      period: -1
    },
    submitDisable: true,
    confirmRule: false,
    dialogShow: false
  },
  onLoad(option) {
    const doctorId = option.doctorId
    this.setData({ doctorId: doctorId, currentDate: new Date() })
    this.getDoctor(doctorId)
    this.getSchedule(doctorId, new Date())
  },
  submit() {
    const title = '预约提示'
    const message =
      this.data.doctorName +
      '医生  (' +
      dayjs(this.data.currentDate).format('MM/DD') +
      this.data.current.week +
      ')  ' +
      this.data.current.periodText
    Dialog.confirm({ title, message, zIndex: 999 })
      .then(() => {
        save({
          patientId: app.globalData.userInfo.uid,
          doctorId: this.data.doctorId,
          timeInterval: this.data.current.interval,
          period: this.data.current.period,
          charge: this.data.charge,
          reserveDate: this.data.currentDate
        }).then(res => {
          this.getSchedule(this.data.doctorId, this.data.currentDate)
          const { data } = res
          const message = data ? '预约成功' : '该时间段已存在预约记录'
          Dialog.alert({ title: '预约提示', zIndex: 999, message: message })
        })
      })
      .catch(() => {})
  },
  handlePeriodSelect(e) {
    this.setData({
      current: e.currentTarget.dataset.data,
      submitDisable: false
    })
  },
  getDoctor(doctorId) {
    getOneById(doctorId).then(res => {
      const { realName, titleName, avatar, charge } = res.data
      this.setData({ doctorName: realName, titleName, avatar, charge })
    })
  },
  getSchedule(doctorId, date) {
    listDoctorSchedule({ doctorId, scheduleDate: date }).then(res => {
      const { data } = res
      this.setData({ schedules: data })
    })
  },
  handleCalendarSelect(e) {
    const date = e.detail
    this.getSchedule(this.data.doctorId, date)
    this.setData({
      date: dayjs(date).format('YYYY年MM月DD日'),
      currentDate: date,
      current: { interval: -1, period: -1 },
      submitDisable: true
    })
    this.handleCalendarShow(false)
  },
  handleCalendarShow(show) {
    this.setData({ calendarShow: show })
  },
  calendarClose() {
    this.handleCalendarShow(false)
  },
  isConfirmRule(e) {
    this.setData({ confirmRule: e.detail })
  },
  isCurrentMonth(date) {
    return dayjs(new Date()).add(7, 'day').endOf('day').isSameOrBefore(date)
  }
})
