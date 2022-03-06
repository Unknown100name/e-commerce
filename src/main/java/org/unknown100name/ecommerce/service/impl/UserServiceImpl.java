package org.unknown100name.ecommerce.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unknown100name.ecommerce.dao.ContactMapper;
import org.unknown100name.ecommerce.dao.UserMapper;
import org.unknown100name.ecommerce.pojo.dto.ContactDTO;
import org.unknown100name.ecommerce.pojo.dto.UserBaseDTO;
import org.unknown100name.ecommerce.pojo.dto.UserDetailDTO;
import org.unknown100name.ecommerce.pojo.entity.Contact;
import org.unknown100name.ecommerce.pojo.entity.User;
import org.unknown100name.ecommerce.pojo.vo.ContactCreateParam;
import org.unknown100name.ecommerce.pojo.vo.UserLoginParam;
import org.unknown100name.ecommerce.pojo.vo.UserRegisterParam;
import org.unknown100name.ecommerce.service.RedisService;
import org.unknown100name.ecommerce.service.UserService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;
import org.unknown100name.ecommerce.util.JwtUtil;
import org.unknown100name.ecommerce.util.SHA1Util;

import java.util.List;

/**
 * @author unknown100name
 * @description 用户服务类
 * @since 2022/1/2
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ContactMapper contactMapper;

    @Resource
    private RedisService redisService;

    @Override
    public BaseResult<UserBaseDTO> login(UserLoginParam userLoginParam) {
        // 比较数据库
        UserDetailDTO existUser = userMapper.getUserDetailByNick(userLoginParam.getNick());
        if (existUser == null || !userLoginParam.getPassword().equals(existUser.getPassword())) {
            return BaseResult.failResult(BaseResultMsg.ERROR_NICK_OR_PASSWORD);
        }else{
            String token = signTokenWithRedis(String.valueOf(existUser.getId()));
            return BaseResult.successResult(token, new UserBaseDTO(
                    existUser.getId(), existUser.getNick(), existUser.getType(), existUser.getGender()
            ));
        }
    }

    /**
     * 通过 userId 颁发 token 并保存入 redis
     * @param userId userId
     * @return token
     */
    private String signTokenWithRedis(String userId){
        // 颁发 token
        String token = JwtUtil.sign(userId, null);
        // redis 处理
        redisService.set(userId, token);
        redisService.expire(userId, JwtUtil.EXPIRE_TIME);
        return token;
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
        redisService.remove(String.valueOf(userId));
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
        if(existUser == null || !idCard.equals(existUser.getIdCard())){
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

    @Override
    public BaseResult<List<ContactDTO>> contactGet(Long userId) {
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, contactMapper.getContactByUserId(userId));
    }

    @Override
    public BaseResult<String> contactAdd(Long userId, ContactCreateParam contactCreateParam) {
        Contact contact = new Contact(userId, contactCreateParam);
        contactMapper.insert(contact);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

    @Override
    public BaseResult<String> contactDelete(Long contactId) {
        contactMapper.fakeDelete(contactId);
        return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, null);
    }

}
