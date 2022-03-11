package org.unknown100name.ecommercerecommend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.unknown100name.ecommercerecommend.dao.UserActivityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;

import javax.annotation.Resource;

/**
 * @author unknown100name
 * @since 2022.03.08
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ECommerceRecommendApplication.class)
public class UserActivityBackupTest {

    @Resource
    private UserActivityMapper userActivityMapper;

    @Test
    public void test(){
        for (long userId = 21622007846141952L; userId <= 21622007846141954L ; userId++) {
            for (long categoryId = 1L; categoryId <=120L ; categoryId++) {
                userActivityMapper.insert(new UserActivity(userId, categoryId, 0L));
            }
        }
    }

}
