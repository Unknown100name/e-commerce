package org.unknown100name.ecommercebackend.service;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommercebackend.pojo.dto.ShoppingCarDTO;
import common.BaseResult;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/3
 */
@Service
public interface ShoppingCarService{

    BaseResult<String> increase(Long userId, Long innerItemId);

    BaseResult<String> decrease(Long userId, Long innerItemId);

    BaseResult<String> delete(Long userId, Long innerItemId);

    BaseResult<ShoppingCarDTO> get(Long userId);

}