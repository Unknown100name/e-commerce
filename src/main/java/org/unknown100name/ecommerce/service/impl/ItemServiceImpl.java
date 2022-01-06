package org.unknown100name.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.ItemMapper;
import org.unknown100name.ecommerce.dao.OrderMapper;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemDetailDTO;
import org.unknown100name.ecommerce.pojo.dto.OrderDTO;
import org.unknown100name.ecommerce.pojo.entity.InnerItem;
import org.unknown100name.ecommerce.pojo.entity.InnerOrder;
import org.unknown100name.ecommerce.pojo.entity.Item;
import org.unknown100name.ecommerce.pojo.vo.ItemCreateParam;
import org.unknown100name.ecommerce.service.ItemService;
import org.unknown100name.ecommerce.util.BaseResult;

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

    @Override
    public BaseResult<List<ItemBaseDTO>> searchList(String keyword) {
        List<ItemBaseDTO> itemBaseList = itemMapper.getItemBaseByKeyword(keyword);
        return BaseResult.successResult(null, itemBaseList);
    }

    @Override
    public BaseResult<List<ItemBaseDTO>> shopList(Long shopId) {
        List<ItemBaseDTO> itemBaseList = itemMapper.getItemBaseByShopId(shopId);
        return BaseResult.successResult(null, itemBaseList);
    }

    @Override
    public BaseResult<ItemBaseDTO> base(Long itemId) {
        return BaseResult.successResult(null, itemMapper.getItemBaseById(itemId));
    }

    @Override
    public BaseResult<ItemDetailDTO> detail(Long itemId) {
        return BaseResult.successResult(null, itemMapper.getItemDetailById(itemId));
    }

    @Override
    public BaseResult<?> create(ItemCreateParam itemCreateParam) {
        Item insertItem = new Item(itemCreateParam);
        List<InnerItem> innerItemList = new ArrayList<>();
        itemCreateParam.getInnerItemCreateParam().forEach(
            innerItemCreateParam -> innerItemList.add(new InnerItem(insertItem, innerItemCreateParam))
        );
        itemMapper.insertItem(insertItem);
        itemMapper.insertInnerItem(innerItemList);
        return BaseResult.successResult(null, null);
    }

    // @Override
    // public BaseResult<?> update(ItemCreateParam itemCreateParam) {
    //     return null;
    // }

    @Override
    public BaseResult<?> upload(Long itemId) {
        itemMapper.updateItemState(itemId, 0, 1);
        return BaseResult.successResult(null, null); 
    }

    @Override
    public BaseResult<?> up(Long itemId) {
        itemMapper.updateItemState(itemId, 2, 3);
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> down(Long itemId) {
        Boolean exist = orderMapper.existInnerOrderNotFinish(itemId);
        if(exist){
            itemMapper.updateItemState(itemId, 3, 5);
        }else{
            itemMapper.updateItemState(itemId, 3, 4);
        }
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> delete(Long itemId) {
        itemMapper.updateItemState(itemId, 0, 6);
        itemMapper.updateItemState(itemId, 1, 6);
        itemMapper.updateItemState(itemId, 2, 6);
        itemMapper.updateItemState(itemId, 5, 6);
        return BaseResult.successResult(null, null);
    }
    
}
