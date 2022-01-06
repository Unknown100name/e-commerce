package org.unknown100name.ecommerce.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * 添加到购物车 + 添加个数
     * @param userId
     * @param innerItemId
     * @return
     */
    @PostMapping("/shoppingCar/increase")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> shoppingCarIncrease(String userId, String innerItemId){
        return shoppingCarService.increase(Long.parseLong(userId), Long.parseLong(innerItemId));
    }

    /**
     * 减少个数
     * @param userId
     * @param innerItemId
     * @return
     */
    @PostMapping("/shoppingCar/decrease")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> shoppingCarDecrease(String userId, String innerItemId){
        return shoppingCarService.decrease(Long.parseLong(userId), Long.parseLong(innerItemId));
    }

    /**
     * 从购物车中删除
     * @param userId
     * @param innerItemId
     * @return
     */
    @PostMapping("/shoppingCar/delete")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> shoppingCarDelete(String userId, String innerItemId){
        return shoppingCarService.delete(Long.parseLong(userId), Long.parseLong(innerItemId));
    }

    /**
     * 获取购物车详情
     * @param userId
     * @return
     */
    @PostMapping("/shoppingCar/get")
    @ResponseBody
    @TokenAuth
    public BaseResult<ShoppingCarDTO> shoppingCarGet(String userId){
        return shoppingCarService.get(Long.parseLong(userId));
    }

    /**
     * 提交订单准备支付
     * @param shoppingCarTurnOrderParam
     * @return
     */
    @PostMapping("/order/prepay")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> orderPrePay(@RequestBody ShoppingCarTurnOrderParam shoppingCarTurnOrderParam){
        return orderService.prePay(shoppingCarTurnOrderParam);
    }


    /**
     * 支付
     * @param orderId
     * @return
     */
    @PostMapping("/order/pay")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> orderPay(String userId, String orderId){
        return orderService.pay(Long.parseLong(orderId));
    }

    /**
     * 确认收货
     * @param innerOrderId
     * @return
     */
    @PostMapping("/order/accept")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> orderAccept(String userId, String innerOrderId){
        return orderService.accept(Long.parseLong(innerOrderId));
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @PostMapping("/order/cancel")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> orderCancel(String userId, String orderId){
        return orderService.buyerCancel(Long.parseLong(orderId));
    }

    // /**
    //  * 退货
    //  * @param innerOrderId
    //  * @return
    //  */
    // @PostMapping("/order/reject")
    // @ResponseBody
    // @TokenAuth
    // public BaseResult<?> orderReject(String userId, String innerOrderId){
    //     return orderService.reject(Long.parseLong(innerOrderId));
    // }

    /**
     * TODO：获得推荐列表
     * @param userId
     * @return
     */
    @PostMapping("/item/recommendList")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> orderReject(String userId){
        return null;
    }
}