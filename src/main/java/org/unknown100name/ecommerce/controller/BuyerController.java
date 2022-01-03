package org.unknown100name.ecommerce.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommerce.aspect.TokenAuth;
import org.unknown100name.ecommerce.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommerce.pojo.vo.ShoppingCarTurnOrderParam;
import org.unknown100name.ecommerce.service.OrderService;
import org.unknown100name.ecommerce.service.ShoppingCarService;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/2
 */
@RestController
@RequestMapping("buyer")
public class BuyerController{

    @Resource
    private ShoppingCarService shoppingCarService;

    @Resource
    private OrderService orderService;

    @PostMapping("/shoppingCar/increase")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> shoppingCarIncrease(String userId, String innerItemId){
        return shoppingCarService.increase(Long.parseLong(userId), Long.parseLong(innerItemId));
    }

    @PostMapping("/shoppingCar/decrease")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> shoppingCarDecrease(String userId, String innerItemId){
        return shoppingCarService.decrease(Long.parseLong(userId), Long.parseLong(innerItemId));
    }

    @PostMapping("/shoppingCar/delete")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> shoppingCarDelete(String userId, String innerItemId){
        return shoppingCarService.delete(Long.parseLong(userId), Long.parseLong(innerItemId));
    }

    @PostMapping("/shoppingCar/get")
    @ResponseBody
    @TokenAuth
    public BaseResult<ShoppingCarDTO> shoppingCarGet(String userId){
        return shoppingCarService.get(Long.parseLong(userId));
    }

    @PostMapping("/order/prepay")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> orderPrePay(ShoppingCarTurnOrderParam shoppingCarTurnOrderParam){
        return orderService.prePay(shoppingCarTurnOrderParam);
    }

}