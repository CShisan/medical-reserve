<template>
  <el-card class="enquiry-form-container">
    <div slot="header" class="clearfix">
      <span>{{ title }}</span>
    </div>
    <el-form
      ref="dataForm"
      :rules="rules"
      :model="form"
      label-width="150px"
      style="width: 800px"
    >
      <el-row v-if="formType" :gutter="10">
        <el-col :span="12">
          <el-form-item label="问诊记录ID" prop="enquiryId">
            <el-input v-model="form.enquiryId" disabled placeholder="问诊记录ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="问诊时间" prop="createTime">
            <el-input v-model="enquiryTime" disabled placeholder="问诊时间" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="患者名称" prop="patientName">
            <el-input v-model="form.patientName" disabled placeholder="请输入患者名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="医生名称" prop="doctorName">
            <el-input v-model="form.doctorName" disabled placeholder="请输入医生名称" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="24">
          <el-form-item label="备注" prop="remark">
            <el-input
              v-model="form.remark"
              type="textarea"
              :rows="2"
              placeholder="请输入备注内容"
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
          保存
        </el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { getOne, update } from '@/api/enquiry'
import { dateFormat } from '@/filters/index'

export default {
  name: 'OrderEnquiryForm',
  data() {
    return {
      // 表单标题
      title: '编辑问诊记录',
      // 表单属性
      form: {
        id: undefined,
        enquiryId: undefined,
        patientId: undefined,
        doctorId: undefined,
        remark: undefined
      },
      // 表单校验
      rules: {
      },
      // 提交按钮加载状态
      submitLoading: true,
      enquiryTime: undefined
    }
  },
  computed: {
    formType: function() {
      return this.$route.params.id !== undefined
    }
  },
  created() {
    if (this.formType) {
      this.getOne()
    }
  },
  methods: {
    // 根据id获取信息(编辑会触发)
    getOne() {
      getOne(this.$route.params.id).then(response => {
        this.form = response.data
        this.enquiryTime = this.dateFormat(this.form.createTime)
        this.submitLoading = false
      })
    },
    // 格式化日期
    dateFormat(date) {
      return dateFormat(date)
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
            return update(this.form)
          }).then(response => {
            this.$message({ type: 'success', message: response.msg })
            setTimeout(() => {
              this.submitLoading = false
              this.handleReturn()
            }, 1000)
          }).catch(() => {
            this.submitLoading = false
          })
        }
      })
    },
    // 返回
    handleReturn() {
      this.$router.push({ name: 'OrderEnquiry' })
    }
  }
}
</script>

<style lang="scss" scoped>
.enquiry-form-container{
    margin: 20px 50px;
    .form-item-width {
        width: 100%;
    }
    .assign-diglog{
      overflow: auto;
      height: 280px;
    }
}
</style>
