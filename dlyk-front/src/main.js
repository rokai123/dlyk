import { createApp } from 'vue'
//import './style.css'
//import App from './App.vue'
import LoginView from './view/LoginView.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
createApp(LoginView)
    .use(ElementPlus)
    .mount('#app')
