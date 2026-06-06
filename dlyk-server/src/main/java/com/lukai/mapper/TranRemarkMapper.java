package com.lukai.mapper;

import com.lukai.model.TranRemark;

/**
* @author 千鸟
* @description 针对表【t_tran_remark(交易跟踪记录表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.TranRemark
*/
public interface TranRemarkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TranRemark record);

    int insertSelective(TranRemark record);

    TranRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TranRemark record);

    int updateByPrimaryKey(TranRemark record);

}
