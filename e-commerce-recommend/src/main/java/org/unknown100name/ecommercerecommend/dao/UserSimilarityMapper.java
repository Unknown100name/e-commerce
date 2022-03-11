package org.unknown100name.ecommercerecommend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Mapper
public interface UserSimilarityMapper {

	/**
	 * 新增用户相似度数据
	 * @param userSimilarity
	 * @return 受影响到的行数，0表示影响0行，1表示影响1行
	 */
	int saveUserSimilarity(UserSimilarity userSimilarity);
	
	/**
	 * 更新用户相似度数据
	 * @param userSimilarity
	 * @return 受影响到的行数，0表示影响0行，1表示影响1行
	 */
	int updateUserSimilarity(UserSimilarity userSimilarity);
	
	/**
	 * 判断某两个用户之间的相似度是否已经存在
	 * @param userSimilarity
	 * @return 返回1表示已经存在，返回0表示不存在
	 */
	int countUserSimilarity(UserSimilarity userSimilarity);
	
	/**
	 * 查询某个用户与其他用户之间的相似度列表
	 * @param userId
	 * @return 该用户与其他用户的相似度列表
	 */
	List<UserSimilarity> getUserSimilarityByUserId(Long userId);
	
}
