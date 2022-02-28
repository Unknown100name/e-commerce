package org.unknown100name.ecommerce.recommend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserActivity;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Mapper
public interface UserActivityMapper {
	
	/**
	 * 查询出所有的用户行为
	 * @return 返回用户的行为数据
	 */
	List<UserActivity> listAllUserActivity();
	
	/**
	 * 根据用户已有的行为信息获取它对某个二级类目的点击量
	 * @param userActivity 用户的行为数据
	 * @return 某个用户对某个二级类目的点击量
	 */
	int getHitsByUserActivityInfo(UserActivity userActivity);
	
	/**
	 * 统计某个用户的行为记录的条数
	 * @param userActivity
	 * @return 1就说明存在这个用户的行为，0说明不存在
	 */
	int countUserActivity(UserActivity userActivity);
	
	/**
	 * 向用户行为表中添加一条用户的行为记录
	 * @param userActivity
	 * @return 受影响的行数，1表示插入成功，0表示插入失败
	 */
	int saveUserActivity(UserActivity userActivity);
	
	/**
	 * 更新用户对某个二级类目的点击量
	 * @param userActivity
	 * @return 1表示更新成功，0表示更新失败
	 */
	int updateUserActivity(UserActivity userActivity);
	
}
