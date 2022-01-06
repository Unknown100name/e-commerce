package org.unknown100name.ecommerce.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.dto.InnerOrderDTO;
import org.unknown100name.ecommerce.pojo.dto.OrderDTO;
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

    /**
     * 查询大订单
     * @param orderId
     * @return
     */
    OrderDTO getOrderById(Long orderId);
    
    /**
     * 查询小订单
     * @param InnerOrderId
     * @return
     */
    InnerOrderDTO getInnerOrderById(Long InnerOrderId);

    /**
     * 更新 InnerOrder 状态
     * @param id innerOrderId
     * @param from
     * @param to
     */
    void updateInnerOrderState(Long innerOrderId, int from, int to);
    
    /**
     * 检查时候有存在未结束的 订单
     * @param itemId
     * @return
     */
    boolean existInnerOrderNotFinish(Long itemId);
    
}
