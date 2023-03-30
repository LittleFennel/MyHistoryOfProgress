<template>
  <el-main>
    <Item v-for="(todoObj, id) in todos" :key="id" :todoObj="todoObj" :checkTodo="checkTodo" :deleteTodo="deleteTodo"/>
  </el-main>
</template>

<script>
import Item from "./Item.vue";
export default {
  name: 'List',
  components:{Item},
  props:['todos', 'checkTodo', 'deleteTodo']
}
</script>

<style>

</style>