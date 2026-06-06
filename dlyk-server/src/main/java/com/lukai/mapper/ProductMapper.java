package com.lukai.mapper;

import com.lukai.model.Product;

/**
* @author 千鸟
* @description 针对表【t_product(产品表)】的数据库操作Mapper
* @createDate 2026-06-06 15:28:44
* @Entity com.lukai.model.Product
*/
public interface ProductMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

}
