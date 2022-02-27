package org.unknown100name.ecommerce.recommend.service;

import org.unknown100name.ecommerce.recommend.pojo.entity.UserActivity;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 *
 * @description 用户的行为数据的服务类
 */
public interface UserActivityService {

	/**
	 * 查询出所有的用户行为
	 * @return
	 */
	List<UserActivity> listAllUserActive();
	
	/**
	 * 保存用户的浏览记录，如果用户的浏览记录存在则更新，不存在则添加
	 * @param userActivity
	 * @return true表示更新成功，false表示更新失败
	 */
	boolean saveUserActive(UserActivity userActivity);
	
}
