package org.unknown100name.ecommerce.recommend.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.recommend.service.RecommendService;

import org.unknown100name.ecommerce.util.BaseResult;

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

    @PostMapping("/item")
    public BaseResult<List<ItemBaseDTO>> getRecommendItem(String userId){
        if (StringUtils.isBlank(userId)){
            return BaseResult.successResult(
                    null,
                    recommendService.getRandomItem()
            );
        }

        return BaseResult.successResult(
                null,
                recommendService.getSimilarityItem(Long.parseLong(userId))
        );
    }
}
