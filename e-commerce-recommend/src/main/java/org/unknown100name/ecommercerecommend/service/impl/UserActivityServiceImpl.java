package org.unknown100name.ecommercerecommend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.unknown100name.ecommercerecommend.dao.UserActivityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.service.UserActivityService;

import javax.annotation.Resource;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Service
public class UserActivityServiceImpl implements UserActivityService {

	@Resource
	private UserActivityMapper userActivityMapper;
	
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveUserActivity(UserActivity userActivity) {
		UserActivity existUserActivity = userActivityMapper.selectOne(new QueryWrapper<UserActivity>()
						.eq("user_id", userActivity.getUserId())
						.eq("category_two_id", userActivity.getCategoryTwoId()));
		if (existUserActivity == null) {
			userActivity.setHits(0L);
			userActivityMapper.insert(userActivity);
		} else { // 已经存在
			userActivity.setHits(existUserActivity.getHits() + 1L);
			userActivityMapper.update(userActivity, new UpdateWrapper<UserActivity>()
					.eq("user_id", userActivity.getUserId())
					.eq("category_two_id", userActivity.getCategoryTwoId()));
		}
	}
	
}
