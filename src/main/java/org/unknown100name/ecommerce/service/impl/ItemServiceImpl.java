package org.unknown100name.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.CategoryMapper;
import org.unknown100name.ecommerce.dao.ItemMapper;
import org.unknown100name.ecommerce.dao.OrderMapper;
import org.unknown100name.ecommerce.pojo.dto.CategoryDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemDetailDTO;
import org.unknown100name.ecommerce.pojo.entity.InnerItem;
import org.unknown100name.ecommerce.pojo.entity.Item;
import org.unknown100name.ecommerce.pojo.vo.ItemCreateParam;
import org.unknown100name.ecommerce.service.ItemService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;

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
    public BaseResult<String> create(ItemCreateParam itemCreateParam) {
        Item insertItem = new Item(itemCreateParam);
        List<InnerItem> innerItemList = new ArrayList<>();
        itemCreateParam.getInnerItemCreateParam().forEach(
            innerItemCreateParam -> innerItemList.add(new InnerItem(insertItem, innerItemCreateParam))
        );
        itemMapper.insert(insertItem);
        itemMapper.insertInnerItem(innerItemList);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    // @Override
    // public BaseResult<String> update(ItemCreateParam itemCreateParam) {
    //     return null;
    // }

    @Override
    public BaseResult<String> upload(Long itemId) {
        itemMapper.updateItemState(itemId, 0, 1);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null); 
    }

    @Override
    public BaseResult<String> up(Long itemId) {
        itemMapper.updateItemState(itemId, 2, 3);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> down(Long itemId) {
        boolean exist = orderMapper.existInnerOrderNotFinish(itemId);
        if(exist){
            itemMapper.updateItemState(itemId, 3, 5);
        }else{
            itemMapper.updateItemState(itemId, 3, 4);
        }
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> delete(Long itemId) {
        itemMapper.updateItemState(itemId, 0, 6);
        itemMapper.updateItemState(itemId, 1, 6);
        itemMapper.updateItemState(itemId, 2, 6);
        itemMapper.updateItemState(itemId, 5, 6);
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
