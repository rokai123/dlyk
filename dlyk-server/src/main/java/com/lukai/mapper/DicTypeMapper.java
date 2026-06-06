package com.lukai.mapper;

import com.lukai.model.DicType;

/**
* @author 千鸟
* @description 针对表【t_dic_type(字典类型表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.DicType
*/
public interface DicTypeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);

}
