package org.unknown100name.ecommerce.service;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.pojo.dto.ShoppingCarDTO;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @descriptio
 * @since 2022/1/3
 */
@Service
public interface ShoppingCarService{

    BaseResult<String> increase(Long userId, Long innerItemId);

    BaseResult<String> decrease(Long userId, Long innerItemId);

    BaseResult<String> delete(Long userId, Long innerItemId);

    BaseResult<ShoppingCarDTO> get(Long userId);

}