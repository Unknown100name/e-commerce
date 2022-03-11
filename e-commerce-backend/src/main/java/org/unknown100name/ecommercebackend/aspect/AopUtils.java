package org.unknown100name.ecommercebackend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;

import java.util.HashMap;

/**
 * @author unknown100name
 * @since 2022.02.28
 *
 * @description aop 工具类
 */
public final class AopUtils {


    /**
     * 根据 point 生成参数名与值的映射 map
     * @param point
     * @return
     */
    public static HashMap<String, Object> getParameterMap(ProceedingJoinPoint point){
        Object[] paramValues = point.getArgs();
        String[] paramNames = ((CodeSignature)point.getSignature()).getParameterNames();

        HashMap<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < paramNames.length; i++) {
            paramMap.put(paramNames[i], paramValues[i]);
        }
        return paramMap;
    }
}
