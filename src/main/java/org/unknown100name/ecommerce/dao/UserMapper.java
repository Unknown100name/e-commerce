package org.unknown100name.ecommerce.dao;

import org.apache.ibatis.annotations.Mapper;
import org.unknown100name.ecommerce.pojo.dto.UserDTO;
import org.unknown100name.ecommerce.pojo.entity.User;

/**
 * @author unknown100name
 * @description 用户数据库接口
 * @since 2022/1/3
 */
@Mapper
public interface UserMapper{

    /**
     * 通过昵称获取用户
     * @param nick
     * @return
     */
    UserDTO getUserByNick(String nick);

    /**
     * 通过 userId 获取用户
     * @param userId
     * @return
     */
    UserDTO getUserById(Long userId);

    /**
     * 新增用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUserById(User user);

    /**
     * 删除用户（假删除）
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * 重置用户密码
     * @param userId
     */
    void resetPassword(Long userId, String newPassword);
}