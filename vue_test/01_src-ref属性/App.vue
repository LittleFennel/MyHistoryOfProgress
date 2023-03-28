<template>
  <div id="app">
    <h1 v-text="msg" ref="title"></h1>
    <el-button type="primary" @click="showDOM" ref="btn">点我输出上方的DOM元素</el-button>
    <hr>
    <School/>
  </div>
</template>

<script>
import School from "./components/School.vue";

export default {
  name: 'App',
  data() {
    return {
      msg: '欢迎来到武汉大学学习！'
    }
  },
  methods: {
    showDOM() {
      console.log(this.$refs.title) // 获取到 html 元素
      console.log(this.$refs.btn) // 获取到组件实例对象
    }
  },
  components:{School}
}
</script>
