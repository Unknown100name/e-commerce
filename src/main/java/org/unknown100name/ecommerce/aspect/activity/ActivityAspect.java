package org.unknown100name.ecommerce.aspect.activity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.unknown100name.ecommerce.aspect.AopUtils;
import org.unknown100name.ecommerce.dao.ItemMapper;
import org.unknown100name.ecommerce.pojo.dto.InnerItemDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserActivity;
import org.unknown100name.ecommerce.recommend.service.UserActivityService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

/**
 * @author unknown100name
 * @since 2022.02.28
 * @description 行为日志处理逻辑
 */
@Component
@Aspect
public class ActivityAspect {

    @Resource
    private UserActivityService userActivityService;

    @Resource
    private ItemMapper itemMapper;

    @Pointcut("@annotation(org.unknown100name.ecommerce.aspect.activity.ActivityRecord)")
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

            for (Parameter parameter : parameters) {
                // userId
                if ("userId".equals(parameter.getName())) {
                    userId = Long.parseLong((String)paramMap.get(parameter.getName()));
                }
                // sourceId
                else if (parameter.isAnnotationPresent(ActivityFiled.class)) {
                    ActivityFiled activityFiled = parameter.getAnnotation(ActivityFiled.class);
                    ItemBaseDTO itemBaseDTO = null;
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
                            break;
                        case CATEGORY_TWO_ID:
                            categoryTwoId = Long.parseLong((String)paramMap.get(parameter.getName()));
                            break;
                        default: break;
                    }
                }
            }

            if (userId != null && categoryTwoId != null){
                userActivityService.saveUserActive(new UserActivity(userId, categoryTwoId, 1L));
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return BaseResult.failResult(BaseResultMsg.ERROR_UNKNOWN);
        }
        return point.proceed();
    }
}
