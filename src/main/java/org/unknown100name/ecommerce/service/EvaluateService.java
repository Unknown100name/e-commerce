package org.unknown100name.ecommerce.service;

import org.unknown100name.ecommerce.pojo.vo.EvaluateCreateParam;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @since 2022/1/3
 * @description 
 *
 */
public interface EvaluateService {

    BaseResult<String> insertEvaluate(EvaluateCreateParam evaluateGiveParam);
    
}
