<template>
  <div>
    <h1>当前求和为：{{res}}</h1>
    <h3>当前求和放大十倍为：{{ bigSum}}</h3>
    <h4>我在{{school}}学习：{{project}}</h4>
    <select v-model.number ="input">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
    </select>
    <button @click="increment(input)">+</button>
    <button @click="decrement(input)">-</button>
    <button @click="incrementWhenOdd(input)">当前求和为奇数再加</button>
    <button @click="incrementWait(input)">等0.5s再加</button>
  </div>
</template>

<script>
import {mapState, mapGetters, mapActions, mapMutations} from "vuex";
export default {
  name: 'Count',
  data() {
    return {
      input: 1, // 用户选择的数字
    }
  },
  methods: {
    /* increment() {
      this.$store.commit('INCREMENT', this.input)
    },
    decrement() {
      this.$store.commit('DECREMENT', this.input)
    }, */
    // 借助 mapMutations 生成对应方法，方法中就会调用 commit 去联系 mutations(对象写法)
    ...mapMutations({increment: 'INCREMENT', decrement: 'DECREMENT'}),
    // 借助 mapMutations 生成对应方法，方法中就会调用 commit 去联系 mutations(数组写法)
    // ...mapMutations(['INCREMENT', 'DECREMENT']),
    /* ************************************************** */
    /* incrementWhenOdd() {
      this.$store.dispatch('incrementWhenOdd', this.input)
    },
    incrementWait() {
      this.$store.dispatch('incrementWait', this.input)
    }, */
    // 借助 mapActions 生成对应方法，方法中就会调用 dispatch 去联系 actions(对象写法)
    ...mapActions({incrementWhenOdd: 'incrementWhenOdd', incrementWait: 'incrementWait'})
    // 借助 mapActions 生成对应方法，方法中就会调用 dispatch 去联系 actions(对象写法)
    // ...mapActions(['incrementWhenOdd', 'incrementWait'])
  },
  computed: {
    // 借助 mapState 生成计算属性，从 state 中读取数据（对象写法）
    // ...mapState({res:'res', project: 'project', school: 'school'}),
    // 借助 mapState 生成计算属性，从 state 中读取数据（数组写法）
    ...mapState(['res', 'project', 'school']),

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