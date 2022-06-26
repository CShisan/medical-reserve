<template>
  <div>
    <el-card class="order-reserve-container">
      <div slot="header" class="header-container">
        <span>排班表</span>
        <el-select v-model="rule" class="rule-selector" placeholder="选择排班规则" size="small">
          <el-option
            v-for="item in rules"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        <el-button
          type="primary"
          size="small"
          round
          class="auto-btn"
          @click="handleDialogOpen"
        >自动生成本月排班表</el-button>
      </div>
      <div class="content-container">
        <el-calendar v-model="current" class="schedule-calendar">
          <template slot="dateCell" slot-scope="{date, data}">
            <div v-for="(item,index) in list" :key="item.id">
              <div v-if="index === 0" :class="data.isSelected ? 'is-selected' : ''">
                {{ dateFormat(data.day, 'MM-DD') }} {{ data.isSelected ? '✔️' : '' }}
              </div>
              <el-tag
                v-if="item.scheduleDate.indexOf(data.day) !== -1"
                :type="tagColor(date)"
              >
                {{ item.rotationKey }} 班次
              </el-tag>
            </div>
          </template>
        </el-calendar>
      </div>
    </el-card>

    <el-dialog
      :title="dateFormat(current, 'YYYY年MM月')"
      :visible.sync="dialogVisible"
      width="30%"
      custom-class="date-selector-dialog"
    >
      <div>选择需要排班的日期</div>
      <el-checkbox v-model="checkAll" :indeterminate="isIndeterminate" @change="handleCheckAllChange">全选</el-checkbox>
      <el-checkbox-group v-model="dateList" @change="handleCheckedChange">
        <el-row :gutter="10">
          <el-col v-for="item in 31" :key="item" :span="4">
            <el-checkbox v-if="showCheckBox(item)" :label="item">
              {{ item }}号
            </el-checkbox>
          </el-col>
        </el-row>
      </el-checkbox-group>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleAutoSchedule">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getList, autoSchedule } from '@/api/schedule'

const dateOptions = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31]

export default {
  name: 'HospitalDepartment',
  data() {
    return {
      current: new Date(),
      currentMaxDays: this.$moment(new Date()).daysInMonth(),
      list: [],
      dateList: dateOptions,
      dates: dateOptions,
      isIndeterminate: true,
      checkAll: false,
      rules: [
        { label: '单天排班规则(AB规则)', value: 'oodDays' },
        { label: '全天排班规则(All规则)', value: 'everyDay' }
      ],
      rule: undefined,
      dialogVisible: false
    }
  },
  watch: {
    current: {
      handler() {
        this.getList()
        this.updateCurrentMaxDays()
      },
      deep: true
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 跳转表单
    handleToForm(id) {
      const name = { name: 'HospitalDepartmentForm' }
      const data = isNaN(id) ? { ...name } : { ...name, params: { id }}
      this.$router.push(data)
    },
    // 自动排班
    handleAutoSchedule() {
      const tips = this.list.length > 0 ? '本月已有排班表，是否重新生成?' : '是否自动生成排班表?'
      this.$confirm(tips, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return autoSchedule({
          scheduleDate: this.current,
          rule: this.rule,
          dateList: this.dateList
        })
      }).then(response => {
        this.dialogVisible = false
        this.getList()
        this.$message({ type: 'success', message: response.msg })
      }).catch(() => { })
    },
    // 打开弹窗
    handleDialogOpen() {
      if (!this.rule) {
        this.$message.error('请先选择排班规则!')
        return
      }
      if (this.isMonthBefore(this.current, new Date())) {
        this.$message.error(
          this.dateFormat(new Date(), 'YYYY-MM') + '月之前的排班表不可重新生成!'
        )
        return
      }
      this.dialogVisible = true
    },
    // 获取list
    getList() {
      getList(this.current).then(response => {
        this.list = response.data
      })
    },
    // 日期格式化
    dateFormat(date, format) {
      return this.$moment(date).format(format)
    },
    // 标签颜色(本月则primary,本月前则danger,本月后则success)
    tagColor(date) {
      let color = 'primary'
      if (this.isMonthBefore(date, new Date())) {
        color = 'danger'
      } else if (this.isMonthBefore(new Date(), date)) {
        color = 'success'
      }
      return color
    },
    // 判断before是否在dete这个月之前
    isMonthBefore(before, date) {
      return this.$moment(before).isBefore(date, 'month')
    },
    showCheckBox(day) {
      return day <= this.currentMaxDays
    },
    updateCurrentMaxDays() {
      this.currentMaxDays = this.$moment(this.current).daysInMonth()
    },
    handleCheckAllChange(val) {
      this.dateList = val ? dateOptions : []
      this.isIndeterminate = false
    },
    handleCheckedChange(value) {
      const checkedCount = value.length
      this.checkAll = checkedCount === this.dates.length
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.dates.length
    }
  }
}
</script>

<style lang="scss">

</style>

<style lang="scss" scoped>
.order-reserve-container{
  margin: 20px 50px;
  .header-container{
    .rule-selector{
      margin-left: 100px;
    }
    .auto-btn{
      margin-left: 20px;
    }
  }
  .content-container{
    display: flex;
    flex-direction: row;
    .schedule-calendar{
      display: flex;
      flex-direction: column;
      font-size: 1vh;
      ::v-deep .el-calendar-table .el-calendar-day{
        // 1vh代表可视宽度1%
        height: 8vh
      }
      ::v-deep .el-calendar__header,
      ::v-deep .el-calendar__body{
        align-self: center;
        width: 80%;
      }
    }
  }
}
</style>
