<template>
  <div>
    <h1 style="color: red;">Count组件的求和结果为：{{res}}</h1>
    <h1>人员列表</h1>
    <h3>列表中第一个人的名字是：{{firstPersonName}}</h3>
    <input v-model="name" type="text" placeholder="请输入名字">
    <button @click="addPerson">添加</button>
    <button @click="addWang">添加一个姓王的人</button>
    <ul>
      <li v-for="person in personList" :key="person.id">{{ person.name }}</li>
    </ul>
  </div>
</template>

<script>
import {nanoid} from 'nanoid'
export default {
  name: 'Person',
  data() {
    return {
      name: ''
    }
  },
  computed: {
    personList() {
      return this.$store.state.personOptions.personList
    },
    res() {
      return this.$store.state.countOptions.res
    },
    firstPersonName() {
      return this.$store.getters['personOptions/firstPersonName']
    }
  },
  methods: {
    addPerson() {
      const personObj = {id:nanoid(), name: this.name}
      this.$store.commit('personOptions/ADDPERSON', personObj)
      this.name = ''
    },
    addWang() {
      const personObj = {id:nanoid(), name: this.name}
      this.$store.dispatch('personOptions/addPersonWang', personObj)
      this.name = ''
    }
  }
}
</script>

<style>

</style>