package org.unknown100name.ecommercerecommend.controller;

import common.BaseResult;
import common.BaseResultMsg;
import entity.CategoryTwo;
import org.springframework.web.bind.annotation.*;
import org.unknown100name.ecommercerecommend.pojo.entity.UserActivity;
import org.unknown100name.ecommercerecommend.service.RecommendService;
import org.unknown100name.ecommercerecommend.service.UserActivityService;

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

    @Resource
    private UserActivityService userActivityService;

    @GetMapping("/getRecommendCategory")
    @ResponseBody
    public BaseResult<List<Long>> getRecommendCategoryTwoId(String userId){
        return BaseResult.successResult(
                BaseResultMsg.SUCCESS_OTHERS,
                recommendService.getRecommendCategoryTwoId(Long.parseLong(userId)));
    }

    @PostMapping("/saveActivity")
    @ResponseBody
    public BaseResult<String> saveActivity(@RequestBody String userId, @RequestBody String categoryTowId){
        userActivityService.saveUserActivity(new UserActivity(Long.parseLong(userId), Long.parseLong(categoryTowId), null));
        return BaseResult.successResult(
                BaseResultMsg.SUCCESS_OTHERS,
                null);
    }

    @PostMapping("/registerNewUser")
    @ResponseBody
    public BaseResult<String> registerNewUser(@RequestBody String userId, @RequestBody List<CategoryTwo> categoryTowList){
        categoryTowList.forEach(
                categoryTwo -> userActivityService.saveUserActivity(new UserActivity(Long.parseLong(userId), categoryTwo.getCategoryTwoId(), null))
        );
        return BaseResult.successResult(
                BaseResultMsg.SUCCESS_OTHERS,
                null);
    }

}
