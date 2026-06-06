package com.lukai.mapper;

import com.lukai.model.RolePermission;

/**
* @author 千鸟
* @description 针对表【t_role_permission(角色权限关系表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.RolePermission
*/
public interface RolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

}
