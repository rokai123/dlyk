<template>
  <div class="common-layout">
    <el-container>
      <el-aside :width="showMenu ? '80px' : '250px'">
        <p class="title">导航</p>
        <el-menu
          active-text-color="#ffd04b"
          background-color="#545c64"
          class="el-menu-vertical-demo"
          default-active="2"
          text-color="#fff"
          :collapse="showMenu"
          :collapse-transition="false"
          :unique-opened="true"
          @open="handleOpen"
          @close="handleClose"
        >
          <el-sub-menu index="1">
            <template #title>
              <el-icon><Shop /></el-icon>
              <span class="menu-title">市场活动</span>
            </template>
            <el-menu-item index="1-1">市场活动</el-menu-item>
            <el-menu-item index="1-2">市场调查</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="2">
            <template #title>
              <el-icon><UserFilled /></el-icon>
              <span class="menu-title">线索管理</span>
            </template>
            <el-menu-item index="2-1">线索管理</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="3">
            <template #title>
              <el-icon><Avatar /></el-icon>
              <span class="menu-title">客户管理</span>
            </template>
            <el-menu-item index="3-1">客户管理</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="4">
            <template #title>
              <el-icon><ShoppingCart /></el-icon>
              <span class="menu-title">交易管理</span>
            </template>
            <el-menu-item index="4-1">交易管理</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="5">
            <template #title>
              <el-icon><Goods /></el-icon>
              <span class="menu-title">产品管理</span>
            </template>
            <el-menu-item index="5-1">产品管理</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="6">
            <template #title>
              <el-icon><Document /></el-icon>
              <span class="menu-title">字典管理</span>
            </template>
            <el-menu-item index="6-1">字典数据</el-menu-item>
            <el-menu-item index="6-2">字典类型</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="7">
            <template #title>
              <el-icon><UserFilled /></el-icon>
              <span class="menu-title">用户管理</span>
            </template>
            <el-menu-item index="7-1">用户管理</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="8">
            <template #title>
              <el-icon><Setting /></el-icon>
              <span class="menu-title">系统管理</span>
            </template>
            <el-menu-item index="8-1">系统管理</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-container class="right-container">
        <el-header>
          <el-icon @click="isShowMenu"><Fold /></el-icon>

          <el-dropdown :hide-on-click="false">
            <span class="el-dropdown-link">
              用户名<el-icon class="el-icon--right"
                ><arrow-down
              /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>我的资料</el-dropdown-item>
                <el-dropdown-item>修改密码</el-dropdown-item>
                <el-dropdown-item divided>退出登录</el-dropdown-item>

              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-header>
        <el-main>Main</el-main>
        <el-footer>Footer</el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { defineComponent } from "vue";
export default defineComponent({
  name: "DashboardView",
  data() {
    return {
      showMenu: false,
    };
  },
  methods: {
    isShowMenu() {
      this.showMenu = !this.showMenu;
    },
  },
});
</script>

<style scoped>
.common-layout {
  min-height: 100vh;
  background: #f2f6fb;
}
.el-aside {
  background: linear-gradient(180deg, #17212d 0%, #0f1724 100%);
  text-align: left;
  padding: 20px 0;
}
.el-menu {
  border-radius: 16px;
  background: rgba(255, 255, 255, 0.04);
  padding: 8px 0;
}
.el-menu-item,
.el-sub-menu__title {
  color: rgba(255, 255, 255, 0.88);
  font-size: 14px;
}
.el-sub-menu__title {
  padding: 0 20px;
}
.el-menu-item {
  padding: 10px 20px;
  transition:
    background-color 0.2s ease,
    color 0.2s ease,
    padding-left 0.2s ease;
}
.el-menu-item:hover {
  background: rgba(255, 255, 255, 0.08);
  color: #ffd04b;
  padding-left: 24px;
}
.el-menu-item.is-active {
  background: rgba(255, 208, 75, 0.18);
  color: #ffd04b;
  font-weight: 700;
  border-left: 4px solid #ffd04b;
}
.title {
  color: #ffd04b;
  font-size: 18px;
  font-weight: 700;
  margin: 20px 16px 14px;
  letter-spacing: 1px;
}
.menu-title {
  font-weight: 700;
  font-size: 15px;
  color: #ffffff;
}
.right-container {
  min-height: 100vh;
  background: #edf2f8;
  display: flex;
  flex-direction: column;
}
.el-header,
.el-footer {
  background: #ffffff;
  color: #2c3e50;
  border: none;
  box-shadow: inset 0 -1px 0 rgba(0, 0, 0, 0.05);
  padding: 18px 24px;
  font-weight: 600;
}
.el-main {
  flex: 1;
  background: #ffffff;
  margin: 16px 24px;
  border-radius: 20px;
  box-shadow: 0 18px 40px rgba(24, 39, 75, 0.08);
  padding: 24px;
}
.el-dropdown{
  float: right;
}
</style>
