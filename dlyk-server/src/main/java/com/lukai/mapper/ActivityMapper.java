package com.lukai.mapper;

import com.lukai.model.Activity;

/**
* @author 千鸟
* @description 针对表【t_activity(市场活动表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.Activity
*/
public interface ActivityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

}
