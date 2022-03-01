package org.unknown100name.ecommerce.dao;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.dto.CategoryDTO;
import org.unknown100name.ecommerce.pojo.entity.CategoryTwo;

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

    List<CategoryTwo> getCategoryTwoList();
}
