package org.unknown100name.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemDetailDTO;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @since 2022/1/2
 * @description
 *
 */
@Service
public interface ItemService {

    BaseResult<List<ItemBaseDTO>> searchList(String keyword);

    BaseResult<List<ItemBaseDTO>> shopList(Long shopId);

    BaseResult<ItemBaseDTO> base(Long itemId);

    BaseResult<ItemDetailDTO> detail(Long itemId);
}
