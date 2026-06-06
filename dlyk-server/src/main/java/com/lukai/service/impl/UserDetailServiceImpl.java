package com.lukai.service.impl;

import com.lukai.model.User;
import com.lukai.mapper.PermissionMapper;
import com.lukai.mapper.RoleMapper;
import com.lukai.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名获取用户信息
        User user = userMapper.SelectByLoginAct(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }

        //将用户信息返回给spring security
        return user;
    }
}
