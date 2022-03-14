package org.unknown100name.ecommercebackend.aspect.activity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.unknown100name.ecommercebackend.aspect.AopUtils;
import org.unknown100name.ecommercebackend.dao.ItemMapper;
import org.unknown100name.ecommercebackend.pojo.dto.InnerItemDTO;
import org.unknown100name.ecommercebackend.pojo.dto.ItemBaseDTO;
import common.BaseResult;
import common.BaseResultMsg;
import org.unknown100name.ecommercebackend.service.RedisService;
import util.HttpRequestUtil;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import static common.ConstUtil.*;

/**
 * @author unknown100name
 * @since 2022.02.28
 * @description 行为日志处理逻辑
 */
@Component
@Aspect
public class ActivityAspect {

    @Resource
    private ItemMapper itemMapper;

    @Resource
    private RedisService redisService;

    @Resource(name = "HttpSender")
    private ThreadPoolTaskExecutor httpSender;

    @Pointcut("@annotation(org.unknown100name.ecommercebackend.aspect.activity.ActivityRecord)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {

            HashMap<String, Object> paramMap = AopUtils.getParameterMap(point);

            Method method = ((MethodSignature) point.getSignature()).getMethod();
            Parameter[] parameters = method.getParameters();

            Long userId = null;
            Long categoryTwoId = null;
            Long itemId = null;

            for (Parameter parameter : parameters) {
                // userId
                if ("userId".equals(parameter.getName())) {
                    userId = Long.parseLong((String)paramMap.get(parameter.getName()));
                }
                // sourceId
                else if (parameter.isAnnotationPresent(ActivityFiled.class)) {
                    ActivityFiled activityFiled = parameter.getAnnotation(ActivityFiled.class);
                    ItemBaseDTO itemBaseDTO;
                    switch (activityFiled.source()){
                        // 如果没有写则直接退出
                        case NONE:
                            break;
                        case ITEM_ID:
                            itemBaseDTO = itemMapper.getItemBaseById(Long.parseLong((String)paramMap.get(parameter.getName())));
                            // 没找到 Item 直接跳过
                            if (itemBaseDTO == null){
                                break;
                            }
                            categoryTwoId = itemBaseDTO.getCategoryTwoId();
                            itemId = itemBaseDTO.getId();
                            break;
                        case INNER_ITEM_ID:
                            InnerItemDTO innerItemDTO = itemMapper.getInnerItemById(Long.parseLong((String)paramMap.get(parameter.getName())));
                            if (innerItemDTO == null){
                                break;
                            }
                            itemBaseDTO = itemMapper.getItemBaseById(innerItemDTO.getItemId());
                            // 没找到 Item 直接跳过
                            if (itemBaseDTO == null){
                                break;
                            }
                            categoryTwoId = itemBaseDTO.getCategoryTwoId();
                            itemId = itemBaseDTO.getId();
                            break;
                        case CATEGORY_TWO_ID:
                            categoryTwoId = Long.parseLong((String)paramMap.get(parameter.getName()));
                            break;
                        default: break;
                    }
                }
            }

            if (userId != null && categoryTwoId != null){
                Map<String, Object> param = new HashMap<>(2);
                param.put("userId", userId);
                param.put("categoryTwoId", categoryTwoId);
                httpSender.submit(() -> {
                    HttpRequestUtil.doPost(RECOMMEND_HOST + RECOMMEND_CONTROLLER + SAVE_ACTIVITY, param, null);
                });

            }

            if (itemId != null){
                itemMapper.increaseHit(itemId);
            }

            if(userId != null){
                if (redisService.hasKey(REDIS_PREFIX_USER_CLICK + userId)) {
                    redisService.increment(REDIS_PREFIX_USER_CLICK + userId, 1);
                }else {
                    redisService.set(REDIS_PREFIX_USER_CLICK + userId, "1");
                }
                // 点击 N 个以上的时候发送请求进行刷新 Similarity
                if (Long.parseLong(redisService.get(REDIS_PREFIX_USER_CLICK + userId)) >= MIN_SIMILARITY_RECAL_SIZE){
                    redisService.set(REDIS_PREFIX_USER_CLICK + userId, "0");
                    Map<String, Object> param = new HashMap<>(2);
                    param.put("userId", userId);
                    httpSender.submit(() -> {
                        HttpRequestUtil.doPost(RECOMMEND_HOST + RECOMMEND_CONTROLLER + UPDATE_SIMILARITY, param, null );
                    });

                }
            }


        } catch (Throwable e) {
            e.printStackTrace();
            return BaseResult.failResult(BaseResultMsg.ERROR_UNKNOWN);
        }
        return point.proceed();
    }
}
