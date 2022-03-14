package org.unknown100name.ecommercerecommend.service.impl;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.unknown100name.ecommercerecommend.dao.UserSimilarityMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserSimilarity;
import org.unknown100name.ecommercerecommend.service.RedisService;
import org.unknown100name.ecommercerecommend.service.UserSimilarityService;
import org.unknown100name.ecommercerecommend.task.RecommendCalTask;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.FutureTask;

import static common.ConstUtil.REDIS_PREFIX_CAL_SIMILARITY;


/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Service
public class UserSimilarityServiceImpl implements UserSimilarityService {

	@Resource
	private UserSimilarityMapper userSimilarityMapper;

	@Resource
	private RedisService redisService;

	@Resource(name = "SimilarityUpdater")
	private ThreadPoolTaskExecutor similarityUpdater;

	private final Object similarityLock = new Object();

	@Override
	public Boolean updateSimilarity(Long userId) {
		// 提交任务
		FutureTask<Boolean> recommendCalTask = new FutureTask<>(new RecommendCalTask(userId));

		// 判断现在有没有这个任务
		if(!redisService.hasKey(REDIS_PREFIX_CAL_SIMILARITY + userId)){
			synchronized (similarityLock){
				if (!redisService.hasKey(REDIS_PREFIX_CAL_SIMILARITY + userId)){
					redisService.set(REDIS_PREFIX_CAL_SIMILARITY + userId, "1");
					similarityUpdater.submit(recommendCalTask);
				}else {
					// 如果有这个任务在 那么就不进行这个任务
					return false;
				}
			}
		}else {
			// 如果有这个任务在 那么就不进行这个任务
			return false;
		}
		// 提交之后默认成功直接返回
		return true;
	}

	@Override
	public boolean saveUserSimilarity(UserSimilarity userSimilarity) {
		if (userSimilarityMapper.containUserSimilarity(userSimilarity)){
			userSimilarityMapper.updateUserSimilarity(userSimilarity);
		}else {
			userSimilarityMapper.insert(userSimilarity);
		}
		return true;
	}

	@Override
	public List<Long> getTopSimilarityUserByUserId(Long userId, Integer defaultRecommendUserNumber) {
		return userSimilarityMapper.getTopSimilarityUserByUserId(userId, defaultRecommendUserNumber);
	}



}
