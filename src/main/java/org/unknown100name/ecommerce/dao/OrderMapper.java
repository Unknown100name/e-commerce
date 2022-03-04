package org.unknown100name.ecommerce.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface OrderMapper extends BaseMapper<Order> {


    /**
     * 插入订单
     * order 为关键字 自动使用 BaseMapper 会导致错误
     * @param order
     * @return
     */
    @Override
    int insert(Order order);

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
     * 根据用户名查询大订单
     * @param userId
     * @return
     */
    List<OrderDTO> getOrderByUserId(String userId);

    /**
     * 查询小订单
     * @param innerOrderId
     * @return
     */
    InnerOrderDTO getInnerOrderById(Long innerOrderId);

    /**
     * 查询小订单
     * @param orderId
     * @return
     */
    InnerOrderDTO getInnerOrderByOrderId(Long orderId);

    /**
     * 更新 InnerOrder 状态
     * @param innerOrderId innerOrderId
     * @param from
     * @param to
     */
    void updateInnerOrderState(Long innerOrderId, int from, int to);

    /**
     * 添加发货信息
     * @param innerOrderId
     * @param expressCode
     */
    void updateExpressCode(Long innerOrderId, String expressCode);
    
    /**
     * 检查时候有存在未结束的 订单
     * @param itemId
     * @return
     */
    boolean existInnerOrderNotFinish(Long itemId);
}
