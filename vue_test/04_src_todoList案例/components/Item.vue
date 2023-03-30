<template>
  <div class="divStyle">
    <el-checkbox class="checkbox" :checked="todoObj.done" @change="handelCheck(todoObj.id)">{{todoObj.title}}</el-checkbox>
    <el-button type="danger" size="mini" style="float: right;" @click="handleDelete(todoObj.id)">删除</el-button>
  </div>
</template>

<script>
export default {
  name: 'Item',
  props:['todoObj', 'checkTodo', 'deleteTodo'],
  methods: {
    handelCheck(id) {
      // 通知
      this.checkTodo(id)
    },
    handleDelete(id) {
      if(confirm('确定删除吗?')) {
        this.deleteTodo(id)
      }
    }
  }
}
</script>

<style scoped>
  .checkbox {
    margin-left: 50px;
  }
  .divStyle:hover {
    background-color: #67C23A;
  }
</style>