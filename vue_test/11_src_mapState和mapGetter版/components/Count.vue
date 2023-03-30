<template>
  <div>
    <h1>当前求和为：{{ res }}</h1>
    <h3>当前求和放大十倍为：{{ bigSum }}</h3>
    <h4>我在{{school}}学习：{{project}}</h4>
    <select v-model.number ="input">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
    <button @click="incrementWhenOdd">当前求和为奇数再加</button>
    <button @click="incrementWait">等0.5s再加</button>
  </div>
</template>

<script>
import {mapState, mapGetters} from "vuex";
export default {
  name: 'Count',
  data() {
    return {
      input: 1, // 用户选择的数字
    }
  },
  methods: {
    increment() {
      this.$store.commit('INCREMENT', this.input)
    },
    decrement() {
      this.$store.commit('DECREMENT', this.input)
    },
    incrementWhenOdd() {
      this.$store.dispatch('incrementWhenOdd', this.input)
    },
    incrementWait() {
      this.$store.dispatch('incrementWait', this.input)
    },
  },
  computed: {
    // 靠程序员自己亲手写计算属性
    /* res() {
      return this.$store.state.res
    },
    project() {
      return this.$store.state.project
    },
    school() {
      return this.$store.state.school
    }, */
    // 借助 mapState 生成计算属性，从 state 中读取数据（对象写法）
    // ...mapState({res:'res', project: 'project', school: 'school'}),
    // 借助 mapState 生成计算属性，从 state 中读取数据（数组写法）
    ...mapState(['res', 'project', 'school']),

    /* bigSum() {
      return this.$store.getters.bigSum
    }, */
    // 借助 mapGetters 生成计算属性，从 getter 中读取数据（数组写法）
    // ...mapGetters(['bigSum'])
    // 借助 mapGetters 生成计算属性，从 getter 中读取数据（数组写法）
    ...mapGetters({bigSum: 'bigSum'})
  },
  mounted() {
    const x = mapState({res:'res', project: 'project', school: 'school'})
    console.log(x);
  },
}
</script>

<style scoped>
  button {
    margin-left: 5px;
  }
</style>