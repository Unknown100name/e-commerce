package org.unknown100name.ecommercerecommend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.unknown100name.ecommercerecommend.dao.UserActivityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;
import org.unknown100name.ecommercerecommend.service.UserSimilarityService;
import org.unknown100name.ecommercerecommend.util.RecommendUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author unknown100name
 * @since 2022.03.07
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ECommerceRecommendApplication.class)
public class CalculateSimilarityUserTest {

    @Resource
    private UserActivityMapper userActivityMapper;

    @Resource
    private UserSimilarityService userSimilarityService;

    @Test
    public void test(){
        // 1.查询所有的用户浏览记录
        List<UserActivity> userActivityList = userActivityMapper.listAllUserActivity();

        // 2.调用推荐模块工具类的方法组装成一个ConcurrentHashMap来存储每个用户以及其对应的二级类目的点击量
        ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> activeMap = RecommendUtils.assembleUserBehavior(userActivityList);

        // 3.调用推荐模块工具类的方法计算用户与用户之间的相似度
        List<UserSimilarity> similarityList = RecommendUtils.calculateSimilarityUser(activeMap);

        similarityList.forEach(
                userSimilarity -> userSimilarityService.saveUserSimilarity(userSimilarity)
        );
    }
}
