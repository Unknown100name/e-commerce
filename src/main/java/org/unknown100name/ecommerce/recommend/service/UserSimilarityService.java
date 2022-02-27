package org.unknown100name.ecommerce.recommend.service;

import org.unknown100name.ecommerce.recommend.pojo.entity.UserSimilarity;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 *
 * @description 对用户之间的相似度进行增删改查的服务接口
 */
public interface UserSimilarityService {

	/**
	 * 新增用户相似度数据
	 * @param userSimilarity
	 * @return true则新增用户相似度成功，false则失败
	 */
	boolean saveUserSimilarity(UserSimilarity userSimilarity);
	
	/**
	 * 更新用户相似度数据
	 * @param userSimilarity
	 * @return 受影响到的行数，0表示影响0行，1表示影响1行
	 */
	boolean updateUserSimilarity(UserSimilarity userSimilarity);
	
	/**
	 * 判断某两个用户之间的相似度是否已经存在
	 * @param userSimilarity
	 * @return true表示已经存在，false表示不存在
	 */
	boolean isExistsUserSimilarity(UserSimilarity userSimilarity);
	
	/**
	 * 查询某个用户与其他用户之间的相似度列表
	 * @param userId
	 * @return 该用户与其他用户的相似度列表
	 */
	List<UserSimilarity> getUserSimilarityByUserId(Long userId);
	
}
