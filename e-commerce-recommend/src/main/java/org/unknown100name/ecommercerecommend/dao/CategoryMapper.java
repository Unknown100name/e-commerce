package org.unknown100name.ecommercerecommend.dao;

import entity.CategoryTwo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author unknown100name
 * @description 联系人数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface CategoryMapper {

    /**
     * 从二层获取目录结构
     * @return
     */
    List<CategoryTwo> getCategoryTwoList();
}
