<template>
  <div>
    <h2 @click="showName">学校名称:{{ name }}</h2>
    <h2>学校地址:{{ addr }}</h2>
  </div>
</template>

<script>
  // 引入 mixin
  // import { mixin } from './mixin'
  export default {
      name: 'School',
      data() {
        return {
          name: '武汉大学',
          addr: '珞珈山'
        }
      },
      // mixins: [mixin]
  }
</script>

<style>

</style>