package org.unknown100name.ecommerce.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.dto.InnerShoppingCarDTO;
import org.unknown100name.ecommerce.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommerce.pojo.entity.ShoppingCar;

import java.util.List;

/**
 * @author unknown100name
 * @description 购物车数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface ShoppingCarMapper extends BaseMapper<ShoppingCar> {

    /**
     * 添加至购物车
     * @param userId
     * @param innerItemId
     */
    void create(Long id, Long userId, Long innerItemId);

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
    ShoppingCarDTO getShoppingCarByUserId(Long userId);

    /**
     * 查询 inner By User
     * @param userId
     * @return
     */
    List<InnerShoppingCarDTO> getInnerShoppingCarByUserId(Long userId);

    /**
     * 查询 inner 定位
     * @param userId
     * @param innerItemId
     * @return
     */
    InnerShoppingCarDTO getInnerShoppingCarByUserIdAndInnerItemId(Long userId, Long innerItemId);


    
}
