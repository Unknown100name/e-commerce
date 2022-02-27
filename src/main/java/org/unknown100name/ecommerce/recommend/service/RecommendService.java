package org.unknown100name.ecommerce.recommend.service;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Service
public interface RecommendService {

    /**
     * 没有用户登录的时候随机获取商品
     * @return
     */
    List<ItemBaseDTO> getRandomItem();

    /**
     * 包含用户登录的时候获取商品
     * @return
     */
    List<ItemBaseDTO> getSimilarityItem(Long userId);
}
