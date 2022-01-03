package org.unknown100name.ecommerce.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.entity.InnerOrder;
import org.unknown100name.ecommerce.pojo.entity.Order;

/**
 * @author unknown100name
 * @description 订单数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface OrderMapper {

    /**
     * 插入大订单
     * @param insertOrder
     */
    void insertOrder(Order insertOrder);

    /**
     * 插入子订单
     * @param insertInnerOrderList
     */
    void insertInnerOrder(List<InnerOrder> insertInnerOrderList);
    
}
