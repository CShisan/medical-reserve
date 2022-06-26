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
          <el-form-item label="医生ID" prop="doctorId">
            <el-input v-model="form.doctorId" disabled placeholder="医生ID" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="医生名称" prop="realName">
            <el-input v-model="form.realName" placeholder="请输入医生名称" />
          </el-form-item>
        </el-col>
        <el-col v-if="!formType" :span="12">
          <el-form-item label="密码" prop="Password">
            <el-input v-model="form.password" placeholder="请输入密码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="所属科室" prop="deptId">
            <el-select
              v-model="form.deptId"
              filterable
              placeholder="请选择科室"
              style="width: 100%"
            >
              <el-option
                v-for="item in deptList"
                :key="item.deptId"
                :label="item.deptName"
                :value="item.deptId"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="医生职称" prop="jobTitleId">
            <el-select
              v-model="form.jobTitleId"
              filterable
              placeholder="请选择职称"
              style="width: 100%"
            >
              <el-option
                v-for="item in titleList"
                :key="item.titleId"
                :label="item.titleName"
                :value="item.titleId"
              />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="手机号码" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入手机号码" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="身份证号码" prop="idCard">
            <el-input v-model="form.idCard" placeholder="请输入身份证号码" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="10">
        <el-col :span="24">
          <el-form-item label="个人介绍" prop="description">
            <el-input
              v-model="form.description"
              type="textarea"
              :rows="3"
              placeholder="请输入个人介绍"
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
import { getOne, save, update } from '@/api/doctor'
import { getAllDepts } from '@/api/department'
import { getAllTitles } from '@/api/job-title'
import { validFormData } from '@/utils/form-vaidate'

export default {
  name: 'HospitalDoctorForm',
  data() {
    return {
      // 表单标题
      title: '',
      // 表单属性
      form: {
        id: undefined,
        doctorId: undefined,
        realName: undefined,
        phone: undefined,
        idCard: undefined,
        password: 123,
        deptId: undefined,
        jobTitleId: undefined,
        description: undefined
      },
      // 表单校验
      rules: {
        phone: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { validator: validFormData, trigger: 'blur' }
        ],
        idCard: [
          { required: true, message: '请输入身份证号码', trigger: 'blur' },
          { min: 10, max: 18, message: '长度在 10 到 18 个字符', trigger: 'blur' }
        ],
        realName: [
          { required: true, message: '请输入真实名字', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        deptId: [{ required: true, message: '请选择科室', trigger: 'change' }],
        jobTitleId: [{ required: true, message: '请选择职称', trigger: 'change' }]
      },
      // 提交按钮加载状态
      submitLoading: true,
      deptList: [],
      titleList: []
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
      this.title = '编辑医生信息'
    } else {
      this.title = '添加医生'
      this.submitLoading = false
    }
    this.getDeptList()
    this.getTitleList()
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
      this.$router.push({ name: 'HospitalDoctor' })
    },
    getDeptList() {
      getAllDepts().then(response => {
        this.deptList = response.data
      })
    },
    getTitleList() {
      getAllTitles().then(response => {
        this.titleList = response.data
      })
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
