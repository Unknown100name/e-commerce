package org.unknown100name.ecommercerecommend.service;

import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 *
 * @description 对用户之间的相似度进行增删改查的服务接口
 */
public interface UserSimilarityService {

	/**
	 * 对于 userId 进行更新相关信息
	 * @param userId
	 * @return 是否进行了任务
	 */
	Boolean updateSimilarity(Long userId);

	/**
	 * 新增用户相似度数据
	 * @param userSimilarity
	 * @return true则新增用户相似度成功，false则失败
	 */
	boolean saveUserSimilarity(UserSimilarity userSimilarity);
	
	/**
	 * 查询某个用户与其他用户之间的相似度列表
	 * @param userId
	 * @param defaultRecommendUserNumber
	 * @return 该用户与其他用户的相似度列表
	 */
	List<Long> getTopSimilarityUserByUserId(Long userId, Integer defaultRecommendUserNumber);
}
