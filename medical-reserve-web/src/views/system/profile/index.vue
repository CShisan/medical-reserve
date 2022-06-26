<template>
  <div>
    <el-card class="role-form-container">
      <div slot="header" class="clearfix">
        <span>个人信息</span>
      </div>
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="form"
        label-width="150px"
        style="width: 800px"
      >
        <el-row :gutter="10">
          <el-col :span="12">
            <el-form-item label="医生名称" prop="realName">
              <el-input v-model="form.realName" disabled placeholder="请输入医生名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="密码" prop="Password">
              <el-button type="primary" @click="dialogVisible = true">更改密码</el-button>
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
                disabled
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
            <el-form-item label="医生职称" prop="titleName">
              <el-select
                v-model="form.jobTitleId"
                filterable
                placeholder="请选择职称"
                style="width: 100%"
                disabled
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
            <el-form-item label="身份证号码" prop="phone">
              <el-input v-model="form.idCard" disabled placeholder="请输入身份证号码" />
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
    <el-dialog
      title="更改密码"
      :visible.sync="dialogVisible"
      width="18%"
      :before-close="handleClose"
    >
      <el-input v-model="passwordForm.password" type="password" class="item-dialog" placeholder="请输入密码" />
      <el-input v-model="passwordForm.newPassword" type="password" class="item-dialog" placeholder="请输入新密码" />
      <el-input v-model="passwordForm.reNewPassword" type="password" class="item-dialog" placeholder="请重复输入新密码" />
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleUpdatePassword">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { getOneByDoctorId, update } from '@/api/doctor'
import { getAllDepts } from '@/api/department'
import { getAllTitles } from '@/api/job-title'
import { updatePassword } from '@/api/user'

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
        deptId: undefined,
        jobTitleId: undefined,
        description: undefined
      },
      dialogVisible: false,
      passwordForm: {
        password: undefined,
        newPassword: undefined,
        reNewPassword: undefined
      },
      // 表单校验
      rules: {
      },
      // 提交按钮加载状态
      submitLoading: true,
      deptList: [],
      titleList: []
    }
  },
  created() {
    this.getOne()
    this.getDeptList()
    this.getTitleList()
  },
  methods: {
    // 根据id获取信息(编辑会触发)
    getOne() {
      getOneByDoctorId(this.$store.state.user.uid).then(response => {
        this.form = response.data
        this.submitLoading = false
      })
    },
    handleUpdatePassword() {
      const { newPassword, reNewPassword } = this.passwordForm
      if (newPassword === reNewPassword) {
        updatePassword({ ...this.passwordForm, uid: this.$store.state.user.uid }).then(res => {
          this.$message({ type: 'success', message: res.msg })
        })
      } else {
        this.$message.error('两次输入密码不一致!')
      }
      this.dialogVisible = false
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
    handleClose() {
      this.dialogVisible = false
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
.item-dialog{ margin-top: 1vh; }

</style>
