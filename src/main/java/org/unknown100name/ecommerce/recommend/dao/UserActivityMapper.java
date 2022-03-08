package org.unknown100name.ecommerce.recommend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.entity.CategoryTwo;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserActivity;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivity> {
	
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
	 * 注册一个新的用户
	 * @param userId
	 */
    void registerNewUser(Long userId, List<CategoryTwo> categoryTwoIds);
}
