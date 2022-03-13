package org.unknown100name.ecommercebackend.controller;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommercebackend.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommercebackend.service.ItemService;
import common.BaseResult;
import common.BaseResultMsg;
import util.HttpRequestUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static common.ConstUtil.*;

/**
 * @author unknown100name
 * @since 2022.02.27
 *
 * @description 商城推荐控制器
 */
@RestController
@RequestMapping("/recommend")
@Api(tags = "商城推荐(商品首页)")
public class RecommendController {

    @Resource
    private ItemService itemService;

    @ApiOperation(value = "根据用户id商品推荐", notes = "不传用户则随机返回商品")
    @GetMapping("/item")
    public BaseResult<List<ItemBaseDTO>> getRecommendItem(String userId){
        // 如果没有登录
        if (StringUtils.isBlank(userId)){
            return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, itemService.getItemByRandom());
        }

        // 登陆了则尝试请求
        Map<String, String> map = new HashMap<>(1);
        map.put("userId", userId);
        JSONArray retData = HttpRequestUtil.doGet(RECOMMEND_HOST + RECOMMEND_CONTROLLER + GET_RECOMMEND_CATEGORY, map).getJSONArray("data");

        // 如果没有数据(表示没有执行完毕或者出错了或者没有数据)
        if (retData == null || retData.isEmpty()) {
            return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, itemService.getItemByRandom());
        }

        // 否则正常返回
        List<Long> categoryTwoIdList = retData.toJavaList(Long.class);
        List<ItemBaseDTO> maxSellItemList = new ArrayList<>();
        categoryTwoIdList.forEach(
                categoryTwoId -> maxSellItemList.add(itemService.getMaxSellItemByCategoryTwoId(categoryTwoId))
        );
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, maxSellItemList);
    }
}
