package com.lukai.mapper;

import com.lukai.model.ClueRemark;

/**
* @author 千鸟
* @description 针对表【t_clue_remark(线索跟踪记录表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.ClueRemark
*/
public interface ClueRemarkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ClueRemark record);

    int insertSelective(ClueRemark record);

    ClueRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClueRemark record);

    int updateByPrimaryKey(ClueRemark record);

}
