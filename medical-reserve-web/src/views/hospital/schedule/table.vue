<template>
  <div>
    <el-card class="order-reserve-container">
      <div class="content-container">
        <el-calendar v-model="current" class="schedule-calendar">
          <template slot="dateCell" slot-scope="{date, data}">
            <div class="cell-calendar" @click="handleDialogOpen(date)">
              <div style="text-align: center;" :class="data.isSelected ? 'is-selected' : ''">
                {{ dateFormat(data.day, 'MM-DD') }} {{ data.isSelected ? '✔️' : '' }}
              </div>
              <div class="tag-calendar">
                <span v-for="item in list" :key="item.id">
                  <el-tag
                    v-if="isExist(item.scheduleDate,data.day)"
                    :type="tagColor(date)"
                  >
                    {{ item.rotationKey }} 班次
                  </el-tag>
                </span>
                <span v-for="item in vacations" :key="item.id">
                  <el-tag
                    v-if="isExist(item.vacationDate,data.day)"
                    type="success"
                  >休：{{ tagInterval(item.timeInterval) }}</el-tag>
                </span>
              </div>
            </div>
          </template>
        </el-calendar>
      </div>
    </el-card>
    <el-dialog
      title="Tips"
      :visible.sync="dialogVisible"
      width="20%"
      center
    >
      <div style="text-align: center">{{ dialogTips }}</div>
      <el-select v-if="!vacationExist" v-model="vacationInterval" style="margin: 20px 0 0 2px;width: 100%" placeholder="请选择调休时段">
        <el-option
          v-for="item in interval"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
      </el-select>
      <el-input
        v-if="!vacationExist"
        v-model="description"
        style="margin: 10px 0 0 2px;width: 100%"
        type="textarea"
        :rows="2"
        placeholder="请输入调休理由"
      />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleVacation">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getList } from '@/api/schedule'
import { getListBy, save, update } from '@/api/vacation'

export default {
  name: 'HospitalScheduleTable',
  data() {
    return {
      current: new Date(),
      list: [],
      vacations: [],
      interval: [
        { label: '全天', value: 0 },
        { label: '上午', value: 1 },
        { label: '下午', value: 2 }
      ],
      vacationExist: false,
      vacationId: undefined,
      vacationDate: undefined,
      vacationInterval: undefined,
      description: undefined,
      dialogTips: undefined,
      dialogVisible: false
    }
  },
  watch: {
    current: {
      handler: 'getList',
      deep: true
    }
  },
  created() {
    this.getList()
    this.getVacationList()
  },
  methods: {
    // 获取list
    getList() {
      getList(this.current).then(response => {
        this.list = response.data
      })
    },
    // 获取调休列表
    getVacationList() {
      getListBy({
        doctorId: this.$store.state.user.uid,
        vacationDate: this.current
      }).then(response => {
        this.vacations = response.data
      })
    },
    // 打开弹窗
    handleDialogOpen(date) {
      // 判断vacations中是否有对应日期的调休记录
      this.isDateVacationExist(date)
      // 日期提示
      const dateTips = this.dateFormat(date, 'MM月DD日')
      // 若date为本日/之前则不打开
      let isOpen = !this.isDayBefore(date, new Date())
      if (!isOpen) {
        this.$message.error(dateTips + '不可申请/撤销调休啦!!!')
        return
      }
      isOpen = this.list.length !== 0
      if (!isOpen) {
        this.$message.error(dateTips + '还没有排班!!!!')
        return
      }
      this.dialogTips = '是否' + (this.vacationExist ? '撤销' : '申请') + dateTips + '调休?'
      this.vacationDate = date
      this.dialogVisible = true
    },
    handleVacation() {
      if (this.vacationInterval === undefined && !this.vacationExist) {
        this.$message.error('请先选择调休时段!!!')
        return
      }
      this.$confirm('是否确认提交该项申请', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const param = {
          vacationId: this.getVacationId(),
          doctorId: this.$store.state.user.uid,
          timeInterval: this.vacationInterval,
          vacationDate: this.vacationDate,
          description: this.description,
          status: this.vacationExist ? 0 : 1
        }
        return this.vacationExist ? update(param) : save(param)
      }).then(response => {
        this.dialogVisible = false
        this.getVacationList()
        this.$message({ type: 'success', message: response.msg })
      }).catch(() => { })
    },
    // 格式化日期
    dateFormat(date, format) {
      return this.$moment(date).format(format)
    },
    // 标签颜色
    tagColor(date) {
      let color = 'primary'
      if (this.isDayBefore(date, new Date())) {
        color = 'danger'
      } else if (this.isMonthBefore(new Date(), date)) {
        color = 'success'
      }
      return color
    },
    // 时间段提示
    tagInterval(data) {
      return this.interval[data].label
    },
    // 判断是否before是否在date之前
    isMonthBefore(before, date) {
      return this.$moment(before).isBefore(date, 'month')
    },
    // 判断是否before是否在date当天或者之前
    isDayBefore(before, date) {
      return this.$moment(before).isBefore(date, 'day')
    },
    // 判断日期是否存在日期子串
    isExist(date, subDate) {
      return this.dateFormat(date, 'YYYY-MM-DD').indexOf(subDate) !== -1
    },
    isDateVacationExist(date) {
      this.vacationExist = this.vacations.length !== 0
      // 判断vacations中是否有对应日期的调休记录
      if (this.vacationExist) {
        const list = this.vacations.filter(item => this.isExist(date, item.vacationDate))
        this.vacationExist = list.length !== 0
      }
    },
    getVacationId() {
      const list = this.vacations.filter(item => this.isExist(this.vacationDate, item.vacationDate))
      return list.length === 0 ? undefined : list[0].vacationId
    }
  }
}
</script>

<style lang="scss" scoped>
.order-reserve-container{
  margin: 20px 50px;
  .content-container{
    display: flex;
    flex-direction: row;
    .schedule-calendar{
      display: flex;
      flex-direction: column;
      font-size: 1vh;
      ::v-deep .el-calendar-table .el-calendar-day{
        // 1vh代表可视宽度1%
        height: 8vh;
        padding: 0;
      }
      ::v-deep .el-calendar__header,
      ::v-deep .el-calendar__body{
        align-self: center;
        width: 100%;
      }
      .cell-calendar{
        width: 100%;
        height: 100%;
        padding: 8px;
      }
      .tag-calendar{
          width: 180px;
      }
    }
  }
}

</style>
