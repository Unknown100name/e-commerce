package org.unknown100name.ecommerce.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommerce.aspect.token.TokenAuth;
import org.unknown100name.ecommerce.pojo.vo.ItemCreateParam;
import org.unknown100name.ecommerce.service.ItemService;
import org.unknown100name.ecommerce.service.OrderService;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @description 
 * @since 2022/1/2
 */
@RestController
@RequestMapping("seller")
public class SellerController {

    @Resource
    private ItemService itemService;

    @Resource
    private OrderService orderService;

    /**
     * 创建商品
     */
    @PostMapping("/item/create")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemCreate(ItemCreateParam itemCreateParam){
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
    @PostMapping("/item/upload")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemUpload(String itemId){
        return itemService.upload(Long.parseLong(itemId));
    }

    /**
     * 上架
     */
    @PostMapping("/item/up")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemUp(String itemId){
        return itemService.up(Long.parseLong(itemId));
    }

    /**
     * 下架
     */
    @PostMapping("/item/down")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemDown(String itemId){
        return itemService.down(Long.parseLong(itemId));
    }

    /**
     * 删除
     */
    @PostMapping("/item/delete")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> itemDelete(String itemId){
        return itemService.delete(Long.parseLong(itemId));
    }

    /**
     * 买家发货
     * @param innerOrderId
     * @return
     */
    @PostMapping("/order/send")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> orderSend(String userId, String innerOrderId){
        return orderService.send(Long.parseLong(innerOrderId));
    }

    /**
     * 卖家取消订单
     * @param innerOrderId
     * @return
     */
    @PostMapping("/order/cancel")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> orderCancel(String userId, String innerOrderId){
        return orderService.sellerCancel(Long.parseLong(innerOrderId));
    }

}
