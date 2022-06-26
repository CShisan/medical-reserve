import { getInfo } from '@/api/user'
import { login, logout } from '@/api/system'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'

const state = {
  token: getToken(),
  uid: '',
  username: '',
  phone: '',
  avatar: '',
  fullRoles: [],
  roles: []
}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_UID: (state, uid) => {
    state.uid = uid
  },
  SET_USERNAME: (state, username) => {
    state.username = username
  },
  SET_PHONE: (state, phone) => {
    state.phone = phone
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  SET_FULL_ROLES: (state, fullRoles) => {
    state.fullRoles = fullRoles
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  }
}

const actions = {
  // 登录
  login({ commit, dispatch }, userInfo) {
    const { phone, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ phone: phone.trim(), password: password }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.token)
        setToken(data.token)
        dispatch('setInfo', data.user)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取用户信息
  getInfo({ dispatch }) {
    return new Promise((resolve, reject) => {
      getInfo().then(response => {
        const { data } = response

        if (!data) {
          reject('登录失败,请重新登录!')
        }

        const { roles } = data

        if (!roles || roles.length <= 0) {
          reject('尚未分配角色!')
        }

        dispatch('setInfo', data)

        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 设置用户信息
  async setInfo({ commit, dispatch }, info) {
    const { uid, username, phone, roles, avatarUrl } = info
    // 后端传入fullRoles 需要抽取keys出来
    const subRoles = roles.map(role => { return role.roleKey })
    commit('SET_UID', uid)
    commit('SET_USERNAME', username)
    commit('SET_PHONE', phone)
    commit('SET_AVATAR', avatarUrl)
    commit('SET_FULL_ROLES', roles)
    commit('SET_ROLES', subRoles)

    // generate accessible routes map based on roles
    const accessRoutes = await dispatch('permission/generateRoutes', subRoles, { root: true })
    // dynamically add accessible routes
    router.addRoutes(accessRoutes)
  },

  // user logout
  logout({ commit, state, dispatch }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(async() => {
        commit('SET_TOKEN', '')
        commit('SET_FULL_ROLES', [])
        commit('SET_ROLES', [])
        removeToken()
        resetRouter()

        // reset visited views and cached views
        // to fixed https://github.com/PanJiaChen/vue-element-admin/issues/2485
        dispatch('tagsView/delAllViews', null, { root: true })

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      commit('SET_TOKEN', '')
      commit('SET_ROLES', [])
      removeToken()
      resolve()
    })
  },

  // dynamically modify permissions
  async changeRoles({ commit, dispatch }, roles) {
    resetRouter()

    // generate accessible routes map based on roles
    const accessRoutes = await dispatch('permission/generateRoutes', roles, { root: true })
    // dynamically add accessible routes
    router.addRoutes(accessRoutes)

    // reset visited views and cached views
    dispatch('tagsView/delAllViews', null, { root: true })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
