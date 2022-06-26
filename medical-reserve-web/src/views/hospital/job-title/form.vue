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
          <el-form-item label="职称ID" prop="titleId">
            <el-input v-model="form.titleId" disabled placeholder="职称ID" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="职称名称" prop="titleName">
            <el-input v-model="form.titleName" placeholder="请输入职称名称" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="挂号费用(元)" prop="charge">
            <el-input v-model="form.charge" placeholder="请输入挂号费用" />
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
import { getOne, save, update } from '@/api/job-title'
import { validFormData } from '@/utils/form-vaidate'

export default {
  name: 'HospitalJobTitleForm',
  data() {
    return {
      // 表单标题
      title: '',
      // 表单属性
      form: {
        id: undefined,
        titleId: undefined,
        titleName: undefined,
        charge: undefined
      },
      // 表单校验
      rules: {
        charge: [
          { required: true, message: '请输入挂号费用', trigger: 'blur' },
          { validator: validFormData, trigger: 'blur' }
        ],
        titleName: [
          { required: true, message: '请输入职称名称', trigger: 'blur' },
          { min: 2, max: 18, message: '长度在 2 到 18 个字符', trigger: 'blur' }
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
      this.title = '编辑职称信息'
    } else {
      this.title = '添加职称'
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
      this.$router.push({ name: 'HospitalJobTitle' })
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
