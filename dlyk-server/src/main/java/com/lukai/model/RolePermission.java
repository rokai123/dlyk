package generator.domain;

import lombok.Data;

/**
 * 角色权限关系表
 * @TableName t_role_permission
 */
@Data
public class RolePermission {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer roleId;

    /**
     * 
     */
    private Integer permissionId;
}