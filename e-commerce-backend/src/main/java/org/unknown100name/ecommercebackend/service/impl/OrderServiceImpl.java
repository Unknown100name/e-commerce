package org.unknown100name.ecommercebackend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unknown100name.ecommercebackend.dao.ContactMapper;
import org.unknown100name.ecommercebackend.dao.ItemMapper;
import org.unknown100name.ecommercebackend.dao.OrderMapper;
import org.unknown100name.ecommercebackend.dao.ShoppingCarMapper;
import org.unknown100name.ecommercebackend.pojo.dto.ContactDTO;
import org.unknown100name.ecommercebackend.pojo.dto.InnerOrderDTO;
import org.unknown100name.ecommercebackend.pojo.dto.InnerShoppingCarDTO;
import org.unknown100name.ecommercebackend.pojo.dto.OrderDTO;
import org.unknown100name.ecommercebackend.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommercebackend.pojo.entity.InnerOrder;
import org.unknown100name.ecommercebackend.pojo.entity.Order;
import org.unknown100name.ecommercebackend.pojo.vo.ShoppingCarTurnOrderParam;
import org.unknown100name.ecommercebackend.service.OrderService;
import common.BaseResult;
import common.BaseResultMsg;

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

    private final Object inventoryLock = new Object();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<String> prePay(ShoppingCarTurnOrderParam shoppingCarTurnOrderParam) {
        try{
            // 验证 ShoppingCarId
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

            // 目前购物车的 List 与 Map
            List<InnerShoppingCarDTO> existInnerShoppingCarList = existShoppingCarDTO.getInnerShoppingCarList();
            Map<Long, InnerShoppingCarDTO> existInnerShoppingCarMap = new HashMap<>(existInnerShoppingCarList.size());
            existInnerShoppingCarList.forEach(
                    innerShoppingCarDTO -> existInnerShoppingCarMap.put(innerShoppingCarDTO.getId(), innerShoppingCarDTO));

            // 验证库存
            synchronized (inventoryLock) {

                // 验证
                for (String insertShoppingCarId : shoppingCarTurnOrderParam.getInnerShoppingCarId()) {
                    InnerShoppingCarDTO innerShoppingCarDTO = existInnerShoppingCarMap.get(Long.parseLong(insertShoppingCarId));
                    if (innerShoppingCarDTO == null) {
                        return BaseResult.failResult(BaseResultMsg.ERROR_PARAM);
                    }
                    if (innerShoppingCarDTO.getInnerItemDTO().getInventory() != -1
                            && innerShoppingCarDTO.getNumber() > innerShoppingCarDTO.getInnerItemDTO().getInventory()) {
                        return BaseResult.failResult(BaseResultMsg.ERROR_INVENTORY_NOT_MATCH);
                    }
                }

                // 减少库存
                for (String insertShoppingCarId : shoppingCarTurnOrderParam.getInnerShoppingCarId()) {
                    InnerShoppingCarDTO innerShoppingCarDTO = existInnerShoppingCarMap.get(Long.parseLong(insertShoppingCarId));
                    itemMapper.decreaseInventory(innerShoppingCarDTO.getInnerItemDTO().getId(), innerShoppingCarDTO.getNumber());
                }
            }

            // 创建 Order 并 insert
            // 待插入的 Order
            Order insertOrder = new Order(shoppingCarTurnOrderParam);
            // 待插入的 InnerOrder
            List<InnerOrder> insertInnerOrderList = new ArrayList<>();
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
                        shoppingCarMapper.deleteById(insertShoppingCarId)
            );
        
            return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
        }catch(Throwable t){
            t.printStackTrace();
            return BaseResult.failResult(BaseResultMsg.ERROR_UNKNOWN);
        }
    }

    @Override
    public BaseResult<String> pay(Long orderId) {
        OrderDTO existOrder = orderMapper.getOrderById(orderId);
        for (InnerOrderDTO innerOrder : existOrder.getInnerOrderList()) {
            orderMapper.updateInnerOrderState(innerOrder.getId(), 0, 1);
        }
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<String> send(Long innerOrderId, String expressCode) {
        int result = orderMapper.updateInnerOrderState(innerOrderId, 1, 2);
        if (result == 0){
            return BaseResult.failResult(BaseResultMsg.ERROR_STATUS);
        }
        orderMapper.updateExpressCode(innerOrderId, expressCode);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }    
    
    @Override
    public BaseResult<String> accept(Long innerOrderId) {
        int result = orderMapper.updateInnerOrderState(innerOrderId, 2, 3);
        if (result == 0){
            return BaseResult.failResult(BaseResultMsg.ERROR_STATUS);
        }
        InnerOrderDTO innerOrderDTO = orderMapper.getInnerOrderById(innerOrderId);
        itemMapper.increaseSell(innerOrderDTO.getInnerItemId(), innerOrderDTO.getNumber());
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> buyerCancel(Long innerOrderId) {
        int result = orderMapper.updateInnerOrderState(innerOrderId, 0, -1);
        if (result == 0){
            return BaseResult.failResult(BaseResultMsg.ERROR_STATUS);
        }
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> sellerCancel(Long innerOrderId) {
        int result = orderMapper.updateInnerOrderState(innerOrderId, 1, -1);
        if (result == 0){
            return BaseResult.failResult(BaseResultMsg.ERROR_STATUS);
        }
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<List<OrderDTO>> buyerCheck(Long userId) {
        List<OrderDTO> orderList = orderMapper.getOrderByUserId(userId);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, orderList);
    }

    @Override
    public BaseResult<List<InnerOrderDTO>> sellerCheck(Long userId) {
        List<InnerOrderDTO> orderList = orderMapper.getOrderByShopId(userId);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, orderList);
    }

    // @Override
    // public BaseResult<String> reject(Long innerOrderId) {
    //     orderMapper.updateInnerOrderState(innerOrderId, 3, 5);
    //     return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    // }

}
