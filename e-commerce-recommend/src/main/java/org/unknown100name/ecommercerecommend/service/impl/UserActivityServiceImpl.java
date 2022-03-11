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
	public void saveUserActive(UserActivity userActiveDTO) {
		// 1.先判断数据库中是否存在当前用户的浏览记录
		int rows = userActivityMapper.countUserActivity(userActiveDTO);
		int saveRows = 0;
		int updateRows = 0;
		// 2.不存在就添加
		if (rows < 1) { // 不存在
			userActiveDTO.setHits(1L); // 不存在记录的话那肯定是第一次访问，那点击量肯定是1
			userActivityMapper.insert(userActiveDTO);
		} else { // 已经存在
			// 3.存在则先把当前用户对当前二级类目的点击量取出来+1
			long hits = userActivityMapper.getHitsByUserActivityInfo(userActiveDTO);
			// 4.然后在更新用户的浏览记录
			hits++;
			userActiveDTO.setHits(hits);
			updateRows = userActivityMapper.updateById(userActiveDTO);
		}
	}
	
}
