<template>
  <el-header>
    <el-input v-model="input" placeholder="请输入任务，按回车键确认" size="big" @keyup.enter.native="add"></el-input>
  </el-header>
</template>

<script>
import { nanoid } from "nanoid";
export default {
  name: 'Top',
  data() {
    return {
      input:''
    }
  },
  props:['addTodo'],
  methods: {
    add() {
      if(!this.input.trim()) return alert("输入不能为空")
      const todoObj = {
        id: nanoid(),
        title: this.input,
        done: false
      }
      this.addTodo(todoObj)
      this.input = ''
    }
  },
}
</script>