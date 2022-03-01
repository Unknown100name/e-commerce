package org.unknown100name.ecommerce.aspect.token;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.unknown100name.ecommerce.aspect.AopUtils;
import org.unknown100name.ecommerce.aspect.activity.ActivityFiled;
import org.unknown100name.ecommerce.pojo.dto.InnerItemDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.recommend.pojo.entity.UserActivity;
import org.unknown100name.ecommerce.service.RedisService;
import org.unknown100name.ecommerce.service.impl.RedisServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

/**
 * @author unknown100name
 * @since 2022.02.28
 * @description 登录验证处理
 */
@Component
@Aspect
public class TokenAspect {

    @Resource
    private RedisService redisService;

    @Pointcut("@annotation(org.unknown100name.ecommerce.aspect.token.TokenAuth)")
    public void pointCut(){

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            HashMap<String, Object> paramMap = AopUtils.getParameterMap(point);

            Method method = ((MethodSignature) point.getSignature()).getMethod();
            Parameter[] parameters = method.getParameters();

            //获取RequestAttributes
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

            String token = null;

            // 获取 userId
            Long userId = null;
            for (Parameter parameter : parameters) {
                if ("userId".equals(parameter.getName())){
                    userId = Long.parseLong((String)paramMap.get(parameter.getName()));
                }
            }

            // TODO: 拒绝访问 token 写好之后重新覆盖
//            if (userId == null || token == null || !redisService.checkToken(userId, token)){
//                return null;
//            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return point.proceed();
    }
}
