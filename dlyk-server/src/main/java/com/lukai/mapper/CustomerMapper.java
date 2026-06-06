package com.lukai.mapper;

import com.lukai.model.Customer;

/**
* @author 千鸟
* @description 针对表【t_customer(客户表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.Customer
*/
public interface CustomerMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

}
