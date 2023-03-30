// 该文件用于创建Vuex 中的 store
// 引入 Vuex
import Vuex from "vuex";
import Vue from 'vue'
// 使用 Vuex 插件
Vue.use(Vuex)

// 求和功能相关的配置
const countOptions = {
  namespaced:true,
  actions: {
    increment(context, data) {
      context.commit('INCREMENT', data)
    },
    incrementWait(context, data) {
      setTimeout(() => {
        context.commit('INCREMENTWAIT', data)
      }, 500)
    },
    decrement(context, data) {
      context.commit('DECREMENT', data)
    },
    incrementWhenOdd(context, data) {
      if(context.state.res % 2)
      context.commit('INCREMENTWHENODD', data)
    },
  },
  mutations: {
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
  },
  state: {
    res: 0,
    school: '武汉大学',
    project: '计算机科学',
  },
  getters: {
    bigSum(state) {
      return state.res * 10
    }
  }
}

// 人员管理相关的配置
const personOptions = {
  namespaced:true,
  actions: {
    addPersonWang(context, value) {
      if(value.name.indexof('王') === 0) {
        context.commit('ADDPERSON', value)
      } else {
        alert('添加的人必须姓王！')
      }
    }
  },
  mutations: {
    ADDPERSON(state, personObj) {
      state.personList.unshift(personObj)
    }
  },
  state: {
    personList: [
      {id:'001', name: '张三'}
    ]
  },
  getters: {
    firstPersonName(state) {
      return state.personList[0].name
    }
  }
}

// 创建并暴露 store
export default new Vuex.Store({
  modules: {
    countOptions,
    personOptions
  }
})