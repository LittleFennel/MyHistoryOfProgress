<template>
  <div>
    <h1>当前求和为：{{ res }}</h1>
    <select v-model.number ="input">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="2">2</option>
    </select>
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
    <button @click="incrementWhenOdd">当前求和为奇数再加</button>
    <button @click="incrementWait">等0.5s再加</button>
  </div>
</template>

<script>
export default {
  name: 'Count',
  data() {
    return {
      res: 0,   // 当前的和
      input: 1, // 用户选择的数字
    }
  },
  methods: {
    increment() {
      this.res += this.input
    },
    decrement() {
      this.res -= this.input
    },
    incrementWhenOdd() {
      if(this.res % 2) this.res += this.input
    },
    incrementWait() {
      setTimeout(() => {
        this.res += this.input
      }, 500)
    },
  },
}
</script>

<style scoped>
  button {
    margin-left: 5px;
  }
</style>