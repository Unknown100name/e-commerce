package org.unknown100name.ecommercerecommend.service.impl;

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
		int rows = userActivityMapper.countUserActivity(userActivity);
		if (rows < 1) {
			userActivity.setHits(1L);
			userActivityMapper.insert(userActivity);
		} else { // 已经存在
			userActivity.setHits(userActivityMapper.getHitsByUserActivityInfo(userActivity) + 1L);
			userActivityMapper.updateById(userActivity);
		}
	}
	
}
