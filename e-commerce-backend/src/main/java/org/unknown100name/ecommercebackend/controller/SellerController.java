package org.unknown100name.ecommercebackend.controller;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.unknown100name.ecommercebackend.aspect.token.TokenAuth;
import org.unknown100name.ecommercebackend.pojo.vo.ItemCreateParam;
import org.unknown100name.ecommercebackend.service.ItemService;
import org.unknown100name.ecommercebackend.service.OrderService;
import common.BaseResult;

/**
 * @author unknown100name
 * @description 
 * @since 2022/1/2
 */
@RestController
@RequestMapping("seller")
@Api(tags = "卖家使用相关接口")
public class SellerController {

    @Resource
    private ItemService itemService;

    @Resource
    private OrderService orderService;

    /**
     * 创建商品
     */
    @ApiOperation(value = "创建商品")
    @PostMapping("/item/create")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemCreate(String userId, @RequestBody ItemCreateParam itemCreateParam){
        itemCreateParam.setUserId(Long.parseLong(userId));
        return itemService.create(itemCreateParam);
    }

    // /**
    //  * 更新商品
    //  */
    // @PostMapping("/item/update")
    // @ResponseBody
    // @TokenAuth
    // public BaseResult<String> itemUpdate(ItemCreateParam itemCreateParam){
    //     return itemService.update(itemCreateParam);
    // }
    
    /**
     * 上传审核
     */
    @ApiOperation(value = "上传审核")
    @PostMapping("/item/upload")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemUpload(String userId, String itemId){
        return itemService.upload(Long.parseLong(itemId));
    }

    /**
     * 上架
     */
    @ApiOperation(value = "上架")
    @PostMapping("/item/up")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemUp(String userId, String itemId){
        return itemService.up(Long.parseLong(itemId));
    }

    /**
     * 下架
     */
    @ApiOperation(value = "下架")
    @PostMapping("/item/down")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemDown(String userId, String itemId){
        return itemService.down(Long.parseLong(itemId));
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @PostMapping("/item/delete")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemDelete(String userId, String itemId){
        return itemService.delete(Long.parseLong(itemId));
    }

    /**
     * 卖家发货
     * @param innerOrderId
     * @return
     */
    @ApiOperation(value = "卖家发货")
    @PostMapping("/order/send")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> orderSend(String userId, String innerOrderId, String expressCode){
        return orderService.send(Long.parseLong(innerOrderId), expressCode);
    }

    /**
     * 卖家取消订单
     * @param innerOrderId
     * @return
     */
    @ApiOperation(value = "卖家取消订单")
    @PostMapping("/order/cancel")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> orderCancel(String userId, String innerOrderId){
        return orderService.sellerCancel(Long.parseLong(innerOrderId));
    }

}
