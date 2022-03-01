package org.unknown100name.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unknown100name.ecommerce.dao.ContactMapper;
import org.unknown100name.ecommerce.dao.ItemMapper;
import org.unknown100name.ecommerce.dao.OrderMapper;
import org.unknown100name.ecommerce.dao.ShoppingCarMapper;
import org.unknown100name.ecommerce.pojo.dto.ContactDTO;
import org.unknown100name.ecommerce.pojo.dto.InnerOrderDTO;
import org.unknown100name.ecommerce.pojo.dto.InnerShoppingCarDTO;
import org.unknown100name.ecommerce.pojo.dto.OrderDTO;
import org.unknown100name.ecommerce.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommerce.pojo.entity.InnerOrder;
import org.unknown100name.ecommerce.pojo.entity.Order;
import org.unknown100name.ecommerce.pojo.vo.ShoppingCarTurnOrderParam;
import org.unknown100name.ecommerce.service.OrderService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;

/**
 * @author unknown100name
 * @since 2022/1/3
 * @description
 *
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ShoppingCarMapper shoppingCarMapper;

    @Resource
    private ContactMapper contactMapper;

    @Resource
    private ItemMapper itemMapper;

    /**
     * TODO: 待测试
     */
    @Override
    @Transactional
    public BaseResult<String> prePay(ShoppingCarTurnOrderParam shoppingCarTurnOrderParam) {
        try{
            // 验证 ShoopingCarId
            ShoppingCarDTO existShoppingCarDTO = shoppingCarMapper.getShoppingCarByUserId(Long.parseLong(shoppingCarTurnOrderParam.getUserId()));
            Set<Long> existInnerShoppingCarIdList = new HashSet<>();
            existShoppingCarDTO.getInnerShoppingCarList().forEach(
                innerShoppingCarDTO -> existInnerShoppingCarIdList.add(innerShoppingCarDTO.getId()));
            for (String inputInnerShoppingCarId : shoppingCarTurnOrderParam.getInnerShoppingCarId()) {
                if(!existInnerShoppingCarIdList.contains(Long.parseLong(inputInnerShoppingCarId))){
                    return BaseResult.failResult(BaseResultMsg.ERROR_PARAM);
                }
            }
            
            // 验证联系人 Id
            List<ContactDTO> existContactList = contactMapper.getContactByUserId(Long.parseLong(shoppingCarTurnOrderParam.getUserId()));
            Set<Long> existContactIdList = new HashSet<>();
            existContactList.forEach(
                contactDTO -> existContactIdList.add(contactDTO.getId()));
            if(!existContactIdList.contains(Long.parseLong(shoppingCarTurnOrderParam.getContactId()))){
                return BaseResult.failResult(BaseResultMsg.ERROR_PARAM);
            }

            // 创建 Order 并 insert
            // 待插入的 Order
            Order insertOrder = new Order(shoppingCarTurnOrderParam);
            // 待插入的 InnerOrder
            List<InnerOrder> insertInnerOrderList = new ArrayList<>();
            // 目前购物车的 List 与 Map
            List<InnerShoppingCarDTO> existInnerShoppingCarList = existShoppingCarDTO.getInnerShoppingCarList();
            Map<Long, InnerShoppingCarDTO> existInnerShoppingCarMap = new HashMap<>();
            existInnerShoppingCarList.forEach(
                innerShoopingCarDTO -> existInnerShoppingCarMap.put(innerShoopingCarDTO.getId(), innerShoopingCarDTO));
            // 构造待插入的 InnerOrder
            shoppingCarTurnOrderParam.getInnerShoppingCarId().forEach(
                insertShoppingCarId -> {
                    InnerShoppingCarDTO existInnerShoppingCar = existInnerShoppingCarMap.get(Long.parseLong(insertShoppingCarId));
                    InnerOrder insertInnerOrder = new InnerOrder(insertOrder, existInnerShoppingCar);
                    insertInnerOrderList.add(insertInnerOrder);
                }
            );
            orderMapper.insert(insertOrder);
            orderMapper.insertInnerOrder(insertInnerOrderList);

            // 删除 ShoppingCar
            shoppingCarTurnOrderParam.getInnerShoppingCarId().forEach(
                insertShoppingCarId -> 
                    shoppingCarMapper.delete(
                        Long.parseLong(shoppingCarTurnOrderParam.getUserId()),
                        Long.parseLong(insertShoppingCarId))
            );
        
            return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
        }catch(Throwable t){
            return BaseResult.failResult(BaseResultMsg.ERROR_UNKNOWN);
        }
    }

    @Override
    public BaseResult<String> pay(Long orderId) {
        OrderDTO existOrder = orderMapper.getOrderById(orderId);
        existOrder.getInnerOrderList().forEach(
            innerOrder -> orderMapper.updateInnerOrderState(innerOrder.getId(), 0, 1)
        );
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> send(Long innerOrderId) {
        orderMapper.updateInnerOrderState(innerOrderId, 1, 2);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }    
    
    @Override
    public BaseResult<String> accept(Long innerOrderId) {
        orderMapper.updateInnerOrderState(innerOrderId, 2, 3);
        InnerOrderDTO innerOrderDTO = orderMapper.getInnerOrderById(innerOrderId);
        itemMapper.increaseSell(innerOrderDTO.getInnerItemId());
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> buyerCancel(Long innerOrderId) {
        orderMapper.updateInnerOrderState(innerOrderId, 0, -1);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> sellerCancel(Long innerOrderId) {
        orderMapper.updateInnerOrderState(innerOrderId, 1, -1);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }
    
    
    // @Override
    // public BaseResult<String> reject(Long innerOrderId) {
    //     orderMapper.updateInnerOrderState(innerOrderId, 3, 5);
    //     return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    // }

}
