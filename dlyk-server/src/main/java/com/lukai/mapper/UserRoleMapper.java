package com.lukai.mapper;

import com.lukai.model.UserRole;

/**
* @author 千鸟
* @description 针对表【t_user_role(用户角色关系表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.UserRole
*/
public interface UserRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

}
