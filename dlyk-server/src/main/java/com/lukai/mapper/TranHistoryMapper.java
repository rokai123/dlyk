package com.lukai.mapper;

import com.lukai.model.TranHistory;

/**
* @author 千鸟
* @description 针对表【t_tran_history(交易历史记录表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.TranHistory
*/
public interface TranHistoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TranHistory record);

    int insertSelective(TranHistory record);

    TranHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TranHistory record);

    int updateByPrimaryKey(TranHistory record);

}
