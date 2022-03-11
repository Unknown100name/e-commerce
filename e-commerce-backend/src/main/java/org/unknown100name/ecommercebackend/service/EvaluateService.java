package org.unknown100name.ecommercebackend.service;

import org.unknown100name.ecommercebackend.pojo.vo.EvaluateCreateParam;
import common.BaseResult;

/**
 * @author unknown100name
 * @since 2022/1/3
 * @description 
 *
 */
public interface EvaluateService {

    BaseResult<String> insertEvaluate(EvaluateCreateParam evaluateGiveParam);
    
}
