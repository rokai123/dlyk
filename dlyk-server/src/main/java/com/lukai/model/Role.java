package com.lukai.model;

import lombok.Data;

/**
 * 角色表
 * @TableName t_role
 */
@Data
public class Role {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String role;

    /**
     * 
     */
    private String roleName;
}