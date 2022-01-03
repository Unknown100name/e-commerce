package org.unknown100name.ecommerce.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemDetailDTO;
import org.unknown100name.ecommerce.pojo.vo.EvaluateGiveParam;
import org.unknown100name.ecommerce.service.EvaluateService;
import org.unknown100name.ecommerce.service.ItemService;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/2
 */
@RestController
@RequestMapping("item")
public class ItemController {

    @Resource
    private ItemService itemService;

    @Resource
    private EvaluateService evaluateService;

    /**
     * 查询列表商品
     * @param keyword
     * @return
     */
    @GetMapping("searchList")
    @ResponseBody
    public BaseResult<List<ItemBaseDTO>> searchList(String keyword){
        return itemService.searchList(keyword);
    }

    /**
     * 查询商店商品
     * @param shopId
     * @return
     */
    @GetMapping("shopList")
    @ResponseBody
    public BaseResult<List<ItemBaseDTO>> shopList(String shopId){
        return itemService.shopList(Long.parseLong(shopId));
    }

    /**
     * 查询商品基础信息
     * @param itemId
     * @return
     */
    @GetMapping("base")
    @ResponseBody
    public BaseResult<ItemBaseDTO> base(String itemId){
        return itemService.base(Long.parseLong(itemId));
    }

    /**
     * 查询商品详细信息
     * @param itemId
     * @return
     */
    @GetMapping("detail")
    @ResponseBody
    public BaseResult<ItemDetailDTO> detail(String itemId){
        return itemService.detail(Long.parseLong(itemId));
    }

    /**
     * 根据 orderId 获取商品并评论
     * @param orderId
     * @return
     */
    @PostMapping("evaluate")
    @ResponseBody
    public BaseResult<String> evaluate(@RequestBody EvaluateGiveParam evaluateGiveParam){
        return evaluateService.insertEvaluate(evaluateGiveParam);
    }
}
