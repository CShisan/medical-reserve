<template>
  <div>
    <!-- 主界面 -->
    <el-card class="order-reserve-container">
      <div slot="header" class="header-container">
        <div>
          <span>班次管理</span>
          <el-button
            type="primary"
            round
            class="add-btn"
            size="small"
            @click="handleToForm"
          >
            添加班次信息
          </el-button>
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
            prop="rotationId"
            label="班次ID"
            min-width="118"
            sortable
          />
          <el-table-column
            fixed
            prop="rotationKey"
            label="班次KEY"
            min-width="155"
            sortable
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
            min-width="270"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                @click="handleDrawerOpen(scope.row)"
              >管理该班次医生</el-button>
              <el-button
                size="mini"
                @click="handleToForm(scope.row.id)"
              >编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.row)"
              >删除</el-button>
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
    <!-- 管理班次医生抽屉 -->
    <el-drawer
      :title="textTips.rotationTips + '科室管理'"
      :visible.sync="drawerVisible"
      direction="rtl"
      :before-close="handleDrawerClose"
    >
      <el-header>
        <el-popover
          placement="right"
          title="Tips："
          width="280"
          trigger="hover"
          content="首先选择科室，然后对该班次对应的科室医生进行管理！"
          style="margin-left: 20px"
        >
          <el-select
            slot="reference"
            v-model="deptId"
            filterable
            placeholder="请选择科室"
            style="width: 40%"
          >
            <el-option
              v-for="item in deptList"
              :key="item.deptId"
              :label="item.deptName"
              :value="item.deptId"
            />
          </el-select>
        </el-popover>
        <el-button
          style="margin-left: 20px"
          type="primary"
          round
          @click="getDoctorList"
        >搜索</el-button>
        <el-button
          style="margin-left: 20px"
          type="primary"
          round
          @click="handleDialogOpen"
        >添加医生</el-button>
      </el-header>
      <el-main>
        <el-table :data="doctorList">
          <el-table-column
            prop="doctorId"
            label="医生ID"
            min-width="120"
          />
          <el-table-column
            prop="doctorName"
            label="医生名称"
            min-width="100"
          />
          <el-table-column
            prop="phone"
            label="手机号码"
            min-width="120"
          />
          <el-table-column
            fixed="right"
            label="操作"
            min-width="100"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="danger"
                @click="handleDrawerDelete(scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-drawer>
    <!-- 添加医生弹窗 -->
    <el-dialog
      :title="'添加 ' + textTips.deptTips +' 医生'"
      :visible.sync="dialogVisible"
      width="18%"
    >
      <el-select
        v-model="doctorId"
        filterable
        placeholder="请选择医生"
        style="width: 90%"
      >
        <el-option
          v-for="item in doctors"
          :key="item.doctorId"
          :label="item.realName + '-' + item.phone"
          :value="item.doctorId"
        />
      </el-select>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;doctorId = undefined">取 消</el-button>
        <el-button type="primary" @click="handleAddDoctor">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import { dateFormat } from '@/filters/index'
import { getList, del } from '@/api/rotation'
import { getAllDepts } from '@/api/department'
import { getDoctorList, saveDr, delDr } from '@/api/doctor-rotation'
import { getListBy } from '@/api/doctor'

export default {
  name: 'HospitalRotation',
  components: { Pagination },
  data() {
    return {
      // 列表数据
      list: [],
      // 数据总数
      total: 0,
      // 查询参数
      listSearch: {
        // 当前页
        current: 1,
        // 每页大小
        size: 10
      },
      // 文本显示
      textTips: {
        rotationTips: undefined,
        deptTips: undefined
      },
      rotationId: undefined,
      deptId: undefined,
      // 用于添加医生
      doctorId: undefined,
      // 全科室列表
      deptList: [],
      // 根据deptId和rotationId查询的医生列表
      doctorList: [],
      // 根据deptId查询的医生列表
      doctors: [],
      drawerVisible: false,
      dialogVisible: false
    }
  },
  watch: {
    listSearch: {
      handler: 'getList',
      deep: true
    },
    deptId: {
      handler: 'getDoctorList',
      deep: true
    }
  },
  created() {
    this.getList()
    this.getDeptList()
  },
  methods: {
    // 跳转表单
    handleToForm(id) {
      const name = { name: 'HospitalRotationForm' }
      const data = isNaN(id) ? { ...name } : { ...name, params: { id }}
      this.$router.push(data)
    },
    // 打开抽屉
    handleDrawerOpen(data) {
      this.drawerVisible = true
      if (this.drawerVisible && data) {
        this.rotationId = data.rotationId
        this.textTips.rotationTips = data.rotationKey + '班次'
      }
    },
    // 关闭抽屉
    handleDrawerClose() {
      this.drawerVisible = false
      this.rotationId = undefined
      this.textTips.rotationTips = undefined
      this.doctorList = []
    },
    // 打开弹窗
    handleDialogOpen() {
      if (this.deptId) {
        this.dialogVisible = !this.dialogVisible
        if (this.dialogVisible) {
          const temp = this.deptList.filter(dept => dept.deptId === this.deptId)
          this.textTips.deptTips = temp[0].deptName + '科室'
          this.getDoctors()
        } else {
          this.textTips.rotationTips = undefined
        }
      } else {
        this.$message.warning('请先选择科室!')
      }
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
        return del(row.id)
      }).then(response => {
        this.getList()
        this.$message({ type: 'success', message: response.msg })
      }).catch(() => { })
    },
    // 抽屉删除
    handleDrawerDelete(row) {
      delDr(row.id).then(() => {
        this.getDoctorList()
      })
    },
    // 添加医生
    handleAddDoctor() {
      const param = { rotationId: this.rotationId, deptId: this.deptId, doctorId: this.doctorId }
      saveDr(param).then(response => {
        const nonExist = response.data
        if (nonExist) {
          this.getDoctorList()
          this.$message.success(response.msg)
        } else {
          this.$notify.error({
            title: '错误',
            message: '该医生已存在被安排的班次!',
            offset: 80
          })
        }
        this.doctorId = undefined
        this.dialogVisible = false
      }).catch(response => {
        this.$message.error(response.msg)
      })
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
    // 获取全科室
    getDeptList() {
      getAllDepts().then(response => {
        this.deptList = response.data
      })
    },
    // 根据deptId和rotationId获取医生列表
    getDoctorList() {
      const param = { rotationId: this.rotationId, deptId: this.deptId }
      getDoctorList(param).then(response => {
        this.doctorList = response.data
      })
    },
    // 根据deptId获取医生列表
    getDoctors() {
      const param = { deptId: this.deptId }
      getListBy(param).then(response => {
        this.doctors = response.data
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
