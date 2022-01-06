package org.unknown100name.ecommerce.service;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.pojo.vo.ShoppingCarTurnOrderParam;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @since 2022/1/3
 * @description
 */
@Service
public interface OrderService {

    BaseResult<?> prePay(ShoppingCarTurnOrderParam shoppingCarTurnOrderParam);

    BaseResult<?> pay(Long orderId);

    BaseResult<?> send(Long innerOrderId);

    BaseResult<?> accept(Long innerOrderId);

    BaseResult<?> buyerCancel(Long innerOrderId);

    BaseResult<?> sellerCancel(Long innerOrderId);
    
    // BaseResult<?> reject(Long innerOrderId);
    
}
