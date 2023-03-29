<template>
  <div id="app">
    <h1>{{ msg }}</h1>
    <School/>
    <Student/>
  </div>
</template>
<script>
import School from "./components/School.vue";
import Student from "./components/Student.vue";
export default {
  name: 'App',
  data() {
    return {
      msg: '欢迎来到武汉大学学习'
    }
  },
  components:{
    School,
    Student
  }
}
</script>
