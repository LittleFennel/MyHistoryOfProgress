<template>
  <div class="student">
    <h2>学生姓名：{{ name }}</h2>
    <h2>学生性别：{{ gender }}</h2>
    <button @click="sendStudentName">把学生名传给School组件</button>
  </div>
</template>

<script>
export default {
  name: 'Student',
  data() {
    return {
      name: '熊昆',
      gender: '男'
    }
  },
  methods: {
    sendStudentName() {
      this.$bus.$emit('sendStudentName', this.name)
    }
  }
}
</script>

<style scoped>
  .student{
    background-color: pink;
    padding: 5px;
    margin-top: 30px;
  }
</style>