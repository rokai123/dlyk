package com.lukai.mapper;

import com.lukai.model.Role;

/**
* @author 千鸟
* @description 针对表【t_role(角色表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.Role
*/
public interface RoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

}
