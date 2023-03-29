<template>
  <div class="school">
    <h2>学校名称：{{ name }}</h2>
    <h2>学校地址：{{ addr }}</h2>
  </div>
</template>

<script>
export default {
  name: 'School',
  data() {
    return {
      name: '武汉大学',
      addr: '珞珈山'
    }
  },
  mounted() {
    this.$bus.$on('sendStudentName', (data) => {
      console.log('School组件收到了数据', data);
    })
  },
  // 在组件销毁前解绑 sendStudentName 事件
  beforeDestroy() {
    this.$bus.$off('sendStudentName')
  }
}
</script>

<style scoped>
  .school{
    background-color: skyblue;
    padding: 5px;
  }
</style>