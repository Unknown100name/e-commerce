package org.unknown100name.ecommercerecommend.database;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.unknown100name.ecommercerecommend.ECommerceRecommendApplication;
import org.unknown100name.ecommercerecommend.dao.UserActivityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author unknown100name
 * @since 2022.03.15
 */
@SpringBootTest(classes = ECommerceRecommendApplication.class)
public class UserActivityRandomTest {

    @Resource
    private UserActivityMapper userActivityMapper;

    private final Random random = new Random();

    @Test
    public void test(){
        List<UserActivity> userActivityList = userActivityMapper.selectList(new QueryWrapper<>());
        userActivityList.forEach(
                userActivity -> {
                    userActivity.setHits((long) random.nextInt(100));
                    userActivityMapper.update(userActivity, new UpdateWrapper<UserActivity>()
                            .eq("user_id", userActivity.getUserId())
                            .eq("category_two_id", userActivity.getCategoryTwoId()));
                }

        );
    }

}
