package org.unknown100name.ecommercebackend.dao;

import entity.CategoryTwo;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercebackend.pojo.dto.CategoryDTO;

import java.util.List;

/**
 * @author unknown100name
 * @description 联系人数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface CategoryMapper{

    /**
     * 获取目录结构
     * @return
     */
    List<CategoryDTO> getCategoryList();

    /**
     * 从二层获取目录结构
     * @param categoryOneId
     * @return
     */
    List<CategoryTwo> getCategoryTwoListByCategoryOneId(Long categoryOneId);

    /**
     * 从二层获取目录结构
     * @return
     */
    List<CategoryTwo> getCategoryTwoList();
}
