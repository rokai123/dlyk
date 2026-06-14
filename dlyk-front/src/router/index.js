import { createRouter, createWebHistory } from 'vue-router'
//import LoginView from '../view/LoginView.vue'

const routes = [
  // 登录页面路由配置
/*   {
    path: '/login',        // 路由路径：当URL为 /login 时匹配此路由
    name: 'Login',         // 路由名称：用于编程式导航（如 router.push({ name: 'Login' })）和路由标识
    component: LoginView,  // 路由组件：匹配该路径时渲染的Vue组件（LoginView.vue）
  }, */
  //优化写法，懒加载
  /* 懒加载的优点：

    代码分割，减小初始包体积
    只在访问路由时才加载对应组件
    提升首屏加载速度 */
    {
        path: '/login',
        name: 'Login',
        // 使用箭头函数 + 动态导入实现懒加载
        component: () => import('../view/LoginView.vue'),
    },

    // 根路径重定向配置
    {
        path: '/',             // 根路径：访问网站域名或根目录时匹配（如 http://localhost:5173/）
        redirect: '/login',    // 重定向目标：将根路径自动跳转到 /login，确保用户首次访问时直接进入登录页面
    },
    // 其他路由页面...
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: () => import('../view/DashboardView.vue'),
    },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router