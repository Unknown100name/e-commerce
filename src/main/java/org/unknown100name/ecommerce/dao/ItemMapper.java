package org.unknown100name.ecommerce.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemDetailDTO;

/**
 * @author unknown100name
 * @description 商品数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface ItemMapper {

    /**
     * 根据关键词查询
     * @param keyword
     * @return
     */
    List<ItemBaseDTO> getItemBaseByKeyword(String keyword);

    /**
     * 根据商店 Id 查询
     * @param shopId
     * @return
     */
    List<ItemBaseDTO> getItemBaseByShopId(Long shopId);

    /**
     * 根据商品查询
     * @param itemId
     * @return
     */
    ItemBaseDTO getItemBaseById(Long itemId);
    
    /**
     * 根据商品查询
     * @param itemId
     * @return
     */
    ItemDetailDTO getItemDetailById(Long itemId);
    
}
