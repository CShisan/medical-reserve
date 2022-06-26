<template>
  <el-container class="workbench-container">
    <el-aside class="left-workbench" width="60vh">
      <div class="search-box">
        <span>选择患者：</span>
        <span>
          <el-input v-model="patientName" placeholder="搜索患者名称" />
        </span>
        <span>
          <el-button
            type="primary"
            icon="el-icon-search"
            circle
            @click="getWaitPatients"
          />
        </span>
      </div>
      <div class="patients-box">
        <el-tag class="tag">本时段待诊患者</el-tag>
        <el-table class="patients-table" :data="waitList" @row-click="selectPatient">
          <el-table-column label="预约ID" prop="reserveId" />
          <el-table-column label="患者名称" prop="patientName" />
          <el-table-column label="年龄" prop="age" />
        </el-table>
      </div>
      <div class="patients-box">
        <el-tag type="warning" class="tag">本日已诊患者</el-tag>
        <el-table class="patients-table" :data="completedList">
          <el-table-column label="预约ID" prop="reserveId" />
          <el-table-column label="患者名称" prop="patientName" />
          <el-table-column label="年龄" prop="age" />
        </el-table>
      </div>

    </el-aside>
    <el-container class="right-workbench">
      <el-main class="info-form-box">
        <el-steps :active="1" finish-status="success" align-center>
          <el-step
            title="患者已报到"
            icon="el-icon-s-claim"
          />
          <el-step
            title="问诊中"
            icon="el-icon-edit"
          />
          <el-step
            title="问诊完毕"
            icon="el-icon-s-check"
          />
        </el-steps>

        <el-form
          ref="dataForm"
          class="info-form"
          :rules="rules"
          :model="form"
          label-width="150px"
          style="width:800px"
        >
          <el-row :gutter="10">
            <el-col :span="12">
              <el-form-item label="预约ID" prop="reserveId">
                <el-input v-model="form.reserveId" disabled placeholder="预约ID" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="患者名称" prop="patientName">
                <el-input v-model="form.patientName" disabled placeholder="患者名称" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="12">
              <el-form-item label="性别" prop="sex">
                <el-radio-group v-model="form.sex" disabled>
                  <el-radio :label="1">男</el-radio>
                  <el-radio :label="0">女</el-radio>
                </el-radio-group>

              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="年龄" prop="age">
                <el-input v-model="form.age" disabled placeholder="年龄" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="10">
            <el-col :span="24">
              <el-form-item label="问诊备注" prop="remark">
                <el-input
                  v-model="form.remark"
                  type="textarea"
                  placeholder="问诊备注"
                  :rows="6"
                />
              </el-form-item>
            </el-col>
          </el-row>
          <el-form-item class="el-form-buttons pt-20">
            <el-button icon="el-icon-back" @click="handleReturn">返回</el-button>
            <el-button
              type="primary"
              icon="el-icon-check"
              :loading="submitLoading"
              @click="submitForm"
            >
              生成问诊记录
            </el-button>
          </el-form-item>
        </el-form>
      </el-main>
      <div v-if="maskVisible" class="mask" />
    </el-container>
  </el-container>
</template>

<script>
import { getWaitPatients, getCompletedPatients } from '@/api/reserve'
import { save } from '@/api/enquiry'

export default {
  name: 'WorkbenchDoctor',
  data() {
    return {
      patientName: undefined,
      waitList: [],
      completedList: [],
      form: {
        reserveId: undefined,
        patientName: undefined,
        sex: undefined,
        age: undefined,
        remark: undefined
      },
      maskVisible: true,
      submitLoading: false,
      rules: {}
    }
  },
  watch: {
    patientName: {
      handler: 'getWaitPatients',
      deep: true
    },
    form: {
      handler: 'handleMaskVisible',
      deep: true
    }
  },
  created() {
    this.getWaitPatients()
    this.getCompletedPatients()
  },
  methods: {
    getWaitPatients() {
      getWaitPatients({
        doctorId: this.$store.state.user.uid,
        patientName: this.patientName
      }).then(res => {
        this.waitList = res.data
        this.waitList.map((item, index, list) => {
          list[index] = {
            ...item,
            age: this.getAge(item.birthday),
            remark: undefined
          }
        })
      })
    },
    getCompletedPatients() {
      getCompletedPatients({
        doctorId: this.$store.state.user.uid
      }).then(res => {
        this.completedList = res.data
        this.completedList.map((item, index, list) => {
          list[index] = { ...item, age: this.getAge(item.birthday) }
        })
      })
    },
    getAge(birthday) {
      // 出生时间 毫秒
      const birthDayTime = new Date(birthday).getTime()
      // 当前时间 毫秒
      const nowTime = new Date().getTime()
      // 一年毫秒数(365 * 86400000 = 31536000000)
      return Math.ceil((nowTime - birthDayTime) / 31536000000)
    },
    selectPatient(row) {
      this.form = row
    },
    // 表单提交
    submitForm() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.$confirm('是否确认提交该项数据?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.submitLoading = true
            return save(this.form)
          }).then(response => {
            this.$message({ type: 'success', message: response.msg })
            setTimeout(() => {
              this.submitLoading = false
              this.getWaitPatients()
              this.getCompletedPatients()
              this.handleReturn()
            }, 1000)
          }).catch(() => {
            this.submitLoading = false
          })
        }
      })
    },
    handleMaskVisible() {
      if (this.form.reserveId) {
        this.maskVisible = false
      } else {
        this.maskVisible = true
      }
    },
    // 返回
    handleReturn() {
      this.form = {
        reserveId: undefined,
        patientName: undefined,
        sex: undefined,
        age: undefined,
        remark: undefined
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.workbench-container{
  .left-workbench{
    background-color: #fff;
    .search-box{
      margin-top: 3vh;
      display: flex;
      justify-content: center;
      align-items: center;
      span{
        margin-right: 1.8vh;
      }
    }
    .patients-box{
      border-top: #d8dce5 1px solid;
      margin-top: 3vh;
      padding: 2vh;
      .tag{
        width: 100%;
      }
      .patients-table{
        min-height: 26vh;
        max-height: 26vh;
      }
    }
  }
  .right-workbench{
    width: 100%;
    .info-form-box{
      margin: 5vh;
      width: 100%;
      .info-form{
        margin-left: 5vh;
        margin-top: 10vh;
      }
    }
    .mask{
      position: absolute;
      width: 100%;
      height: 100%;
      background-color: rgba(0,0,0,.5);
      z-index: 999;
    }
  }
}
</style>
