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

<script>
import { doPost } from '../http/HttpRequest'
import { ElMessage } from 'element-plus'
import { messageTitle } from '../utils/Tools'
export default {
  name: 'LoginView',
  data() {
    return {
      // 标签默认右对齐
      labelPosition: 'right',
      // 单个表单项标签对齐设置
      itemLabelPosition: '',
      // 登录表单模型
      loginForm: {
        loginAct: '',
        loginPwd: '',
        rememberPwd: false,
      },
      // 表单验证规则对象
      LoginFormRules: {
        loginAct: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          },
          {
            min: 3,
            max: 32,
            message: '用户名长度在 3 到 32 个字符',
            trigger: 'blur',
          },
        ],
        loginPwd: [
          {
            required: true,
            message: '密码不能为空',
            trigger: 'blur',
          },
          {
            min: 6,
            max: 32,
            message: '密码长度在 6 到 32 个字符',
            trigger: 'blur',
          },
        ],
      },
    }
  },
  methods: {
    async submitLogin() {
      // 使用 ref 引用获取表单实例，并执行校验
      /* this.$refs.formRef.validate(async (valid) => {
        if (!valid) {
          messageTitle("表单验证失败","error");//提示信息
          return
        } */

       /* 1. 优化表单校验写法
        Element Plus 的 validate 支持 Promise，更清晰： */
        try {
          await this.$refs.formRef.validate()
        } catch {
          messageTitle('表单验证失败', 'error')
          return
        }
        try {
          // 构建要提交的表单数据
          const formData = new FormData()
          formData.append('loginAct', this.loginForm.loginAct)
          formData.append('loginPwd', this.loginForm.loginPwd)
          
          // 发送登录请求，等待响应结果
          const result = await doPost('/api/login', formData)
          /* if(result.data.code==200){
            messageTitle(result.data.message,"success");//提示信息
          }else{
            messageTitle(result.data.data,"error");//提示信息
          } */

          /* 2. 登录请求结果处理更清晰
          建议统一解构 result.data： */
          const { code, message, data } = result.data
          if (code === 200) {//登录成功
            messageTitle(message, 'success')
            // 登录成功，跳转到首页
            this.$router.push('/dashboard')
          } else {//登录失败
            messageTitle(data || '登录失败', 'error')
          }
          

          // 成功后可继续添加页面跳转逻辑，例如：
          // this.$router.push('/dashboard')
        } catch (error) {
          const msg = error?.response?.data?.message || error?.message || '请求失败'  //?. 用于安全地访问嵌套对象属性，如果前面的值是 null 或 undefined，就直接返回 undefined，不会报错。
          messageTitle(msg, 'error')
        }
      //})
    },
  },
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