<template>
  <div id="app">
    <button @click="getStudents">获取学生信息</button>
  </div>
</template>
<script>
import axios from 'axios'
export default {
  name: 'App',
  methods: {
    getStudents() {
      axios.get('')
    }
  },
}
</script>
