package org.unknown100name.ecommercebackend.controller;

import com.alibaba.fastjson.JSONObject;
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
import common.ConstUtil;
import util.HttpRequestUtil;

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
@Api(tags = "商城推荐(商品首页)")
public class RecommendController {

    @Resource
    private ItemService itemService;

    @ApiOperation(value = "根据用户id商品推荐", notes = "不传用户则随机返回商品")
    @GetMapping("/item")
    public BaseResult<List<ItemBaseDTO>> getRecommendItem(String userId){
        if (StringUtils.isBlank(userId)){
            return BaseResult.successResult(
                    BaseResultMsg.SUCCESS_OTHERS,
                    itemService.getItemByRandom()
            );
        }

        JSONObject retVal = HttpRequestUtil.doGet(ConstUtil.RECOMMEND_HOST + ConstUtil.RECOMMEND_CONTROLLER + ConstUtil.GET_RECOMMEND_CATEGORY, Long.parseLong(userId));
        return BaseResult.successResult(
                BaseResultMsg.SUCCESS_OTHERS,
                null
        );
    }
}
