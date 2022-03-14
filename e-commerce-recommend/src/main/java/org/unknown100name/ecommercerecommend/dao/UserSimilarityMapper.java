package org.unknown100name.ecommercerecommend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Mapper
public interface UserSimilarityMapper extends BaseMapper<UserSimilarity> {
	
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
	Boolean containUserSimilarity(UserSimilarity userSimilarity);

	/**
	 * 获取与该用户最相似的几个用户的 userId
	 * @param userId 基准用户
	 * @param defaultRecommendUserNumber 推荐的用户个数
	 * @return
	 */
	List<Long> getTopSimilarityUserByUserId(Long userId, Integer defaultRecommendUserNumber);
}
