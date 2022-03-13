package org.unknown100name.ecommercerecommend.util;

import org.unknown100name.ecommercerecommend.config.ApplicationContextHolder;
import org.unknown100name.ecommercerecommend.dao.UserActivityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;
import org.unknown100name.ecommercerecommend.service.UserSimilarityService;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author unknown100name
 * @since 2022.03.13
 *
 * @description 找到推荐的东西
 */
public class RecommendGetTask implements Callable<List<Long>> {

    private final UserSimilarityService userSimilarityService;

    private final UserActivityMapper userActivityMapper;

    private final long userId;

    public RecommendGetTask(long userId) {
        this.userId = userId;
        userActivityMapper = ApplicationContextHolder.getContext().getBean(UserActivityMapper.class);
        userSimilarityService = ApplicationContextHolder.getContext().getBean(UserSimilarityService.class);
    }

    @Override
    public List<Long> call() {
        try {
            // 找到当前用户与其他用户的相似度列表
            List<UserSimilarity> userSimilarityList = userSimilarityService.getUserSimilarityByUserId(userId);

            // 找到与当前用户相似度最高的topN个用户
            List<Long> userIds = RecommendUtils.getSimilarityBetweenUsers(userId, userSimilarityList);

            // 找到应该推荐给用户的二级类目的id
            List<UserActivity> userActivityList = userActivityMapper.listAllUserActivity();
            return RecommendUtils.getRecommendCategoryTwo(userId, userIds, userActivityList);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
