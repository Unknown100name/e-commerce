package org.unknown100name.ecommerce.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.dto.ContactDTO;
import org.unknown100name.ecommerce.pojo.entity.Contact;

/**
 * // TODO: 增删改查没有增加
 * @author unknown100name
 * @description 联系人数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface ContactMapper extends BaseMapper<Contact> {

    /**
     * 根据 Id 获取联系人
     * @param contactId
     * @return
     */
    ContactDTO getContactById(long contactId);

    /**
     * 根据 userId 获取联系人菜单
     * @param userId
     * @return
     */
    List<ContactDTO> getContactByUserId(long userId);

    
    
}
