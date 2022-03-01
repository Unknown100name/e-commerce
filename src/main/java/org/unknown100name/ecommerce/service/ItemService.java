package org.unknown100name.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.pojo.dto.CategoryDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.ItemDetailDTO;
import org.unknown100name.ecommerce.pojo.vo.ItemCreateParam;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @since 2022/1/2
 * @description
 *
 */
@Service
public interface ItemService {

    BaseResult<List<CategoryDTO>> getCategory();

    BaseResult<List<ItemBaseDTO>> getByCategoryOne(Long categoryOneId);

    BaseResult<List<ItemBaseDTO>> getByCategoryTwo(Long categoryTwoId);

    BaseResult<List<ItemBaseDTO>> searchList(String keyword);

    BaseResult<List<ItemBaseDTO>> shopList(Long shopId);

    BaseResult<ItemBaseDTO> base(Long itemId);

    BaseResult<ItemDetailDTO> detail(Long itemId);

    BaseResult<String> create(ItemCreateParam itemCreateParam);

    // BaseResult<String> update(ItemCreateParam itemCreateParam);

    BaseResult<String> upload(Long parseLong);

    BaseResult<String> up(Long itemId);

    BaseResult<String> down(Long itemId);

    BaseResult<String> delete(Long itemId);

    ItemBaseDTO getMaxSellItemByCategoryTwoId(Long categoryTwoId);

    List<ItemBaseDTO> getItemByRandom();
}
