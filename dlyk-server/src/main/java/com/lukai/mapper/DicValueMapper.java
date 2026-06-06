package com.lukai.mapper;

import com.lukai.model.DicValue;

/**
* @author 千鸟
* @description 针对表【t_dic_value(字典值表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.DicValue
*/
public interface DicValueMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DicValue record);

    int insertSelective(DicValue record);

    DicValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicValue record);

    int updateByPrimaryKey(DicValue record);

}
