package org.unknown100name.ecommerce.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.EvaluateMapper;
import org.unknown100name.ecommerce.dao.OrderMapper;
import org.unknown100name.ecommerce.pojo.dto.InnerOrderDTO;
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

    @Resource
    private OrderMapper orderMapper;

    @Override
    public BaseResult<String> insertEvaluate(EvaluateGiveParam evaluateGiveParam) {
        InnerOrderDTO existOrder = orderMapper.getInnerOrderById(Long.parseLong(evaluateGiveParam.getInnerOrderId()));
        if (existOrder == null){
            return BaseResult.failResult(BaseResultMsg.ERROR_PARAM);
        }
        Evaluate insertEvaluate = new Evaluate(evaluateGiveParam);
        insertEvaluate.setInnerItemId(existOrder.getInnerItemId());
        evaluateMapper.insert(insertEvaluate);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }
}
