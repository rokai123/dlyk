
// https://vite.dev/config/
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vite.dev/config/
export default defineConfig({
  // 插件配置数组
  // vue() 插件用于支持 Vue 3 单文件组件 (SFC) 的编译和热模块替换 (HMR)
  plugins: [vue()],
  
  // 开发服务器配置
  server: {
    // 指定开发服务器监听的端口号
    // 如果该端口被占用，Vite 会自动尝试下一个可用端口
    port: 8082,
    
    // 指定服务器监听的 IP 地址
    // '0.0.0.0' 表示监听所有网络接口，允许通过局域网 IP 访问（不仅限于 localhost）
    host: '0.0.0.0',
    
    // 是否在服务器启动时自动在浏览器中打开页面
    // true 为自动打开，false 为不打开
    open: true,
  },
})
