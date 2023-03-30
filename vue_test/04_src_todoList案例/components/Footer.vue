<template>
  <el-footer v-show="total">
    <el-checkbox class="checkbox" :checked="isAll" @change="checkAll">以完成{{doneTotal}}/全部{{total}}</el-checkbox>
  </el-footer>
</template>

<script>
export default {
  name: 'Footer',
  props:['todos', 'checkAllTodo'],
  computed: {
    total() {
      return this.todos.length
    },
    doneTotal() {
      return this.todos.reduce((pre, todo) => pre + (todo.done ? 1 : 0), 0)
    },
    isAll() {
      return this.total === this.doneTotal && this.total > 0
    }
  },methods: {
    checkAll(e) {
      checkAllTodo(e.target.checked)
    }
  },
}
</script>

<style>

</style>