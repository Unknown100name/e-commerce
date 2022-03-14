package org.unknown100name.ecommercerecommend.controller;

import common.BaseResult;
import common.BaseResultMsg;
import common.ConstUtil;
import entity.CategoryTwo;
import org.springframework.web.bind.annotation.*;
import org.unknown100name.ecommercerecommend.dao.CategoryMapper;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.service.RecommendService;
import org.unknown100name.ecommercerecommend.service.UserActivityService;
import org.unknown100name.ecommercerecommend.service.UserSimilarityService;

import javax.annotation.Resource;
import java.util.List;

import static common.ConstUtil.*;

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

    @Resource
    private UserActivityService userActivityService;

    @Resource
    private UserSimilarityService userSimilarityService;

    @Resource
    private CategoryMapper categoryMapper;

    @GetMapping(GET_RECOMMEND_CATEGORY)
    @ResponseBody
    public BaseResult<List<Long>> getRecommendCategoryTwoId(@RequestParam("userId") String userId){
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS,
                recommendService.getRecommendCategoryTwoId(Long.parseLong(userId)));
    }

    @PostMapping(SAVE_ACTIVITY)
    @ResponseBody
    public BaseResult<String> saveActivity(@RequestParam("userId") String userId, @RequestParam("categoryTwoId")String categoryTwoId){
        userActivityService.saveUserActivity(new UserActivity(Long.parseLong(userId), Long.parseLong(categoryTwoId), null));
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @PostMapping(REGISTER_NEW_USER)
    @ResponseBody
    public BaseResult<String> registerNewUser(@RequestParam("userId") String userId){
        List<CategoryTwo> categoryTwoList = categoryMapper.getCategoryTwoList();
        categoryTwoList.forEach(
                categoryTwo -> userActivityService.saveUserActivity(new UserActivity(Long.parseLong(userId), categoryTwo.getCategoryTwoId(), null))
        );
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @PostMapping(UPDATE_SIMILARITY)
    @ResponseBody
    public BaseResult<Boolean> updateSimilarity(@RequestParam("userId") String userId){
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS,
                userSimilarityService.updateSimilarity(Long.parseLong(userId)));
    }

}
