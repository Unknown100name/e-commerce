package org.unknown100name.ecommercerecommend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommercerecommend.service.RecommendService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author unknown100name
 * @since 2022.02.27
 *
 * @description 商城推荐控制器
 */
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Resource
    private RecommendService recommendService;

    @GetMapping("/item")
    public List<Long> getRecommendCategoryTwoId(String userId){
        return recommendService.getRecommendCategoryTwoId(Long.parseLong(userId));
    }
}
