package com.lukai.mapper;

import com.lukai.model.Clue;

/**
* @author 千鸟
* @description 针对表【t_clue(线索表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.Clue
*/
public interface ClueMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Clue record);

    int insertSelective(Clue record);

    Clue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);

}
