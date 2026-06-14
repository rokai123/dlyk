<script setup>
import { useRouter } from 'vue-router'
const router = useRouter()
</script>

<template>
  <div id="app">
    <!-- 路由出口：显示当前路由对应的组件 -->
    <router-view />
  </div>
</template>

<style scoped>
#app {
  width: 100%;
  height: 100%;
}
</style>
