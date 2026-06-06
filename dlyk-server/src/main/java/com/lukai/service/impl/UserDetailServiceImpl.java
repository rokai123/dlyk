package com.lukai.service.impl;

import com.lukai.domain.Permission;
import com.lukai.domain.Role;
import com.lukai.domain.User;
import com.lukai.mapper.PermissionMapper;
import com.lukai.mapper.RoleMapper;
import com.lukai.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名获取用户信息
        User user = userMapper.SelectByLoginAct(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //获取用户角色和权限
        List<Role> roles = roleMapper.selectRolesByUserId(user.getId());
        List<String> roleList= new ArrayList<>();
        List<String> permissionList = new ArrayList<>();

        if (!ObjectUtils.isEmpty(roles)){
            roles.forEach(role -> {
                roleList.add(role.getRole());
                List<Permission> permissions = permissionMapper.selectPermissionsByRoleId(role.getId());
                //role.setPermissions(permissions);//一个角色对应着多个权限，在角色对象类中添加List<Permission> permissions;
                permissions.forEach(permission -> {
                    permissionList.add(permission.getCode());
                });
            });
            //将不为空的roles给user
            //user.setRoles(roles);

            user.setRoleList(roleList);
            user.setPermissionList(permissionList);
        }

        //将用户信息返回给spring security
        return user;
    }
}
