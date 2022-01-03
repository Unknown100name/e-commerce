package org.unknown100name.ecommerce.dao;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.entity.Evaluate;

/**
 * @author unknown100name
 * @description 评论数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface EvaluateMapper {

    /**
     * 查询评论
     */
    void insertEvaluate(Evaluate insertEvaluate);
    
}
