package com.lukai.config.filter;

import com.lukai.domain.User;
import com.lukai.domain.constants.Constants;
import com.lukai.domain.result.R;
import com.lukai.utils.JSONUtils;
import com.lukai.utils.JWTUtils;
import com.lukai.utils.ResponseUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 校验令牌的过滤器
 */
@Component
public class JWTVerifyFilter extends OncePerRequestFilter {
    @Resource
    RedisTemplate<String, Object> redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //指定需要校验的请求
        String requestURI = request.getRequestURI();
        if (requestURI.contains("/api/login")){
            filterChain.doFilter(request, response);
            return;
        }
        //获取令牌
        String token = request.getHeader("Authorization");

        //当令牌不存在时，返回错误信息，重新登录
        R result=null;
        if (!StringUtils.hasText(token)){
            result=R.builder().code(10001).message("token不能为空").build();
            String resultJosn = JSONUtils.beanToJson(result);
            ResponseUtils.write(response,resultJosn);
            return;
        }

        //令牌存在时，校验令牌是否合法
        if (!JWTUtils.checkeJwt(token)){
            result=R.builder().code(10002).message("token不合法").build();
            String resultJosn = JSONUtils.beanToJson(result);
            ResponseUtils.write(response,resultJosn);
            return;
        }

        //从前端传过来的jwt中解析出用户user的信息
        String userJson = JWTUtils.parseJwt(token);
        User user = JSONUtils.jsonToBean(userJson, User.class);
        //令牌合法时，校验令牌是否和储存在redis中的一致
        String redisToken = (String) redisTemplate.opsForValue().get(Constants.USER_TOKEN+user.getId());

        //判断redisToken是否还存在
        if (!StringUtils.hasText(redisToken)){
            result=R.builder().code(10003).message("token已过期").build();
            String resultJosn = JSONUtils.beanToJson(result);
            ResponseUtils.write(response,resultJosn);
            return;
        }

        //比较令牌：校验 Redis 中的 Token 是否与请求头中的 Token 一致（用于实现单点登录或强制下线功能）
        if (!redisToken.equals(token)){
            result=R.builder().code(10004).message("登录已过期").build();
            String resultJosn = JSONUtils.beanToJson(result);
            ResponseUtils.write(response,resultJosn);
            return;
        }
        
        //告知框架，该请求已经完成，可以继续处理下一个过滤器或者请求
//        这段代码的作用是创建Spring Security的认证对象，将用户信息存入Security上下文
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//        UsernamePasswordAuthenticationToken 对象后，没有将其设置到Security上下文中，导致这个认证对象没有生效。应该添加：
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request, response);

    }
}
