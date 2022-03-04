package org.unknown100name.ecommerce.service;

import org.springframework.stereotype.Service;
import org.unknown100name.ecommerce.pojo.dto.ContactDTO;
import org.unknown100name.ecommerce.pojo.dto.UserBaseDTO;
import org.unknown100name.ecommerce.pojo.vo.ContactCreateParam;
import org.unknown100name.ecommerce.pojo.vo.UserLoginParam;
import org.unknown100name.ecommerce.pojo.vo.UserRegisterParam;
import org.unknown100name.ecommerce.util.BaseResult;

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
