<template>
  <div>
    <h2 @click="showName">名称:{{ name }}</h2>
    <h2>性别:{{ gender }}</h2>
  </div>
</template>

<script>
  // import { mixin } from './mixin'
  export default {
      name: 'Student',
      data() {
        return {
          name: '熊昆',
          gender: '男'
        }
      },
      // mixins: [mixin]
  }
</script>

<style>

</style>