package com.lukai.config;


import com.lukai.config.filter.JWTVerifyFilter;
import com.lukai.config.handle.MyAccessDeniedHandler;
import com.lukai.config.handle.MyAuthenticationFailureHandler;
import com.lukai.config.handle.MyAuthenticationSuccessHandler;
import com.lukai.config.handle.MyLogoutSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Lukai
 *2026/5/10
 */
//@EnableMethodSecurity//开启方法的权限认证
@Configuration
public class SpringSecurityConfig {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private JWTVerifyFilter jwtVerifyFilter;

    //编写密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> {
                    csrf.disable();//关闭跨站请求伪造
                })
                .formLogin(formLogin -> {//认证
                    formLogin.usernameParameter("loginAct")
                            .passwordParameter("loginPwd")
                            .loginProcessingUrl("/api/login")//进行认证的地址
                            .successHandler(myAuthenticationSuccessHandler)//认证成功处理程序
                            .failureHandler(myAuthenticationFailureHandler);//认证失败处理程序


                })
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.anyRequest().authenticated();//所有请求路径都需要认证
                })
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling.accessDeniedHandler(myAccessDeniedHandler);//无权限处理器
                })
                .logout(logout -> {
                    logout.logoutUrl("/api/logout")//指定退出地址\
                            .invalidateHttpSession(true)//清除session
                            .logoutSuccessHandler(myLogoutSuccessHandler);
                })
                .sessionManagement(sessionManagement -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);//禁用session
                })
                .addFilterBefore(jwtVerifyFilter, LogoutFilter.class)
//                .addFilterBefore(captchaCodeVerify, UsernamePasswordAuthenticationFilter.class)//在账号密码前添加验证码验证
                .cors(cors -> {
                    cors.configurationSource(configurationSource());
                })
                .build();
    }

    /**
     * 配置跨域资源共享（CORS）策略
     * 允许前端应用跨域访问后端接口
     *
     * @return CorsConfigurationSource CORS配置源
     */
    @Bean
    public CorsConfigurationSource configurationSource() {
        // 创建CORS配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 设置允许的请求来源，"*"表示允许所有域名访问
        // 生产环境建议指定具体的域名以提高安全性
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));

        // 设置允许的HTTP请求方法，"*"表示允许所有方法（GET, POST, PUT, DELETE等）
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));

        // 设置允许的请求头，"*"表示允许所有请求头
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));

        // 创建基于URL的CORS配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 注册CORS配置，"/**"表示对所有路径生效
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
