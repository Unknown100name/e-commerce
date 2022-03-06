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
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;
import org.unknown100name.ecommerce.util.JwtUtil;

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
    public Object around(ProceedingJoinPoint point) {
        try {
            HashMap<String, Object> paramMap = AopUtils.getParameterMap(point);

            Method method = ((MethodSignature) point.getSignature()).getMethod();
            Parameter[] parameters = method.getParameters();

            //获取RequestAttributes
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            if (requestAttributes != null){
                HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            }else {
                return BaseResult.failResult(BaseResultMsg.ERROR_UNKNOWN);
            }


            // TODO: 拒绝访问 token 写好之后重新覆盖
            String token = null;
            // 从 token 中获取 userId
//            Long userId = JwtUtil.getUserId(token);
//            if (userId == null || token == null || !token.equals(redisService.get(String.valueOf(userId)))){
//                return null;
//            }

            return point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            return BaseResult.failResult(BaseResultMsg.ERROR_UNKNOWN);
        }

    }
}
