<template>
  <div>
    <el-card class="order-reserve-container">
      <div slot="header" class="header-container">
        <div class="header-container-left">
          <span>调休审批</span>
          <span>
            <el-select
              slot="reference"
              v-model="listSearch.deptId"
              filterable
              clearable
              placeholder="请选择科室"
              style="margin: 0 10px 0 60px; width: 35%"
              size="small"
            >
              <el-option
                v-for="item in deptList"
                :key="item.deptId"
                :label="item.deptName"
                :value="item.deptId"
              />
            </el-select>
            <el-button
              type="primary"
              round
              size="small"
              @click="getList"
            >
              查询科室
            </el-button>
          </span>
        </div>
        <div>
          <el-input
            v-model="listSearch.realName"
            placeholder="搜索医生名称"
            size="mini"
            class="search-input"
            @keyup.enter.native="getList"
          />
          <el-button type="primary" size="mini" @click="getList">搜索</el-button>
        </div>
      </div>
      <div class="content-container">
        <el-table
          ref="dataTable"
          border
          stripe
          :data="list"
          :cell-style="{'padding-top': '5px','padding-bottom': '5px'}"
          style="width: 100%"
          tooltip-effect="dark"
          :default-sort="{prop: 'reserveId', order: 'ascending'}"
        >
          <el-table-column
            fixed
            prop="vacationId"
            label="调休记录ID"
            min-width="130"
            sortable
          />
          <el-table-column
            fixed
            prop="realName"
            label="医生名称"
            min-width="150"
            sortable
          />
          <el-table-column
            prop="deptName"
            label="所属科室"
            min-width="150"
          />
          <el-table-column
            prop="vacationDate"
            label="调休时间"
            min-width="150"
          />
          <el-table-column
            prop="timeInterval"
            label="调休时段"
            min-width="150"
          >
            <template slot-scope="scope">
              {{ handleIntervalFormat(scope.row.timeInterval) }}
            </template>
          </el-table-column>
          <el-table-column
            prop="description"
            label="调休备注"
            min-width="150"
            show-overflow-tooltip
          />
          <el-table-column
            fixed="right"
            label="操作"
            min-width="180"
          >
            <template slot-scope="scope">
              <div v-if="scope.row.status === 1">
                <el-button
                  size="mini"
                  type="success"
                  @click="handleUpdate(scope.row, 2)"
                >通过审批</el-button>
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleUpdate(scope.row, 0)"
                >不批准</el-button>
              </div>
              <div v-if="scope.row.status !== 1">
                <el-tag :type="scope.row.status === 0 ? 'danger' : 'success'">
                  {{ scope.row.status === 0 ? '已取消审批' : '已通过审批' }}
                </el-tag>
              </div>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          :total="total"
          :page="listSearch.current"
          :limit="listSearch.size"
          @pagination="handlePagination"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { getList, update } from '@/api/vacation'
import { getAllDepts } from '@/api/department'

export default {
  name: 'HospitalVacation',
  components: { Pagination },
  data() {
    return {
      // 列表数据
      list: [],
      // 数据总数
      total: 0,
      // 查询参数
      listSearch: {
        deptId: undefined,
        realName: undefined,
        // 当前页
        current: 1,
        // 每页大小
        size: 10
      },
      searchContext: '',
      deptList: []
    }
  },
  watch: {
    listSearch: {
      handler: 'getList',
      deep: true
    }
  },
  created() {
    this.getList()
    this.getDeptList()
  },
  methods: {
    handleIntervalFormat(interval) {
      let result
      switch (interval) {
        case 0: result = '全天'; break
        case 1: result = '上午'; break
        case 2: result = '下午'; break
      }
      return result
    },
    // 更新
    handleUpdate(row, status) {
      this.$confirm('是否确认提交该项数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return update({ ...row, status })
      }).then(response => {
        this.getList()
        this.$message({ type: 'success', message: response.msg })
      }).catch(() => { })
    },
    // 获取list
    getList() {
      getList(this.listSearch).then(response => {
        const res = response.data
        this.list = res.records
        this.total = res.total
        this.listSearch.current = res.current
        this.listSearch.size = res.size
      })
    },
    // 分页
    handlePagination(pagination) {
      const { page, limit } = pagination
      this.listSearch.current = page
      this.listSearch.size = limit
    },
    getDeptList() {
      getAllDepts().then(response => {
        this.deptList = response.data
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.order-reserve-container{
  margin: 20px 50px;
  .header-container{
    display: flex;
    align-self: center;
    justify-content: space-between;
    font-size: 18px;
    .search-input{
      margin-right: 5px;
      width: 160px;
    }
  }
}

</style>
