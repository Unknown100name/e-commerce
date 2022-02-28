package org.unknown100name.ecommerce.recommend.service.impl;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.recommend.RecommendUtils;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserActivity;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserSimilarity;
import org.unknown100name.ecommerce.recommend.service.RecommendService;
import org.unknown100name.ecommerce.recommend.service.UserActivityService;
import org.unknown100name.ecommerce.recommend.service.UserSimilarityService;
import org.unknown100name.ecommerce.service.ItemService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Resource
    private UserActivityService userActivityService;

    @Resource
    private UserSimilarityService userSimilarityService;

    @Resource
    private ItemService itemService;

    @Override
    public List<ItemBaseDTO> getRandomItem() {
         return itemService.getItemByRandom();
    }

    @Override
    public List<ItemBaseDTO> getSimilarityItem(Long userId) {
        // 找到当前用户与其他用户的相似度列表
        List<UserSimilarity> userSimilarityList = userSimilarityService.getUserSimilarityByUserId(userId);

        // 找到与当前用户相似度最高的topN个用户
        List<Long> userIds = RecommendUtils.getSimilarityBetweenUsers(userId, userSimilarityList);

        // 找到应该推荐给用户的二级类目的id
        List<UserActivity> userActivityList = userActivityService.listAllUserActive();
        List<Long> categoryTwoList = RecommendUtils.getRecommendCategoryTwo(userId, userIds, userActivityList);

        // 找到这些二级类目下销量最大的商品
        List<ItemBaseDTO> recommendProducts = new ArrayList<>();
        categoryTwoList.forEach(
                categoryTwoId -> recommendProducts.add(itemService.getMaxSellItemByCategoryTwoId(categoryTwoId))
        );

        return recommendProducts;
    }
}
