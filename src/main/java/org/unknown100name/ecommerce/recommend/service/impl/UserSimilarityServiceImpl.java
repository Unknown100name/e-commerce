package org.unknown100name.ecommerce.recommend.service.impl;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.recommend.dao.UserSimilarityMapper;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserSimilarity;
import org.unknown100name.ecommerce.recommend.service.UserSimilarityService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Service
public class UserSimilarityServiceImpl implements UserSimilarityService {

	@Resource
	private UserSimilarityMapper userSimilarityMapper;

	@Override
	public boolean saveUserSimilarity(UserSimilarity userSimilarity) {
		userSimilarityMapper.saveUserSimilarity(userSimilarity);
		return true;
	}

	@Override
	public boolean updateUserSimilarity(UserSimilarity userSimilarity) {
		userSimilarityMapper.updateUserSimilarity(userSimilarity);
		return true;
	}

	@Override
	public boolean isExistsUserSimilarity(UserSimilarity userSimilarity) {
		userSimilarityMapper.countUserSimilarity(userSimilarity);
		return true;
	}
	
	@Override
	public List<UserSimilarity> getUserSimilarityByUserId(Long userId) {
		return userSimilarityMapper.getUserSimilarityByUserId(userId);
	}

}
