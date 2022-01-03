package org.unknown100name.ecommerce.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.ItemMapper;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemDetailDTO;
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

    
}
