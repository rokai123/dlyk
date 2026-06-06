package com.lukai.mapper;

import com.lukai.model.User;

/**
* @author 千鸟
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
