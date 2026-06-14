package com.lukai.config.handle;

import com.lukai.domain.result.R;
import com.lukai.utils.JSONUtils;
import com.lukai.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证失败处理器
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 1. 封装响应结果
        //返回JSON出去
        R result=R.builder().code(-1).message("登录失败！").build();
        if(exception instanceof BadCredentialsException){
            result.setData("密码不正确");
        }else if(exception instanceof DisabledException){
            result.setData("账号被禁用");
        }else if(exception instanceof UsernameNotFoundException){
            result.setData("用户名不存在");
        }else if(exception instanceof CredentialsExpiredException){
            result.setData("密码已过期");
        }else if(exception instanceof AccountExpiredException){
            result.setData("账号已过期");
        }else if(exception instanceof LockedException){
            result.setData("账号被锁定");
        }else{
            result.setData("未知异常");
        }

        // 2. 转为json
        String json = JSONUtils.beanToJson(result);


        // 3. 响应出去
        ResponseUtils.write(response, json);
    }
}
