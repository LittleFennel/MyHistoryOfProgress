<template>
  <section class="jumbotron">
    <h3 class="jumbotron-heading">Search Github Users</h3>
    <div>
      <input type="text" placeholder="enter the name you search" v-model="keyWord"/>&nbsp;
      <button @click="searchUsers">Search</button>
    </div>
  </section>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Search',
  data() {
    return {
      keyWord: ''
    }
  },
  methods: {
    searchUsers() {
      // 请求前更新 List 数据
      this.$bus.$emit('updateListData', {isFirst: false, isLoading: true, errorMsg: '', users: []})
      axios.get(`https://api.github.com/search/users?q=${this.keyWord}`).then(
        response => {
          console.log('请求成功了');
          this.$bus.$emit('updateListData', {isLoading: false, errorMsg: '', users: response.data.items})
        },
        // 请求失败后更新 List 数据
        error => {
          console.log('请求失败了', error.message);
          this.$bus.$emit('updateListData', {isLoading: false, errorMsg: response.message, users: []})
        }
      )
    }
  },
}
</script>

<style>

</style>