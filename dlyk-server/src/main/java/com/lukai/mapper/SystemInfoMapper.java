package com.lukai.mapper;

import com.lukai.model.SystemInfo;

/**
* @author 千鸟
* @description 针对表【t_system_info(系统信息表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.SystemInfo
*/
public interface SystemInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SystemInfo record);

    int insertSelective(SystemInfo record);

    SystemInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemInfo record);

    int updateByPrimaryKey(SystemInfo record);

}
