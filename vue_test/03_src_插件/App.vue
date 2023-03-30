<template>
  <div id="app">
    <School/>
    <hr>
    <Student/>
  </div>
</template>

<script>
import Student from "./components/Student.vue";
import School from "./components/School.vue";

export default {
  name: 'App',
  data() {
    return {
      msg: '欢迎来到武汉大学学习！'
    }
  },
  components:{Student, School}
}
</script>
