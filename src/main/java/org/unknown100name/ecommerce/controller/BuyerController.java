package org.unknown100name.ecommerce.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;
import org.unknown100name.ecommerce.aspect.token.TokenAuth;
import org.unknown100name.ecommerce.pojo.dto.OrderDTO;
import org.unknown100name.ecommerce.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommerce.pojo.vo.EvaluateCreateParam;
import org.unknown100name.ecommerce.pojo.vo.ShoppingCarTurnOrderParam;
import org.unknown100name.ecommerce.service.EvaluateService;
import org.unknown100name.ecommerce.service.OrderService;
import org.unknown100name.ecommerce.service.ShoppingCarService;
import org.unknown100name.ecommerce.util.BaseResult;

import java.util.List;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/2
 */
@RestController
@RequestMapping("buyer")
public class BuyerController {

    @Resource
    private ShoppingCarService shoppingCarService;

    @Resource
    private OrderService orderService;

    @Resource
    private EvaluateService evaluateService;

    /**
     * 添加到购物车 + 添加个数
     * @param userId
     * @param innerItemId
     * @return
     */
    @PostMapping("/shoppingCar/increase")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> shoppingCarIncrease(String userId, String innerItemId) {
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
    public BaseResult<String> shoppingCarDecrease(String userId, String innerItemId) {
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
    public BaseResult<String> shoppingCarDelete(String userId, String innerItemId) {
        return shoppingCarService.delete(Long.parseLong(userId), Long.parseLong(innerItemId));
    }

    /**
     * 获取购物车详情
     * @param userId
     * @return
     */
    @GetMapping("/shoppingCar/get")
    @ResponseBody
    @TokenAuth
    public BaseResult<ShoppingCarDTO> shoppingCarGet(String userId) {
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
    public BaseResult<String> orderPrePay(@RequestBody ShoppingCarTurnOrderParam shoppingCarTurnOrderParam) {
        return orderService.prePay(shoppingCarTurnOrderParam);
    }

    /**
     * 查看个人订单
     * @param userId
     * @return
     */
    @GetMapping("/order/check")
    @ResponseBody
    @TokenAuth
    public BaseResult<List<OrderDTO>> orderCheck(String userId){
        return orderService.check(userId);
    }

    /**
     * 支付
     * @param orderId
     * @return
     */
    @PostMapping("/order/pay")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> orderPay(String userId, String orderId) {
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
    public BaseResult<String> orderAccept(String userId, String innerOrderId) {
        return orderService.accept(Long.parseLong(innerOrderId));
    }

    /**
     * 取消订单
     * @param innerOrderId
     * @return
     */
    @PostMapping("/order/cancel")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> orderCancel(String userId, String innerOrderId) {
        return orderService.buyerCancel(Long.parseLong(innerOrderId));
    }

    // /**
    //  * 退货
    //  * @param innerOrderId
    //  * @return
    //  */
    // @PostMapping("/order/reject")
    // @ResponseBody
    // @TokenAuth
    // public BaseResult<String> orderReject(String userId, String innerOrderId){
    //     return orderService.reject(Long.parseLong(innerOrderId));
    // }

    /**
     * 根据 orderId 获取商品并评论
     * @param userId
     * @param evaluateGiveParam
     * @return
     */
    @PostMapping("/order/evaluate")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> evaluate(String userId, @RequestBody EvaluateCreateParam evaluateGiveParam) {
        return evaluateService.insertEvaluate(evaluateGiveParam);
    }
}