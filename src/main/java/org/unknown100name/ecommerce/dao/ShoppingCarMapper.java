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
public interface ShoppingCarMapper {

    void increase(Long userId, Long innerItemId);

    void decrease(Long userId, Long innerItemId);

    void delete(Long userId, Long innerItemId);

    ShoppingCarDTO getByUserId(Long userId);

    InnerShoppingCarDTO getByUserIdAndInnerItemId(Long userId, Long innerItemId);

    void create(Long userId, Long innerItemId);
    
}
