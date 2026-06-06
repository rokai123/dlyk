<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="40%">
        <img class="logo" src="../assets/loginBox.svg">
        <p class="Thetitle">欢迎使用客户管理系统</p>
      </el-aside>
      <el-main>
        <el-form
        :label-position="labelPosition"
        label-width="auto"
        :model="loginForm"
        class="login-form"
        :rules="LoginFormRules"
        ref="formRef"
        >
          <p class="login-title">欢迎登录</p>
          <el-form-item label="账号" :label-position="itemLabelPosition" prop="loginAct">
              <el-input v-model="loginForm.loginAct" placeholder="请输入登录账号"/>
          </el-form-item>
          <el-form-item label="密码" :label-position="itemLabelPosition" prop="loginPwd">
              <el-input v-model="loginForm.loginPwd" type="password" placeholder="请输入登录密码"/>
          </el-form-item>
          <el-form-item label="  ">
              <el-button class="login-button" type="primary" @click="submitLogin">登录</el-button>
          </el-form-item>
          <el-form-item label="">
              <el-checkbox v-model="loginForm.rememberPwd">记住我</el-checkbox>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { doPost } from '../http/HttpRequest'
const labelPosition = ref('right')
const itemLabelPosition = ref('')
// 定义表单数据模型（响应式对象）
const loginForm = reactive({
  loginAct: '',
  loginPwd: '',
  rememberPwd:'',
})
// 获取表单实例的引用，用于调用验证方法
const formRef = ref(null)
// 定义表单验证规则对象，使用 reactive 使其具有响应性
const LoginFormRules = reactive({
  // 针对 loginAct (账号) 字段的验证规则数组
  loginAct: [
    { 
      required: true,   // 必填项校验
      message: '请输入用户名', // 校验失败时的提示信息
      trigger: 'blur'   // 触发校验的时机：失去焦点时
    },
    { 
      min: 3,   // 最小长度校验
      max: 32,  // 最大长度校验
      message: '用户名长度在 3 到 32 个字符', // 校验失败时的
      trigger: 'blur'   // 触发校验的时机：失去焦点时
    }
  ],
  // 针对 LoginPwd (密码) 字段的验证规则数组
  loginPwd: [
    // 当前为空数组，表示暂无校验规则（建议后续添加必填和长度校验）LoginPwd: [
    { required: true, message: '密码不能为空', trigger: 'blur' },
    { min: 6, max: 32, message: '密码长度在 6 到 32 个字符', trigger: 'blur' }
  ]
})

// 登录方法
const submitLogin = () => {
  // 通过 formRef.value 获取表单实例并调用 validate 方法进行验证
  formRef.value.validate(async (valid) => {
    if (valid) {
      // 表单验证通过，执行登录逻辑
      try {
        // 构建表单数据
        let formData = new FormData();
        formData.append("loginAct", loginForm.loginAct);
        formData.append("loginPwd", loginForm.loginPwd);
        
        // 使用 await 等待异步请求完成，并将响应结果赋值给 result 变量
        const result = await doPost("/api/login", formData);
        // 处理成功响应
        console.log('登录成功，响应结果：', result);
        ElMessage({
          message: '登录成功',
          type: 'success',
          duration: 2000
        });
        // 这里可以添加跳转逻辑，例如：
        // router.push('/dashboard');
        
      } catch (error) {
        // 捕获异步请求过程中的错误
        console.error('登录失败：', error);
        ElMessage({
          message: error.message || '登录失败，请稍后重试',
          type: 'error',
          duration: 3000
        });
      }
    } else {
      // 表单验证失败，显示错误提示
      ElMessage({
        message: '表单验证未通过',
        type: 'error',
        duration: 2000
      })
    }
  })
}
</script>

<style scoped>
.el-aside{
    background-color: black;
    text-align: center;
}
.el-main{
    height: calc(100vh);
    display: flex;
    justify-content: center;
    align-items: center;
}
.logo{
    width: 60%;
}
.Thetitle{
    color: white;
    font-size: 50px;
}
.login-title {
    font-weight: 700;
    text-align: center;
    margin-bottom: 24px;
}
.login-form{
    width: 100%;
    max-width: 760px;
    margin: 0 auto;
}
.login-button {
    width: 100%;
}
.el-main {
    height: calc(100vh);
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 100px;
}
</style>