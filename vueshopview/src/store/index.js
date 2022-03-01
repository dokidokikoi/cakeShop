import Vue from 'vue'
import Vuex from 'vuex'

import {getToken, setToken, removeToken} from '@/request/token'
import {login, logout} from '@/api/login'
import {getUserInfo} from '@/api/user'
import { getCarCounts } from '@/api/car'
import { getRole } from '@/api/role'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {},
    car: [],
    token: '',
    role: ''
  },
  mutations: {
    SET_USER: (state, user) => {
      state.user = user
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_CAR: (state, car) => {
      state.car = car
    },
    SET_ROLE: (state, role) => {
      state.role = role
    }
  },
  actions: {
    login({commit}, user) {
      return new Promise((reslove, reject) => {
          login(user).then(res => {
              commit('SET_TOKEN', res.headers['Authorization'])
              setToken(res.headers['authorization'])
              reslove()
          }).catch(err => {
              reject(err)
          })
      })
    },
    logout({commit}) {
        return new Promise((reslove, reject) => {
            logout().then(res => {
                commit('SET_TOKEN', "")
                removeToken()
                reslove()
            }).catch(err => {
                reject(err)
            })
        })
    },
    getUserInfo({commit}) {
      return new Promise((reslove, reject) => {
          getUserInfo().then(res => {
              commit('SET_USER', res.data.data)
              reslove(res.data.data)
              getRole().then(res => {
                commit('SET_ROLE', res.data.data.name)
              })
          }).catch(err => {
              reject(err)
          })
      })
    },
    getCarCount({commit}, id) {
      return new Promise((reslove, reject) => {
        getCarCounts(id).then(res => {
          console.log(res.data.data)
          reslove(res.data.data)
        }).catch(err => {
          reject(err)
        })
      })
      
    }
  },
  modules: {
  }
})
