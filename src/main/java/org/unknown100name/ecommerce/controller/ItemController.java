package org.unknown100name.ecommerce.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommerce.aspect.activity.ActivityFiled;
import org.unknown100name.ecommerce.aspect.activity.ActivityRecord;
import org.unknown100name.ecommerce.aspect.activity.ActivitySource;
import org.unknown100name.ecommerce.aspect.token.TokenAuth;
import org.unknown100name.ecommerce.pojo.dto.CategoryDTO;
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

    @GetMapping("getCategory")
    @ResponseBody
    public BaseResult<List<CategoryDTO>> getCategory(){
        return itemService.getCategory();
    }

    @GetMapping("getByCategoryOne")
    @ResponseBody
    public BaseResult<List<ItemBaseDTO>> getByCategoryOne(String userId, String categoryOneId){
        return itemService.getByCategoryOne(Long.parseLong(categoryOneId));
    }

    @GetMapping("getByCategoryTwo")
    @ResponseBody
    @ActivityRecord
    public BaseResult<List<ItemBaseDTO>> getByCategoryTwo(String userId, @ActivityFiled(source = ActivitySource.CATEGORY_TWO_ID) String categoryTwoId){
        return itemService.getByCategoryTwo(Long.parseLong(categoryTwoId));
    }

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
    @ActivityRecord
    public BaseResult<ItemBaseDTO> base(String userId, @ActivityFiled(source = ActivitySource.ITEM_ID)String itemId){
        return itemService.base(Long.parseLong(itemId));
    }

    /**
     * 查询商品详细信息
     * @param itemId
     * @return
     */
    @GetMapping("detail")
    @ResponseBody
    @ActivityRecord
    public BaseResult<ItemDetailDTO> detail(String userId, @ActivityFiled(source = ActivitySource.ITEM_ID)String itemId){
        return itemService.detail(Long.parseLong(itemId));
    }

    /**
     * 根据 orderId 获取商品并评论
     * @param userId
     * @param evaluateGiveParam
     * @return
     */
    @PostMapping("evaluate")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> evaluate(String userId, @RequestBody EvaluateGiveParam evaluateGiveParam){
        return evaluateService.insertEvaluate(evaluateGiveParam);
    }
}
