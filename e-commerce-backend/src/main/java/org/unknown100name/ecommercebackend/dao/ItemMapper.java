package org.unknown100name.ecommercebackend.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercebackend.pojo.dto.InnerItemDTO;
import org.unknown100name.ecommercebackend.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommercebackend.pojo.dto.ItemDetailDTO;
import org.unknown100name.ecommercebackend.pojo.entity.InnerItem;
import org.unknown100name.ecommercebackend.pojo.entity.Item;

/**
 * @author unknown100name
 * @description 商品数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

    //-------------------- ITEM --------------------

    /**
     * 根据一级目录查询
     * @param categoryOneId
     * @return
     */
    List<ItemBaseDTO> getItemBaseByCategoryOne(Long categoryOneId);

    /**
     * 根据二级目录查询
     * @param categoryTwoId
     * @return
     */
    List<ItemBaseDTO> getItemBaseByCategoryTwo(Long categoryTwoId);

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

    /**
     * 更新商品状态
     * @param itemId
     * @param from
     * @param to
     */
    int updateItemState(Long itemId, int from, int to);

    /***
     * 增加该商品的销量
     * @param innerItemId
     */
    void increaseSell(Long innerItemId, Integer sellCount);

    /**
     * 增加商品点击量
     * @param itemId
     */
    void increaseHit(Long itemId);

    /**
     * 减少商品的库存
     * @param innerItemId
     * @param deInventory
     */
    void decreaseInventory(Long innerItemId, Integer deInventory);

    /**
     * 通过二级目录查找销量最高的商品
     * @param categoryTwoId
     * @return
     */
    ItemBaseDTO getMaxSellItemByCategoryTwoId(Long categoryTwoId);

    /**
     * 随机获取商品 (10个)
     * @return
     */
    List<ItemBaseDTO> getItemByRandom();

    //-------------------- INNER ITEM --------------------

    /**
     * 获取内部商品
     * @param id
     * @return
     */
    InnerItemDTO getInnerItemById(Long id);

    /**
     * 获取内部商品
     * @param itemId
     * @return
     */
    List<InnerItemDTO> getInnerItemByItemId(Long itemId);

    /**
     * 插入内部商品
     * @param innerItemList
     */
    void insertInnerItem(List<InnerItem> innerItemList);

    /**
     * 删除内部商品
     * @param itemId
     */
    void deleteInnerItemByItemId(Long itemId);
}
