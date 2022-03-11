package org.unknown100name.ecommercebackend.service;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommercebackend.pojo.dto.ContactDTO;
import org.unknown100name.ecommercebackend.pojo.dto.UserBaseDTO;
import org.unknown100name.ecommercebackend.pojo.vo.ContactCreateParam;
import org.unknown100name.ecommercebackend.pojo.vo.UserLoginParam;
import org.unknown100name.ecommercebackend.pojo.vo.UserRegisterParam;
import common.BaseResult;

import java.util.List;

/**
 * @author unknown100name
 * @description
 * @since 2022/1/2
 */
@Service
public interface UserService {

    BaseResult<UserBaseDTO> login(UserLoginParam userLoginParam);

    BaseResult<String> register(UserRegisterParam userRegisterParam);

    BaseResult<String> logout(Long userId);

    BaseResult<String> delete(Long userId);

    BaseResult<String> forgetPassword(String nick, String idCard);

    BaseResult<String> resetPassword(Long userId, String oldPassword, String newPassword);

    BaseResult<List<ContactDTO>> contactGet(Long userId);

    BaseResult<String> contactAdd(Long userId, ContactCreateParam contactCreateParam);

    BaseResult<String> contactDelete(Long contactId);
}
