package com.lukai.config.handle;

import com.lukai.domain.result.R;
import com.lukai.utils.JSONUtils;
import com.lukai.utils.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 无权限处理器
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 1. 封装响应结果
        R result = R.builder().code(-2).message("没有权限访问！").build();


        // 2. 转为json
        String json = JSONUtils.beanToJson(result);

        // 3. 响应出去
        ResponseUtils.write(response, json);
    }
}
