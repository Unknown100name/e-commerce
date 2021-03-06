package org.unknown100name.ecommercebackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unknown100name.ecommercebackend.dao.CategoryMapper;
import org.unknown100name.ecommercebackend.dao.ItemImageMapper;
import org.unknown100name.ecommercebackend.dao.ItemMapper;
import org.unknown100name.ecommercebackend.dao.OrderMapper;
import org.unknown100name.ecommercebackend.pojo.dto.*;
import org.unknown100name.ecommercebackend.pojo.entity.InnerItem;
import org.unknown100name.ecommercebackend.pojo.entity.Item;
import org.unknown100name.ecommercebackend.pojo.entity.ItemImage;
import org.unknown100name.ecommercebackend.pojo.vo.ItemCreateParam;
import org.unknown100name.ecommercebackend.service.ItemService;
import common.BaseResult;
import common.BaseResultMsg;

/**
 * @author unknown100name
 * @since 2022/1/3
 * @description 
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private ItemImageMapper itemImageMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public BaseResult<List<CategoryDTO>> getCategory() {
        List<CategoryDTO> category = categoryMapper.getCategoryList();
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, category);
    }

    @Override
    public BaseResult<List<ItemBaseDTO>> getByCategoryOne(Long categoryOneId) {
        List<ItemBaseDTO> itemBaseList = itemMapper.getItemBaseByCategoryOne(categoryOneId);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, itemBaseList);
    }

    @Override
    public BaseResult<List<ItemBaseDTO>> getByCategoryTwo(Long categoryTwoId) {
        List<ItemBaseDTO> itemBaseList = itemMapper.getItemBaseByCategoryTwo(categoryTwoId);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, itemBaseList);
    }

    @Override
    public BaseResult<List<ItemBaseDTO>> searchList(String keyword) {
        List<ItemBaseDTO> itemBaseList = itemMapper.getItemBaseByKeyword(keyword);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, itemBaseList);
    }

    @Override
    public BaseResult<List<ItemBaseDTO>> shopList(Long shopId) {
        List<ItemBaseDTO> itemBaseList = itemMapper.getItemBaseByShopId(shopId);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, itemBaseList);
    }

    @Override
    public BaseResult<ItemBaseDTO> base(Long itemId) {
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, itemMapper.getItemBaseById(itemId));
    }

    @Override
    public BaseResult<ItemDetailDTO> detail(Long itemId) {
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, itemMapper.getItemDetailById(itemId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult<String> create(ItemCreateParam itemCreateParam) {
        Item insertItem = new Item(itemCreateParam);
        List<InnerItem> innerItemList = new ArrayList<>();
        List<ItemImage> imageList = new ArrayList<>();

        // InnerItem
        itemCreateParam.getInnerItemCreateParam().forEach(
            innerItemCreateParam -> {
                InnerItem insertInnerItem = new InnerItem(insertItem, innerItemCreateParam);
                innerItemList.add(insertInnerItem);
                // ???????????????
                imageList.add(new ItemImage(innerItemCreateParam.getImageBase64(), insertInnerItem.getId(), 2, 0));
            }
        );

        // ??????
        imageList.add(new ItemImage(itemCreateParam.getMainImageBase64(), insertItem.getId(), 0, 0));
        // ??????
        for (int order = 0; order < itemCreateParam.getImageListBase64().size(); order++) {
            String image = itemCreateParam.getImageListBase64().get(order);
            imageList.add(new ItemImage(image, insertItem.getId(), 1, order));
        }

        // Mapper
        itemMapper.insert(insertItem);
        itemMapper.insertInnerItem(innerItemList);
        itemImageMapper.insertItemImage(imageList);

        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

     @Override
     @Transactional(rollbackFor = Exception.class)
     public BaseResult<String> update(ItemCreateParam itemCreateParam) {
         Item updateItem = new Item(itemCreateParam);
         updateItem.setId(itemCreateParam.getId());
         List<ItemImage> imageList = new ArrayList<>();

         // ??????????????? innerItem??????????????? innerItem????????? id ????????????????????? id
         List<InnerItem> newInnerItemList = new ArrayList<>();
         itemCreateParam.getInnerItemCreateParam().forEach(
                 innerItemCreateParam -> {
                     InnerItem newInnerItem = new InnerItem(updateItem, innerItemCreateParam);
                     if (innerItemCreateParam.getId() != null){
                         newInnerItem.setId(innerItemCreateParam.getId());
                     }
                     newInnerItemList.add(newInnerItem);
                     // ???????????????
                     imageList.add(new ItemImage(innerItemCreateParam.getImageBase64(), newInnerItem.getId(), 2, 0));
                 }
         );

         // ??????
         imageList.add(new ItemImage(itemCreateParam.getMainImageBase64(), updateItem.getId(), 0, 0));
         // ??????
         for (int order = 0; order < itemCreateParam.getImageListBase64().size(); order++) {
             String image = itemCreateParam.getImageListBase64().get(order);
             imageList.add(new ItemImage(image, updateItem.getId(), 1, order));
         }

         // Mapper ????????????
         itemMapper.deleteInnerItemByItemId(updateItem.getId());
         itemImageMapper.deleteByItemId(updateItem.getId());

         itemMapper.updateById(updateItem);
         itemMapper.insertInnerItem(newInnerItemList);
         itemImageMapper.insertItemImage(imageList);

         return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
     }

    @Override
    public BaseResult<String> upload(Long itemId) {
        int result = itemMapper.updateItemState(itemId, 0, 2);
        if(result == 0){
            return BaseResult.failResult(BaseResultMsg.ERROR_STATUS);
        }
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null); 
    }

    @Override
    public BaseResult<String> up(Long itemId) {
        int result = 0;
        result += itemMapper.updateItemState(itemId, 2, 3);
        result += itemMapper.updateItemState(itemId, 4, 3);
        result += itemMapper.updateItemState(itemId, 5, 3);
        if(result == 0){
            return BaseResult.failResult(BaseResultMsg.ERROR_STATUS);
        }
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> down(Long itemId) {
        boolean exist = orderMapper.existInnerOrderNotFinish(itemId);
        // ???????????? ?????? true ????????????????????????, ?????? order ??????
        int result;
        if(exist){
            result = itemMapper.updateItemState(itemId, 3, 4);
        }else{
            result = itemMapper.updateItemState(itemId, 3, 5);
        }
        if(result == 0){
            return BaseResult.failResult(BaseResultMsg.ERROR_STATUS);
        }
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> delete(Long itemId) {
        int result = 0;
        result += itemMapper.updateItemState(itemId, 0, 6);
        result += itemMapper.updateItemState(itemId, 1, 6);
        result += itemMapper.updateItemState(itemId, 2, 6);
        result += itemMapper.updateItemState(itemId, 5, 6);
        if(result == 0){
            return BaseResult.failResult(BaseResultMsg.ERROR_STATUS);
        }
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public ItemBaseDTO getMaxSellItemByCategoryTwoId(Long categoryTwoId) {
        return itemMapper.getMaxSellItemByCategoryTwoId(categoryTwoId);
    }

    @Override
    public List<ItemBaseDTO> getItemByRandom() {
        return itemMapper.getItemByRandom();
    }

}
