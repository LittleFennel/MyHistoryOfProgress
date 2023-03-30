<template>
  <div id="app">
    <el-container direction="vertical">
      <Top :addTodo="addTodo"/>
      <List :todos="todos" :checkTodo="checkTodo" :deleteTodo="deleteTodo"/>
      <Footer :todos="todos" :checkAllTodo="checkAllTodo"/>
    </el-container>
  </div>
</template>
<script>
import Top from "./components/Top.vue";
import List from "./components/List.vue";
import Footer from "./components/Footer.vue";
export default {
  name: 'App',
  components:{
    Top,
    List,
    Footer
  },
  data() {
    return {
      todos: [
        {id:'001', title: '吃饭', done: true},
        {id:'002', title: '睡觉', done: false},
        {id:'003', title: '打游戏', done: false}
      ]
    }
  },
  methods: {
    // 添加一个 todo
    addTodo(todoObj) {
      this.todos.unshift(todoObj)
    },
    // 勾选和取消勾选一个 todo
    checkTodo(id) {
      this.todos.forEach((todo) => {
        if(todo.id === id) todo.done = !todo.done
      })
    },
    // 删除一个 todoObj
    deleteTodo(id) {
      this.todos = this.todos.filter(todo => {return todo.id !== id })
    },
    // 取消全选
    checkAllTodo(done) {
      this.todos.forEach((todo) => {
        todo.done = done
      })
    }
  },
}
</script>
