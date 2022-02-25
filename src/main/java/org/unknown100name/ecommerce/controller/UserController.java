package org.unknown100name.ecommerce.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommerce.aspect.TokenAuth;
import org.unknown100name.ecommerce.pojo.vo.UserLoginParam;
import org.unknown100name.ecommerce.pojo.vo.UserRegisterParam;
import org.unknown100name.ecommerce.service.RedisService;
import org.unknown100name.ecommerce.service.UserService;
import org.unknown100name.ecommerce.util.BaseResult;
import org.unknown100name.ecommerce.util.BaseResultMsg;
import org.unknown100name.ecommerce.util.SHA1Util;
import org.unknown100name.ecommerce.util.VertifyCodeUtil;

/**
 * TODO: 联系人相关接口
 * @author unknown100name
 * @since 2022/1/2
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private VertifyCodeUtil vertifyCodeUtil;

    @Resource
    private RedisService redisService;

    /**
     * 登录
     * @param userLoginParam
     * @return
     */
    @PostMapping("login")
    @ResponseBody
    public BaseResult<?> login(@RequestBody UserLoginParam userLoginParam){
        // TODO: IP 获取
        if(!vertifyCodeUtil.compareWithVertifyCode(null, userLoginParam.getVertifyCode())){
            return BaseResult.failResult(BaseResultMsg.ERROR_VERTIFY_CODE);
        }
        // password 转 sha1
        userLoginParam.setPassword(SHA1Util.encodeToSha1(userLoginParam.getPassword()));
        return userService.login(userLoginParam);
    }
    
    /**
     * 注册
     * @param userRegisterParam
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public BaseResult<?> register(@RequestBody UserRegisterParam userRegisterParam){
        // TODO: IP 获取
        if(!vertifyCodeUtil.compareWithVertifyCode(null, userRegisterParam.getVertifyCode())){
            return BaseResult.failResult(BaseResultMsg.ERROR_VERTIFY_CODE);
        }
        //password 转 sha1
        userRegisterParam.setPassword(SHA1Util.encodeToSha1(userRegisterParam.getPassword()));
        return userService.register(userRegisterParam);
    }

    /**
     * 登出
     * @param userId
     * @return
     */
    @PostMapping("logout")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> logout(String userId){
        return userService.logout(Long.parseLong(userId));
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> delete(String userId){
        return userService.delete(Long.parseLong(userId));
    }

    /**
     * 忘记密码
     * @param nick
     * @param idCard
     * @return
     */
    @PostMapping("forgetPassword")
    @ResponseBody
    public BaseResult<?> forgetPassword(String nick, String idCard){
        return userService.forgetPassword(nick, idCard);
    }

    /**
     * 重置密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("resetPassword")
    @ResponseBody
    @TokenAuth
    public BaseResult<?> resetPassword(String userId, String oldPassword, String newPassword){
        return userService.resetPassword(Long.parseLong(userId), oldPassword, newPassword);
    }   

    /**
     * 获取验证码
     * @return
     */
    @GetMapping("vertifyCodeImage")
    @ResponseBody
    public BaseResult<?> vertifyCodeImage(){
        // TODO: IP 获取
        String newImageBase64 = vertifyCodeUtil.getNewVertifyCode(null);
        // Redis 存储
        if(newImageBase64 == null){
            return BaseResult.failResult(BaseResultMsg.ERROR_VERTIFY_CODE_TOO_QUICK);
        }else{
            return BaseResult.successResult(null, newImageBase64);
        }
    }
}
