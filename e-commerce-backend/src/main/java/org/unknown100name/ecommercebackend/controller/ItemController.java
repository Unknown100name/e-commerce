package org.unknown100name.ecommercebackend.controller;

import java.util.List;

import javax.annotation.Resource;

import common.BaseResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommercebackend.aspect.activity.ActivityFiled;
import org.unknown100name.ecommercebackend.aspect.activity.ActivityRecord;
import org.unknown100name.ecommercebackend.aspect.activity.ActivitySource;
import org.unknown100name.ecommercebackend.pojo.dto.CategoryArrayDTO;
import org.unknown100name.ecommercebackend.pojo.dto.CategoryDTO;
import org.unknown100name.ecommercebackend.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommercebackend.pojo.dto.ItemDetailDTO;
import org.unknown100name.ecommercebackend.service.ItemService;
import common.BaseResult;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/2
 */
@RestController
@RequestMapping("item")
@Api(tags = "商品相关接口")
public class ItemController {

    @Resource
    private ItemService itemService;

    @ApiOperation(value = "获取目录（商家，数组返回）")
    @GetMapping("getCategoryForSeller")
    @ResponseBody
    public BaseResult<CategoryArrayDTO> getCategoryForSeller(){
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, new CategoryArrayDTO(itemService.getCategory().getData()));
    }

    @ApiOperation(value = "获取目录（买家，列表返回）")
    @GetMapping("getCategoryForBuyer")
    @ResponseBody
    public BaseResult<List<CategoryDTO>> getCategoryForBuyer(){
        return itemService.getCategory();
    }

    @ApiOperation(value = "根据一级目录ID获取商品")
    @GetMapping("getByCategoryOne")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryOneId", value = "一级目录 ID", paramType = "header", dataTypeClass = String.class)})
    public BaseResult<List<ItemBaseDTO>> getByCategoryOne(String categoryOneId){
        return itemService.getByCategoryOne(Long.parseLong(categoryOneId));
    }

    @ApiOperation(value = "根据二级目录ID获取商品")
    @GetMapping("getByCategoryTwo")
    @ResponseBody
    @ActivityRecord
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户 ID", paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "categoryTwoId", value = "二级目录 ID", paramType = "header", dataTypeClass = String.class)})
    public BaseResult<List<ItemBaseDTO>> getByCategoryTwo(String userId, @ActivityFiled(source = ActivitySource.CATEGORY_TWO_ID) String categoryTwoId){
        return itemService.getByCategoryTwo(Long.parseLong(categoryTwoId));
    }

    /**
     * 根据关键词查询商品
     * @param keyword
     * @return
     */
    @ApiOperation(value = "根据关键词查询商品")
    @GetMapping("searchList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "检索关键词", paramType = "header", dataTypeClass = String.class)})
    public BaseResult<List<ItemBaseDTO>> searchList(String keyword){
        return itemService.searchList(keyword);
    }

    /**
     * 查询商店商品
     * @param shopId
     * @return
     */
    @ApiOperation(value = "根据商店的userId商品")
    @GetMapping("shopList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId", value = "商家用户 ID", paramType = "header", dataTypeClass = String.class)})
    public BaseResult<List<ItemBaseDTO>> shopList(String shopId){
        return itemService.shopList(Long.parseLong(shopId));
    }

    /**
     * 查询商品基础信息
     * @param itemId
     * @return
     */
    @ApiOperation(value = "查询商品基础信息")
    @GetMapping("base")
    @ResponseBody
    @ActivityRecord
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户 ID", paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "itemId", value = "商品 ID", paramType = "header", dataTypeClass = String.class)})
    public BaseResult<ItemBaseDTO> base(String userId, @ActivityFiled(source = ActivitySource.ITEM_ID)String itemId){
        return itemService.base(Long.parseLong(itemId));
    }

    /**
     * 查询商品详细信息
     * @param itemId
     * @return
     */
    @ApiOperation(value = "查询商品详细信息")
    @GetMapping("detail")
    @ResponseBody
    @ActivityRecord
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户 ID", paramType = "header", dataTypeClass = String.class),
            @ApiImplicitParam(name = "itemId", value = "商品 ID", paramType = "header", dataTypeClass = String.class)})
    public BaseResult<ItemDetailDTO> detail(String userId, @ActivityFiled(source = ActivitySource.ITEM_ID)String itemId){
        return itemService.detail(Long.parseLong(itemId));
    }
}
