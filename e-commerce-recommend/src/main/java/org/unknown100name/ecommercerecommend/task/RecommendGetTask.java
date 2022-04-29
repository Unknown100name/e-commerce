package org.unknown100name.ecommercerecommend.task;

import org.unknown100name.ecommercerecommend.config.ApplicationContextHolder;
import org.unknown100name.ecommercerecommend.dao.UserActivityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.service.UserSimilarityService;
import org.unknown100name.ecommercerecommend.util.RecommendUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import static common.ConstUtil.DEFAULT_RECOMMEND_USER_NUMBER;

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
            List<Long> topSimilarityUserIdList = userSimilarityService.getTopSimilarityUserByUserId(userId, DEFAULT_RECOMMEND_USER_NUMBER);

            // 找到 userId 的点击行为
            List<UserActivity> userActivityList = userActivityMapper.listUserActivityByUserIdList(Collections.singletonList(userId));

            // 找到这些相似用户的点击行为
            if (topSimilarityUserIdList.isEmpty()){
                return null;
            }

            List<UserActivity> similarityUserActivityList = userActivityMapper.listUserActivityByUserIdList(topSimilarityUserIdList);

            // 找到应该推荐给用户的二级类目的id
            return RecommendUtils.getRecommendCategoryTwo(userId, userActivityList, topSimilarityUserIdList, similarityUserActivityList);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
