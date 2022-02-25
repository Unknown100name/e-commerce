package org.unknown100name.ecommerce.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.ShoppingCarMapper;
import org.unknown100name.ecommerce.pojo.dto.InnerShoppingCarDTO;
import org.unknown100name.ecommerce.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommerce.service.ShoppingCarService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;
import org.unknown100name.ecommerce.util.IdUtil;

import java.util.HashMap;

/**
 * @author unknown100name
 * @description 
 * @since 2022/1/3
 */
@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Resource
    private ShoppingCarMapper shoppingCarMapper;

    @Override
    public BaseResult<?> increase(Long userId, Long innerItemId) {
        if(shoppingCarMapper.getInnerShoppingCarByUserIdAndInnerItemId(userId, innerItemId) == null){
            shoppingCarMapper.create(IdUtil.getId(), userId, innerItemId);
        }else{
            shoppingCarMapper.increase(userId, innerItemId);
        }
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> decrease(Long userId, Long innerItemId) {
        InnerShoppingCarDTO innerShoppingCar = shoppingCarMapper.getInnerShoppingCarByUserIdAndInnerItemId(userId, innerItemId);
        if(innerShoppingCar == null){
            return BaseResult.failResult(BaseResultMsg.ERROR_PARAM);
        }else if (innerShoppingCar.getCount() == 1) {
            delete(userId, innerItemId);
        }else {
            shoppingCarMapper.decrease(userId, innerItemId);
        }
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> delete(Long userId, Long innerItemId) {
        shoppingCarMapper.delete(userId, innerItemId);
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<ShoppingCarDTO> get(Long userId) {
        ShoppingCarDTO shoppingCarDTO = shoppingCarMapper.getShoppingCarByUserId(userId);
        return BaseResult.successResult(null, shoppingCarDTO);
    }
    
}
