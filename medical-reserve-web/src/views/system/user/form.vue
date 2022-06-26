<template>
  <el-card class="user-form-container">
    <div slot="header" class="clearfix">
      <span>{{ title }}</span>
    </div>
    <el-form
      ref="dataForm"
      :rules="rules"
      :model="form"
      label-width="150px"
      style="width: 750px"
    >
      <el-row v-if="formType" :gutter="10">
        <el-col v-if="formType" :span="12">
          <el-form-item label="UID" prop="uid">
            <el-input
              v-model="form.uid"
              disabled
              placeholder="请输入UID"
            />
          </el-form-item>
        </el-col>
        <el-col v-if="formType" :span="12">
          <el-form-item label="头像" prop="avatarUrl">
            <el-input v-model="form.avatarUrl" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="form.phone" placeholder="请输入手机号" />
          </el-form-item>
        </el-col>
        <el-col v-if="!formType" :span="12">
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" placeholder="请输入密码" />
          </el-form-item>
        </el-col>
        <el-col v-if="formType" :span="12">
          <el-form-item label="出生日期" prop="birthday">
            <el-input v-model="form.birthday" disabled placeholder="请输入出生日期" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="真实姓名" prop="realName">
            <el-input v-model="form.realName" placeholder="请输入真实姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="身份证号码" prop="idCard">
            <el-input v-model="form.idCard" placeholder="请输入身份证号码" />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="12">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username" placeholder="请输入用户名" />
          </el-form-item>
        </el-col>
        <el-col v-if="formType" :span="12">
          <el-form-item label="性别" prop="sex">
            <el-radio-group v-model="form.sex" disabled>
              <el-radio :label="1">男</el-radio>
              <el-radio :label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10">
        <el-col :span="24">
          <el-form-item label="角色标签" prop="roles">
            <el-tag
              v-for="item in form.roles"
              :key="item.roleId"
              closable
              :disable-transitions="false"
              @close="handleRoleDel(item)"
            >
              {{ item.roleName }}
            </el-tag>
            <el-select
              v-if="selectorVisible"
              ref="selector"
              v-model="selected"
              placeholder="请选择"
              size="mini"
              class="selector-new-tag"
              @change="handleSelected"
            >
              <el-option
                v-for="item in roleList"
                :key="item.roleId"
                :label="item.roleName"
                :value="item"
              />
            </el-select>
            <el-button v-else class="button-new-tag" size="small" @click="showSelector">+ 添加角色</el-button>
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
import { getOne, update } from '@/api/user'
import { getAllRoles, getRolesByUid } from '@/api/role'
import { validFormData } from '@/utils/form-vaidate'

export default {
  name: 'SystemUserForm',
  data() {
    return {
      // 表单标题
      title: '编辑用户信息',
      // 表单属性
      form: {
        uid: undefined,
        phone: undefined,
        password: '123456',
        realName: undefined,
        idCard: undefined,
        username: undefined,
        birthday: undefined,
        sex: 0,
        avatarUrl: undefined,
        roles: []
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
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
        ],
        roles: [{ type: 'array', required: true, message: '请至少选择一个角色', trigger: 'change' }]
      },
      selectorVisible: false,
      selected: '',
      roleList: [],
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
    this.getAllRoles()
    if (this.formType) {
      this.getOne()
    }
  },
  methods: {
    // 根据id获取信息(修改会触发)
    getOne() {
      getOne(this.$route.params.id).then(response => {
        this.form = response.data
        getRolesByUid(this.form.uid).then(response => {
          this.form.roles = response.data
        })
        this.submitLoading = false
      })
    },
    // 获取角色列表
    getAllRoles() {
      getAllRoles().then(response => {
        this.roleList = response.data
      })
    },
    // 移除拥有角色
    handleRoleDel(item) {
      this.form.roles.splice(this.form.roles.indexOf(item), 1)
    },
    // 显示角色列表
    showSelector() {
      this.selectorVisible = true
      this.$nextTick(_ => {
        this.$refs.selector.focus()
      })
    },
    // 角色选择
    handleSelected() {
      const exit = this.form.roles.filter(item => {
        return item.roleId === this.selected.roleId
      })
      if (exit.length === 0) {
        this.form.roles.push(this.selected)
      }
      this.selectorVisible = false
      this.selected = ''
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
            this.form.createTime = undefined
            this.form.updateTime = undefined
            this.form.roles.map((item, index, roles) => {
              roles[index].createTime = undefined
              roles[index].updateTime = undefined
            })
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
      this.$router.push({ name: 'SystemUser' })
    }
  }
}
</script>

<style lang="scss">
.user-form-container{
  .selector-new-tag {
    .el-input--mini{
      height: 30px;
    }
    .el-input__suffix{
      height: 30px;
    }
  }

}
</style>

<style lang="scss" scoped>
.user-form-container{
    margin: 20px 50px;
    .form-item-width {
        width: 100%;
    }
  .el-tag + .el-tag {
    margin-left: 10px;
  }
  .button-new-tag {
    margin-left: 10px;
    height: 28px;
    line-height: 26px;
    padding-top: 0;
    padding-bottom: 0;
  }
  .selector-new-tag {
    width: 90px;
    margin-left: 10px;
  }
}
</style>
