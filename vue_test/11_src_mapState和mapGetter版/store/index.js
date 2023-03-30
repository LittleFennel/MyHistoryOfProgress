// 该文件用于创建Vuex 中的 store
// 引入 Vuex
import Vuex from "vuex";
import Vue from 'vue'
// 使用 Vuex 插件
Vue.use(Vuex)
// 准备 actions，用于响应组件中的动作（state）
const actions = {
  increment(context, data) {
    context.commit('INCREMENT', data)
  },
  decrement(context, data) {
    context.commit('DECREMENT', data)
  },
  incrementWhenOdd(context, data) {
    if(context.state.res % 2)
    context.commit('INCREMENTWHENODD', data)
  },
  incrementWait(context, data) {
    setTimeout(() => {
      context.commit('INCREMENTWAIT', data)
    }, 500)
  }
}

// 准备 mutations，用于操作数据（state）
const mutations = {
  INCREMENT(state, data) {
    state.res += data
  },
  DECREMENT(state, data) {
    state.res -= data
  },
  INCREMENTWHENODD(state, data) {
    state.res += data
  },
  INCREMENTWAIT(state, data) {
    state.res += data
  }
}

// 准备 state 存储数据
const state = {
  res: 0,
  school: '武汉大学',
  project: '计算机科学'
}

// 准备 getters 将 state 中的数据进行加工
const getters = {
  bigSum(state) {
    return state.res * 10
  }
}

// 创建并暴露 store
export default new Vuex.Store({
  actions,
  mutations,
  state,
  getters
})