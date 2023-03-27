<template>
  <div class = "demo">
    <h2>学校名称{{ schoolName }}</h2>
    <h2>学校地址{{ schoolAddr }}</h2>
    <button @click="showX">点我提示学校名</button>
  </div>
</template>

<script>
export default ({
  name: 'School',
  data() {
    return {
      schoolName: '武汉大学',
      schoolAddr: '珞珈山'
    }
  },
  methods: {
    showX() {
      alert(this.schoolName)
    }
  },
})
</script>

<style lang="style">
  .demo{
    background-color: orange;
  }
</style>