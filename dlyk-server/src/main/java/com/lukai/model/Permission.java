package com.lukai.model;

import lombok.Data;

/**
 * 权限表
 * @TableName t_permission
 */
@Data
public class Permission {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String type;

    /**
     * 
     */
    private Integer parentId;

    /**
     * 
     */
    private Integer orderNo;

    /**
     * 
     */
    private String icon;
}