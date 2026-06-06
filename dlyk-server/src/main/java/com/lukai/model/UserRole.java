package com.lukai.model;

import lombok.Data;

/**
 * 用户角色关系表
 * @TableName t_user_role
 */
@Data
public class UserRole {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer roleId;
}