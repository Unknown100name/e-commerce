package org.unknown100name.ecommercebackend.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommercebackend.dao.EvaluateMapper;
import org.unknown100name.ecommercebackend.dao.OrderMapper;
import org.unknown100name.ecommercebackend.pojo.dto.InnerOrderDTO;
import org.unknown100name.ecommercebackend.pojo.entity.Evaluate;
import org.unknown100name.ecommercebackend.pojo.vo.EvaluateCreateParam;
import org.unknown100name.ecommercebackend.service.EvaluateService;
import common.BaseResult;
import org.unknown100name.ecommercebackend.util.BaseResultMsg;


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
    public BaseResult<String> insertEvaluate(EvaluateCreateParam evaluateGiveParam) {
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
