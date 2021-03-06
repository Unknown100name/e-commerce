package org.unknown100name.ecommercerecommend.service;

import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;

/**
 * @author unknown100name
 * @since 2022.02.27
 *
 * @description 用户的行为数据的服务类
 */
public interface UserActivityService {
	
	/**
	 * 保存用户的浏览记录，如果用户的浏览记录存在则更新，不存在则添加
	 * @param userActivity
	 */
	void saveUserActivity(UserActivity userActivity);
	
}
