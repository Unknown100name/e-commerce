package org.unknown100name.ecommercebackend.service;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommercebackend.pojo.dto.OrderDTO;
import org.unknown100name.ecommercebackend.pojo.vo.ShoppingCarTurnOrderParam;
import common.BaseResult;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022/1/3
 * @description
 */
@Service
public interface OrderService {

    BaseResult<String> prePay(ShoppingCarTurnOrderParam shoppingCarTurnOrderParam);

    BaseResult<String> pay(Long orderId);

    BaseResult<String> send(Long innerOrderId, String expressCode);

    BaseResult<String> accept(Long innerOrderId);

    BaseResult<String> buyerCancel(Long innerOrderId);

    BaseResult<String> sellerCancel(Long innerOrderId);

    BaseResult<List<OrderDTO>> check(String userId);

    // BaseResult<String> reject(Long innerOrderId);
    
}
