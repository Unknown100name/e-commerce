package org.unknown100name.ecommerce.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.ShoppingCarMapper;
import org.unknown100name.ecommerce.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommerce.service.ShoppingCarService;
import org.unknown100name.ecommerce.util.BaseResult;

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
        shoppingCarMapper.increase(userId, innerItemId);
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> decrease(Long userId, Long innerItemId) {
        shoppingCarMapper.decrease(userId, innerItemId);
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> delete(Long userId, Long innerItemId) {
        shoppingCarMapper.delete(userId, innerItemId);
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<ShoppingCarDTO> get(Long userId) {
        ShoppingCarDTO shoppingCarDTO = shoppingCarMapper.getByUserId(userId);
        return BaseResult.successResult(null, shoppingCarDTO);
    }
    
}
