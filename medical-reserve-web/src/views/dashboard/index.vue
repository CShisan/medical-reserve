<template>
  <div class="dashboard-editor-container">

    <panel-group
      :count-wait-reserve="statistics.countWaitReserve"
      :count-all-enquiry="statistics.countAllEnquiry"
      :count-week-all-enquiry="statistics.countWeekAllEnquiry"
    />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;border-radius: 3vh;">
      <div class="line-chart-box">
        <line-chart :chart-data="lineChartData" />
      </div>
    </el-row>

  </div>
</template>

<script>
import PanelGroup from './components/PanelGroup'
import LineChart from './components/LineChart'
import { statistics } from '@/api/system'

export default {
  name: 'DashboardAdmin',
  components: {
    PanelGroup,
    LineChart
  },
  data() {
    return {
      lineChartData: {
        expectedData: undefined,
        actualData: undefined
      },
      statistics: {
        countWeekEnquiry: undefined,
        countLastWeekEnquiry: undefined
      }
    }
  },
  created() {
    this.getStatistics()
  },
  methods: {
    getStatistics() {
      statistics().then(res => {
        const { data } = res
        this.statistics = data
        this.lineChartData = {
          expectedData: data.countWeekEnquiry,
          actualData: data.countLastWeekEnquiry
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  display: flex;
  flex-direction: column;
  padding: 32px;
  width: 100%;

  .line-chart-box{
    padding: 1vh 0;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 3vh;
    height: 100%;
    border: #e6ebf5 1px solid;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)
  }
}
</style>
