<template>
  <div>
    <el-card class="order-reserve-container">
      <div slot="header" class="header-container">
        <div>
          <span>医生管理</span>
          <el-button
            type="primary"
            round
            class="add-btn"
            size="small"
            @click="handleToForm"
          >
            添加医生
          </el-button>
        </div>
        <div>
          <el-input
            v-model="searchContext"
            placeholder="搜索医生名称/手机号码"
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
            prop="doctorId"
            label="医生ID"
            min-width="150"
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
            prop="titleName"
            label="职称"
            min-width="150"
          />
          <el-table-column
            prop="phone"
            label="手机号码"
            min-width="150"
          />
          <el-table-column
            prop="description"
            label="个人介绍"
            min-width="150"
            show-overflow-tooltip
          />
          <el-table-column
            fixed="right"
            label="操作"
            min-width="100"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleToForm(scope.row.id)"
              >编辑</el-button>
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
import { getList } from '@/api/doctor'
import { dateFormat } from '@/filters/index'

export default {
  name: 'HospitalDoctor',
  components: { Pagination },
  data() {
    return {
      // 列表数据
      list: [],
      // 数据总数
      total: 0,
      // 查询参数
      listSearch: {
        realName: undefined,
        phone: undefined,
        // 当前页
        current: 1,
        // 每页大小
        size: 10
      },
      searchContext: ''
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 跳转表单
    handleToForm(id) {
      const name = { name: 'HospitalDoctorForm' }
      const data = isNaN(id) ? { ...name } : { ...name, params: { id }}
      this.$router.push(data)
    },
    // 格式化日期
    dateFormat(row, column) {
      return dateFormat(row[column.property])
    },
    // 搜索
    handleSearch() {
      if (isNaN(this.searchContext)) {
        this.listSearch.realName = this.searchContext
      } else {
        this.listSearch.phone = this.searchContext
      }
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
