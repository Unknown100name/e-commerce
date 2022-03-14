package org.unknown100name.ecommercerecommend.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.unknown100name.ecommercerecommend.config.ApplicationContextHolder;
import org.unknown100name.ecommercerecommend.dao.UserActivityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;
import org.unknown100name.ecommercerecommend.service.RedisService;
import org.unknown100name.ecommercerecommend.service.UserSimilarityService;
import org.unknown100name.ecommercerecommend.util.RecommendUtils;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import static common.ConstUtil.REDIS_PREFIX_CAL_SIMILARITY;

/**
 * @author unknown100name
 * @since 2022.03.14
 *
 * @description 更新 UserSimilarity
 */
public class RecommendCalTask implements Callable<Boolean> {

    private final UserSimilarityService userSimilarityService;

    private final UserActivityMapper userActivityMapper;

    private final RedisService redisService;

    private final long userId;

    public RecommendCalTask(long userId) {
        this.userId = userId;
        userActivityMapper = ApplicationContextHolder.getContext().getBean(UserActivityMapper.class);
        userSimilarityService = ApplicationContextHolder.getContext().getBean(UserSimilarityService.class);
        redisService = ApplicationContextHolder.getContext().getBean(RedisService.class);
    }

    @Override
    public Boolean call() {
        try {
            // 1.查询所有的用户浏览记录
            List<UserActivity> userActivityList = userActivityMapper.selectList(new QueryWrapper<>());

            // 2.调用推荐模块工具类的方法组装成一个ConcurrentHashMap来存储每个用户以及其对应的二级类目的点击量
            ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> activeMap = RecommendUtils.assembleUserBehavior(userActivityList);

            // 3.调用推荐模块工具类的方法计算用户与用户之间的相似度
            List<UserSimilarity> similarityList = RecommendUtils.calculateSimilarityUser(userId, activeMap);

            similarityList.forEach(userSimilarityService::saveUserSimilarity);

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            redisService.remove(REDIS_PREFIX_CAL_SIMILARITY + userId);
        }
    }
}
