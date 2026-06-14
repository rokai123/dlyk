package com.lukai.config;


import com.lukai.config.filter.JWTVerifyFilter;
import com.lukai.config.handle.MyAccessDeniedHandler;
import com.lukai.config.handle.MyAuthenticationFailureHandler;
import com.lukai.config.handle.MyAuthenticationSuccessHandler;
import com.lukai.config.handle.MyLogoutSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Lukai
 *2026/5/10
 */
@EnableMethodSecurity//开启方法的权限认证
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
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf ->{
                    csrf.disable();//关闭跨站请求伪造
                })
                .formLogin(formLogin -> {//认证
                    formLogin.usernameParameter("loginAct")
                            .passwordParameter("loginPwd")
                            .loginProcessingUrl("/api/login")//进行认证的地址
                            .successHandler(myAuthenticationSuccessHandler)//认证成功处理程序
                            .failureHandler(myAuthenticationFailureHandler);//认证失败处理程序



                })
                .authorizeHttpRequests(authorizeRequests ->{
                    authorizeRequests.anyRequest().authenticated();//所有请求路径都需要认证
                })
                .exceptionHandling(exceptionHandling ->{
                    exceptionHandling.accessDeniedHandler(myAccessDeniedHandler);//无权限处理器
                })
                .logout(logout -> {
                    logout.logoutUrl("/api/logout")//指定退出地址\
                          .invalidateHttpSession(true)//清除session
                          .logoutSuccessHandler(myLogoutSuccessHandler);
                })
                .sessionManagement(sessionManagement ->{
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);//禁用session
                })
                .addFilterBefore(jwtVerifyFilter, LogoutFilter.class)
//                .addFilterBefore(captchaCodeVerify, UsernamePasswordAuthenticationFilter.class)//在账号密码前添加验证码验证
                .build();
    }
}
