package org.unknown100name.ecommercebackend.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercebackend.pojo.dto.ContactDTO;
import org.unknown100name.ecommercebackend.pojo.entity.Contact;

/**
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
    ContactDTO getContactByIdWithOutDelete(long contactId);

    /**
     * 根据 userId 获取联系人菜单
     * @param userId
     * @return
     */
    List<ContactDTO> getContactByUserId(long userId);

    /**
     * 删除联系人(假删除)
     * @param contactId
     */
    void fakeDelete(Long contactId);
}
