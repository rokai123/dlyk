package com.lukai.mapper;

import com.lukai.model.CustomerRemark;

/**
* @author 千鸟
* @description 针对表【t_customer_remark(客户跟踪记录表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.CustomerRemark
*/
public interface CustomerRemarkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CustomerRemark record);

    int insertSelective(CustomerRemark record);

    CustomerRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerRemark record);

    int updateByPrimaryKey(CustomerRemark record);

}
