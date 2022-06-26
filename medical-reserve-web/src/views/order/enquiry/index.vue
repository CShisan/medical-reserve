<template>
  <div>
    <el-card class="order-enquiry-container">
      <div slot="header" class="header-container">
        <div>
          <span>问诊管理</span>
        </div>
        <div>
          <el-input
            v-model="listSearch.doctorName"
            placeholder="搜索医生名称"
            size="mini"
            class="search-input"
            @keyup.enter.native="getList"
          />
          <el-input
            v-model="listSearch.patientName"
            placeholder="搜索患者名称"
            size="mini"
            class="search-input"
            @keyup.enter.native="getList"
          />
          <el-button style="margin-left: 10px" type="primary" size="mini" @click="getList">搜索</el-button>
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
          :default-sort="{prop: 'enquiryId', order: 'ascending'}"
        >
          <el-table-column
            fixed
            prop="enquiryId"
            label="问诊记录ID"
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
            prop="remark"
            label="备注"
            min-width="150"
            show-overflow-tooltip
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
import { getList, deleteRole } from '@/api/enquiry'
import { dateFormat } from '@/filters/index'

export default {
  name: 'OrderEnquiry',
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
      searchContext: ''
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
      const name = { name: 'OrderEnquiryForm' }
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
.order-enquiry-container{
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
