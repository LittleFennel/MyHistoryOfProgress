<template>
  <div id="app">
    <Student name="张三" :age="24" gender="男"/>
  </div>
</template>

<script>
import Student from "./components/Student.vue";

export default {
  name: 'App',
  data() {
    return {
      msg: '欢迎来到武汉大学学习！'
    }
  },
  components:{Student}
}
</script>
