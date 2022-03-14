package org.unknown100name.ecommercerecommend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entity.CategoryTwo;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivity> {
	
	/**
	 * 按照 userIdList 查询出所有的用户行为
	 * @param userIdList 用户 IdList
	 * @return 返回用户的行为数据
	 */
	List<UserActivity> listUserActivityByUserIdList(List<Long> userIdList);

	/**
	 * 注册一个新的用户
	 * @param userId
	 */
    void registerNewUser(Long userId, List<CategoryTwo> categoryTwoIds);
}
