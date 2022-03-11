package org.unknown100name.ecommercebackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommercebackend.pojo.dto.UserBaseDTO;
import org.unknown100name.ecommercebackend.pojo.dto.UserDetailDTO;
import org.unknown100name.ecommercebackend.pojo.entity.User;

/**
 * @author unknown100name
 * @description 用户数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过昵称获取用户
     * @param nick
     * @return
     */
    UserBaseDTO getUserByNick(String nick);

    /**
     * 通过昵称获取用户全部信息
     * @param nick
     * @return
     */
    UserDetailDTO getUserDetailByNick(String nick);

    /**
     * 通过 userId 获取用户
     * @param userId
     * @return
     */
    UserBaseDTO getUserById(Long userId);

    /**
     * 通过昵称获取用户全部信息
     * @param id
     * @return
     */
    UserDetailDTO getUserDetailById(Long id);

    /**
     * 重置用户密码
     * @param userId
     * @param newPassword
     */
    void resetPassword(Long userId, String newPassword);

    /**
     * 假删除账号
     * @param userId
     */
    void fakeDeleteByUserId(Long userId);
}