package org.unknown100name.ecommercebackend.controller;

import javax.annotation.Resource;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.unknown100name.ecommercebackend.aspect.token.TokenAuth;
import org.unknown100name.ecommercebackend.pojo.dto.OrderDTO;
import org.unknown100name.ecommercebackend.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommercebackend.pojo.vo.EvaluateCreateParam;
import org.unknown100name.ecommercebackend.pojo.vo.ShoppingCarTurnOrderParam;
import org.unknown100name.ecommercebackend.service.EvaluateService;
import org.unknown100name.ecommercebackend.service.OrderService;
import org.unknown100name.ecommercebackend.service.ShoppingCarService;
import common.BaseResult;

import java.util.List;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/2
 */
@RestController
@RequestMapping("buyer")
@Api(tags = "买家使用相关接口")
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
    @ApiOperation(value = "添加到购物车 + 添加个数", notes = "购物车中没有的时候会添加到购物车, 有的时候会数量 +1")
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
    @ApiOperation(value = "减少个数", notes = "购物车中的数量为 1 的时候会直接删除")
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
    @ApiOperation(value = "从购物车中删除")
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
    @ApiOperation(value = "获取购物车详情")
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
    @ApiOperation(value = "提交订单准备支付", notes = "从购物车中删除对应信息, 并返回订单 ID")
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
    @ApiOperation(value = "查看个人订单")
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
    @ApiOperation(value = "支付")
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
    @ApiOperation(value = "确认收货")
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
    @ApiOperation(value = "取消订单", notes = "取消订单不会将订单退回至购物车")
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
    @ApiOperation(value = "添加评论")
    @PostMapping("/order/evaluate")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> evaluate(String userId, @RequestBody EvaluateCreateParam evaluateGiveParam) {
        return evaluateService.insertEvaluate(evaluateGiveParam);
    }
}