package org.unknown100name.ecommercerecommend.service.impl;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommercerecommend.RecommendUtils;
import org.unknown100name.ecommercerecommend.dao.UserActivityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;
import org.unknown100name.ecommercerecommend.service.RecommendService;
import org.unknown100name.ecommercerecommend.service.UserSimilarityService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Service
public class RecommendServiceImpl implements RecommendService {
    @Resource
    private UserActivityMapper userActivityMapper;

    @Resource
    private UserSimilarityService userSimilarityService;

    @Override
    public List<Long> getRecommendCategoryTwoId(Long userId) {
        // 找到当前用户与其他用户的相似度列表
        List<UserSimilarity> userSimilarityList = userSimilarityService.getUserSimilarityByUserId(userId);

        // 找到与当前用户相似度最高的topN个用户
        List<Long> userIds = RecommendUtils.getSimilarityBetweenUsers(userId, userSimilarityList);

        // 找到应该推荐给用户的二级类目的id
        List<UserActivity> userActivityList = userActivityMapper.listAllUserActivity();
        return RecommendUtils.getRecommendCategoryTwo(userId, userIds, userActivityList);
    }
}
