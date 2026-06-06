package com.lukai.mapper;

import com.lukai.model.Tran;

/**
* @author 千鸟
* @description 针对表【t_tran(交易表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.Tran
*/
public interface TranMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Tran record);

    int insertSelective(Tran record);

    Tran selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tran record);

    int updateByPrimaryKey(Tran record);

}
