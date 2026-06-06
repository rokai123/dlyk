package com.lukai.mapper;

import com.lukai.model.ActivityRemark;

/**
* @author 千鸟
* @description 针对表【t_activity_remark(市场活动备注表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.ActivityRemark
*/
public interface ActivityRemarkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    ActivityRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);

}
