package org.unknown100name.ecommerce.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.EvaluateMapper;
import org.unknown100name.ecommerce.pojo.entity.Evaluate;
import org.unknown100name.ecommerce.pojo.vo.EvaluateGiveParam;
import org.unknown100name.ecommerce.service.EvaluateService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;


/**
 * @author unknown100name
 * @description
 * @since 2022/1/3
 */
@Service
public class EvaluateServiceImpl implements EvaluateService{

    @Resource
    private EvaluateMapper evaluateMapper;

    @Override
    public BaseResult<String> insertEvaluate(EvaluateGiveParam evaluateGiveParam) {
        Evaluate insertEvaluate = new Evaluate(evaluateGiveParam);
        evaluateMapper.insert(insertEvaluate);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }
}
