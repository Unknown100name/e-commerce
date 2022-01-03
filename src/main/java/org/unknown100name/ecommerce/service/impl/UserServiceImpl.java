package org.unknown100name.ecommerce.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.UserMapper;
import org.unknown100name.ecommerce.pojo.dto.UserDTO;
import org.unknown100name.ecommerce.pojo.entity.User;
import org.unknown100name.ecommerce.pojo.vo.UserLoginParam;
import org.unknown100name.ecommerce.pojo.vo.UserRegisterParam;
import org.unknown100name.ecommerce.service.UserService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/2
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public BaseResult<?> login(UserLoginParam userLoginParam) {
        // 比较数据库
        UserDTO existUser = userMapper.getUserByNick(userLoginParam.getNick());
        if (existUser == null || !userLoginParam.getPassword().equals(existUser.getPassword())) {
            return BaseResult.failResult(BaseResultMsg.ERROR_NICK_OR_PASSWORD);
        }else{
            // TODO: token 签发
            String token = null;
            return BaseResult.successResult(null, token);
        }
    }

    @Override
    public BaseResult<?> register(UserRegisterParam userRegisterParam) {
        User insertUser = new User(userRegisterParam);
        userMapper.insertUser(insertUser);
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> logout(Long userId) {
        // TODO: token 销毁
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> delete(Long userId) {
        userMapper.deleteUser(userId);
        return BaseResult.successResult(null, null);
    }

    @Override
    public BaseResult<?> forgetPassword(String nick, String idCard) {
        // 验证身份证
        UserDTO existUser = userMapper.getUserByNick(nick);
        if(existUser == null || idCard.equals(existUser.getIdCard())){
            return BaseResult.failResult(BaseResultMsg.ERROR_NICK_OR_IDCARD);
        }
        // 重置密码, 默认为身份证后六位
        userMapper.resetPassword(existUser.getId(), idCard.substring(12));
        return BaseResult.successResult(BaseResultMsg.SUCCESS_FORGET_PASSWORD, null);
    }

    @Override
    public BaseResult<?> resetPassword(Long userId, String oldPassword, String newPassword) {
        // 验证身份证
        UserDTO existUser = userMapper.getUserById(userId);
        if(existUser == null || !oldPassword.equals(existUser.getPassword())){
            return BaseResult.failResult(BaseResultMsg.ERROR_NICK_OR_PASSWORD);
        }
        // 重置密码
        userMapper.resetPassword(userId, newPassword);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_RESET_PASSWORD, null);
    }

}
