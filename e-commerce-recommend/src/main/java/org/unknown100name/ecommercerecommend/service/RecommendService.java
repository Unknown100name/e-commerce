package org.unknown100name.ecommercerecommend.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 */
@Service
public interface RecommendService {

    /**
     * 根据用户 ID 获取推荐的二级目录
     * @return
     */
    List<Long> getRecommendCategoryTwoId(Long userId);
}
