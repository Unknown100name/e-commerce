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

    BaseResult<String> prePay(ShoppingCarTurnOrderParam shoppingCarTurnOrderParam);

    BaseResult<String> pay(Long orderId);

    BaseResult<String> send(Long innerOrderId);

    BaseResult<String> accept(Long innerOrderId);

    BaseResult<String> buyerCancel(Long innerOrderId);

    BaseResult<String> sellerCancel(Long innerOrderId);
    
    // BaseResult<String> reject(Long innerOrderId);
    
}
