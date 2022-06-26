<template>
  <div>
    <el-card class="order-reserve-container">
      <div slot="header" class="header-container">
        <div>
          <span>预约管理</span>
        </div>
        <div>
          <el-input
            v-model="listSearch.doctorName"
            placeholder="搜索医生名称"
            size="mini"
            class="search-input"
            @keyup.enter.native="handleSearch"
          />
          <el-input
            v-model="listSearch.patientName"
            placeholder="搜索患者名称"
            size="mini"
            class="search-input"
            @keyup.enter.native="handleSearch"
          />
          <el-button type="primary" size="mini" @click="handleSearch">搜索</el-button>
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
            prop="reserveId"
            label="预约记录ID"
            min-width="118"
            sortable
          />
          <el-table-column
            fixed
            prop="patientName"
            label="患者名称"
            min-width="155"
            sortable
          />
          <el-table-column
            prop="doctorName"
            label="医生名称"
            min-width="155"
          />
          <el-table-column
            prop="updateTime"
            label="更新时间"
            min-width="180"
            :formatter="dateFormat"
          />
          <el-table-column
            prop="createTime"
            label="创建时间"
            min-width="180"
            :formatter="dateFormat"
          />
          <el-table-column
            fixed="right"
            prop="status"
            label="订单状态"
            min-width="80"
          >
            <template slot-scope="scope">
              <el-tag :type="statusTagType[scope.row.status]">
                {{ statusTag[scope.row.status] }}
              </el-tag>
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
import { getList, deleteRole } from '@/api/reserve'
import { dateFormat } from '@/filters/index'

export default {
  name: 'OrderReserve',
  components: { Pagination },
  data() {
    return {
      // 列表数据
      list: [],
      // 数据总数
      total: 0,
      // 查询参数
      listSearch: {
        patientName: undefined,
        doctorName: undefined,
        // 当前页
        current: 1,
        // 每页大小
        size: 10
      },
      statusTag: ['已取消', '待报到', '已完成'],
      statusTagType: ['danger', 'info', 'success']
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
  },
  methods: {
    // 跳转表单
    handleToForm(id) {
      const name = { name: 'OrderReserveForm' }
      const data = isNaN(id) ? { ...name } : { ...name, params: { id }}
      this.$router.push(data)
    },
    // 格式化日期
    dateFormat(row, column) {
      return dateFormat(row[column.property])
    },
    // 删除
    handleDelete(row) {
      this.$confirm('是否确认删除该项数据?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        const data = {
          id: row.id
        }
        return deleteRole(data)
      }).then(response => {
        this.getList()
        this.$message({ type: 'success', message: response.msg })
      }).catch(() => { })
    },
    // 搜索
    handleSearch() {
      this.getList()
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
    }
  }
}
</script>

<style lang="scss" scoped>
.order-reserve-container{
  margin: 20px 50px;
  .header-container{
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 18px;
    .add-btn{
      margin-left: 20px;
    }
    .search-input{
      margin-right: 5px;
      width: 160px;
    }
  }
}

</style>
