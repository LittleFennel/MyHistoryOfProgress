<template>
  <div class = "demo">
    <h2>学生名称{{ studentName }}</h2>
    <h2>学生年龄{{ studentAge }}</h2>
    <button @click="showX">点我提示学生名称</button>
  </div>
</template>

<script>
export default ({
  name: 'Student',
  data() {
    return {
      studentName: '熊昆',
      studentAge: '24'
    }
  },
  methods: {
    showX() {
      alert(this.studentName)
    }
  },
})
</script>