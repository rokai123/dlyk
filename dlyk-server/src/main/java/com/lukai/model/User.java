package com.lukai.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户表
 * @TableName t_user
 */
@Data
public class User implements UserDetails {
    /**
     * 主键，自动增长，用户ID
     */
    private Integer id;

    /**
     * 登录账号
     */
    private String loginAct;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户手机
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 账户是否没有过期，0已过期 1正常
     */
    private Integer accountNoExpired;

    /**
     * 密码是否没有过期，0已过期 1正常
     */
    private Integer credentialsNoExpired;

    /**
     * 账号是否没有锁定，0已锁定 1正常
     */
    private Integer accountNoLocked;

    /**
     * 账号是否启用，0禁用 1启用
     */
    private Integer accountEnabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 编辑人
     */
    private Integer editBy;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    
    @JsonIgnore
    @Override
    public String getPassword() {
        return this.loginPwd;
    }
    
    @JsonIgnore
    @Override
    public String getUsername() {
        return this.loginAct;
    }
    
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNoExpired != null && this.accountNoExpired == 1;
    }
    
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {

        return this.accountNoLocked != null && this.accountNoLocked == 1;
    }
    
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNoExpired != null && this.credentialsNoExpired == 1;
    }
    
    @JsonIgnore
    @Override
    public boolean isEnabled() {

        return this.accountEnabled != null && this.accountEnabled == 1;
    }
}