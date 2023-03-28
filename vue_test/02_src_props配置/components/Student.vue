<template>
  <div>
    <h1>{{ msg }}</h1>
    <h2>名称:{{ name }}</h2>
    <h2>性别:{{ gender }}</h2>
    <h2>年龄:{{ myAge + 1 }}</h2>
    <el-button type="primary" @click="changeAge">尝试修改收到的年龄</el-button>
  </div>
</template>

<script>
export default {
    name: 'Student',
    data() {
      return {
        msg: '我是武汉大学的学生',
        myAge: this.age,
      }
    },
    methods: {
      changeAge() {
        this.myAge++
      }
    },
    // props: ['name', 'gender', 'age'] 简单声明接收
    /* 接收的同时对数据进行类型限制 
    props: {
      name: String,
      age: Number,
      gender: String
    } */
    // 接收的同时对数据进行类型限制、默认值的指定和必要性的限制
    props: {
      name: {
        type: String, // name 类型
        required: true // name 必要的
      },
      age: {
        type: Number, // age 类型
        default: 18  // 不传默认 18
      },
      gender: {
        type: String, // name 类型
        required: true // name 必要的
      }
    },
}
</script>

<style>

</style>