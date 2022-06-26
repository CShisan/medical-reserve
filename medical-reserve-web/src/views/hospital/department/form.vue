<template>
  <el-card class="role-form-container">
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
      <el-row :gutter="10">
        <el-col v-if="formType" :span="12">
          <el-form-item label="科室ID" prop="deptId">
            <el-input v-model="form.deptId" disabled placeholder="科室ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="科室名称" prop="deptName">
            <el-input v-model="form.deptName" placeholder="请输入科室名称" />
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
import { getOne, save, update } from '@/api/department'

export default {
  name: 'HospitalDepartmentForm',
  data() {
    return {
      // 表单标题
      title: '',
      // 表单属性
      form: {
        id: undefined,
        deptId: undefined,
        deptName: undefined
      },
      // 表单校验
      rules: {
        deptName: [
          { required: true, message: '请输入科室名称', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ]
      },
      // 提交按钮加载状态
      submitLoading: true
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
      this.title = '编辑科室信息'
    } else {
      this.title = '添加科室'
      this.submitLoading = false
    }
  },
  methods: {
    // 根据id获取信息(编辑会触发)
    getOne() {
      getOne(this.$route.params.id).then(response => {
        this.form = response.data
        this.submitLoading = false
      })
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
            return this.formType ? update(this.form) : save(this.form)
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
      this.$router.push({ name: 'HospitalDepartment' })
    }
  }
}
</script>

<style lang="scss">
</style>

<style lang="scss" scoped>
.role-form-container{
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
