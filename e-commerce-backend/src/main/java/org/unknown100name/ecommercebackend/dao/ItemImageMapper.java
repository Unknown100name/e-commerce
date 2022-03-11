package org.unknown100name.ecommercebackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercebackend.pojo.entity.ItemImage;

import java.util.List;

/**
 * 商品图片 Mapper
 * @author unknown100name
 * @since 2022.02.24
 */
@Mapper
public interface ItemImageMapper extends BaseMapper<ItemImage> {

    /**
     * 获取商品图册
     * @param itemId
     * @return
     */
    List<String> getItemImagesByItemId(String itemId);

}
