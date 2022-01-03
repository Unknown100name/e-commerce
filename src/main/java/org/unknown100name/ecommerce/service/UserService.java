package org.unknown100name.ecommerce.service;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.pojo.vo.UserLoginParam;
import org.unknown100name.ecommerce.pojo.vo.UserRegisterParam;
import org.unknown100name.ecommerce.util.BaseResult;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/2
 */
@Service
public interface UserService {

    BaseResult<?> login(UserLoginParam userLoginParam);

    BaseResult<?> register(UserRegisterParam userRegisterParam);

    BaseResult<?> logout(Long userId);

    BaseResult<?> delete(Long userId);

    BaseResult<?> forgetPassword(String nick, String idCard);

    BaseResult<?> resetPassword(Long userId, String oldPassword, String newPassword);
}
