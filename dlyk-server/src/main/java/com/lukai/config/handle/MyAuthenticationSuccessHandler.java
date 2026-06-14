package com.lukai.config.handle;

import com.lukai.domain.constants.Constants;
import com.lukai.domain.result.CodeEnum;
import com.lukai.domain.result.R;
import com.lukai.model.User;
import com.lukai.utils.JSONUtils;
import com.lukai.utils.JWTUtils;
import com.lukai.utils.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 认证成功处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;// Redis模板，用于存储用户认证信息或Token

    /**
     * 认证成功处理逻辑
     * 
     * 当用户成功通过身份验证后调用此方法。主要完成以下操作：
     * 1. 根据用户信息生成JWT令牌
     * 2. 将令牌存储到Redis中并设置过期时间
     * 3. 封装成功响应结果并返回JWT令牌给客户端
     *
     * @param request HTTP请求对象
     * @param response HTTP响应对象，用于向客户端返回认证结果
     * @param authentication 认证对象，包含已认证用户的详细信息
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    throws IOException, ServletException {
        // 通过用户信息生成JWT
        User user = (User) authentication.getPrincipal();
        String userJson = JSONUtils.beanToJson(user);
        String jwt = JWTUtils.createJwt(userJson);

        // 将生成的令牌存储到Redis并设置有效期
        redisTemplate.opsForValue().set(Constants.USER_TOKEN+user.getId(),jwt);
        redisTemplate.expire(Constants.USER_TOKEN+user.getId(), 1, TimeUnit.DAYS);
        
        // 封装成功的响应结果
        R result = R.builder()
                .code(CodeEnum.OK.getCode())
                .message(CodeEnum.OK.getMsg())
                .data(jwt).build();
        
        // 将对象转为JSON并响应给客户端
        String json = JSONUtils.beanToJson(result);
        ResponseUtils.write(response,json);

    }
}
