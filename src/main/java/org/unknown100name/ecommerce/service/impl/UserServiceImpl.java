package org.unknown100name.ecommerce.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.dao.UserMapper;
import org.unknown100name.ecommerce.pojo.dto.UserBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.UserDetailDTO;
import org.unknown100name.ecommerce.pojo.entity.User;
import org.unknown100name.ecommerce.pojo.vo.UserLoginParam;
import org.unknown100name.ecommerce.pojo.vo.UserRegisterParam;
import org.unknown100name.ecommerce.service.UserService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;
import org.unknown100name.ecommerce.util.SHA1Util;

/**
 * @author unknown100name
 * @description 用户服务类
 * @since 2022/1/2
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public BaseResult<UserBaseDTO> login(UserLoginParam userLoginParam) {
        // 比较数据库
        UserDetailDTO existUser = userMapper.getUserDetailByNick(userLoginParam.getNick());
        if (existUser == null || !userLoginParam.getPassword().equals(existUser.getPassword())) {
            return BaseResult.failResult(BaseResultMsg.ERROR_NICK_OR_PASSWORD);
        }else{
            // TODO: token 签发
            String token = null;
            return BaseResult.successResult(token, new UserBaseDTO(
                    existUser.getId(), existUser.getNick(), existUser.getType(), existUser.getGender()
            ));
        }
    }

    @Override
    public BaseResult<String> register(UserRegisterParam userRegisterParam) {
        UserBaseDTO existUser = userMapper.getUserByNick(userRegisterParam.getNick());
        if (existUser != null){
            return BaseResult.failResult(BaseResultMsg.ERROR_EXISTED_USERNAME);
        }
        User insertUser = new User(userRegisterParam);
        userMapper.insert(insertUser);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> logout(Long userId) {
        // TODO: token 销毁
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> delete(Long userId) {
        userMapper.fakeDeleteByUserId(userId);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> forgetPassword(String nick, String idCard) {
        // 验证身份证
        UserDetailDTO existUser = userMapper.getUserDetailByNick(nick);
        if(idCard.equals(existUser.getIdCard())){
            return BaseResult.failResult(BaseResultMsg.ERROR_NICK_OR_ID_CARD);
        }
        // 重置密码, 默认为身份证后六位
        userMapper.resetPassword(existUser.getId(), SHA1Util.encodeToSha1(idCard.substring(12)));
        return BaseResult.successResult(BaseResultMsg.SUCCESS_FORGET_PASSWORD, null);
    }

    @Override
    public BaseResult<String> resetPassword(Long userId, String oldPassword, String newPassword) {
        // 验证身份证
        UserDetailDTO existUser = userMapper.getUserDetailById(userId);
        if(!SHA1Util.encodeToSha1(oldPassword).equals(existUser.getPassword())){
            return BaseResult.failResult(BaseResultMsg.ERROR_NICK_OR_PASSWORD);
        }
        // 重置密码
        userMapper.resetPassword(userId, SHA1Util.encodeToSha1(newPassword));
        return BaseResult.successResult(BaseResultMsg.SUCCESS_RESET_PASSWORD, null);
    }

}
