package org.unknown100name.ecommerce.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.dto.ContactDTO;

/**
 * // TODO: 增删改查没有增加
 * @author unknown100name
 * @description 联系人数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface ContactMapper {

    /**
     * 根据 userId 获取联系人菜单
     * @param shoppingCar
     * @return
     */
    List<ContactDTO> getByUserId(long shoppingCar);
    
}
