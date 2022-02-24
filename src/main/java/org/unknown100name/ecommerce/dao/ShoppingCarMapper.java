package org.unknown100name.ecommerce.dao;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.dto.InnerShoppingCarDTO;
import org.unknown100name.ecommerce.pojo.dto.ShoppingCarDTO;

/**
 * @author unknown100name
 * @description 购物车数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface ShoppingCarMapper{

    /**
     * 增加数量
     * @param userId
     * @param innerItemId
     */
    void increase(Long userId, Long innerItemId);

    /**
     * 减少数量
     * @param userId
     * @param innerItemId
     */
    void decrease(Long userId, Long innerItemId);

    /**
     * 删除
     * @param userId
     * @param innerItemId
     */
    void delete(Long userId, Long innerItemId);

    /**
     * 按照用户查询
     * @param userId
     * @return
     */
    ShoppingCarDTO getByUserId(Long userId);

    /**
     * 查询 inner
     * @param userId
     * @param innerItemId
     * @return
     */
    InnerShoppingCarDTO getByUserIdAndInnerItemId(Long userId, Long innerItemId);

    /**
     * 添加至购物车
     * @param userId
     * @param innerItemId
     */
    void create(Long userId, Long innerItemId);
    
}
