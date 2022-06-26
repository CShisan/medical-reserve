<template>
  <div>
    <el-card class="system-user-container">
      <div slot="header" class="header-container">
        <div>
          <span>用户管理</span>
        </div>
        <div>
          <el-input
            v-model="searchContext"
            placeholder="搜索UID/手机号码"
            size="mini"
            class="search-input"
            @keyup.enter.native="handleSearch"
          />
          <el-button type="primary" size="mini" @click="handleSearch">搜索</el-button>
        </div>
      </div>
      <div class="content-container">
        <el-table
          border
          stripe
          :data="list"
          :cell-style="{'padding-top': '5px','padding-bottom': '5px'}"
          style="width: 100%"
          tooltip-effect="dark"
          :default-sort="{prop: 'uid', order: 'ascending'}"
        >
          <el-table-column
            fixed
            prop="uid"
            label="UID"
            min-width="100"
            sortable
          />
          <el-table-column
            fixed
            prop="realName"
            label="真实姓名"
            min-width="100"
          />
          <el-table-column
            fixed
            prop="phone"
            label="手机号码"
            min-width="110"
          />
          <el-table-column
            prop="username"
            label="用户名"
            min-width="120"
          />
          <el-table-column
            label="头像"
            min-width="52"
          >
            <template slot-scope="scope">
              <el-image
                style="width: 30px; height: 30px"
                :src="scope.row.avatarUrl"
                :preview-src-list="[scope.row.avatarUrl]"
                fit="fill"
              />
            </template>
          </el-table-column>
          <el-table-column
            label="性别"
            min-width="50"
          >
            <template slot-scope="scope">
              <span>{{ scope.row.sex===0?'女':'男' }}</span>
            </template>
          </el-table-column>
          <el-table-column
            prop="updateTime"
            label="更新时间"
            min-width="150"
            :formatter="dateFormat"
          />
          <el-table-column
            prop="createTime"
            label="创建时间"
            min-width="150"
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
import { getList } from '@/api/user'
import { dateFormat } from '@/filters/index'

export default {
  name: 'SystemUser',
  components: { Pagination },
  data() {
    return {
      // 列表数据
      list: [],
      // 数据总数
      total: 0,
      // 查询参数
      listSearch: {
        uid: undefined,
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
      const name = { name: 'SystemUserForm' }
      const data = isNaN(id) ? { ...name } : { ...name, params: { id }}
      this.$router.push(data)
    },
    // 格式化日期
    dateFormat(row, column) {
      return dateFormat(row[column.property])
    },
    // 搜索
    handleSearch() {
      if (this.searchContext.length !== 11) {
        this.listSearch.phone = undefined
        this.listSearch.uid = this.searchContext
      } else {
        this.listSearch.uid = undefined
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

<style lang="scss">
.system-user-container{
  .lock-box{
    color: red;
  }
}
</style>

<style lang="scss" scoped>
.system-user-container{
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
