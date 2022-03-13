package org.unknown100name.ecommercebackend.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import common.BaseResult;
import common.BaseResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.unknown100name.ecommercebackend.aspect.token.TokenAuth;
import org.unknown100name.ecommercebackend.pojo.dto.ContactDTO;
import org.unknown100name.ecommercebackend.pojo.dto.UserBaseDTO;
import org.unknown100name.ecommercebackend.pojo.vo.ContactCreateParam;
import org.unknown100name.ecommercebackend.pojo.vo.UserLoginParam;
import org.unknown100name.ecommercebackend.pojo.vo.UserRegisterParam;
import org.unknown100name.ecommercebackend.service.UserService;
import org.unknown100name.ecommercebackend.util.VertifyCodeUtil;
import util.IpUtil;
import util.SHA1Util;

import java.util.List;

/**
 * TODO: 关于验证码
 * @author unknown100name
 * @since 2022/1/2
 * @description
 */
@RestController
@RequestMapping("user")
@Api(tags = "基本用户相关接口")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private VertifyCodeUtil vertifyCodeUtil;

    /**
     * 登录
     * @param userLoginParam
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping("login")
    @ResponseBody
    public BaseResult<UserBaseDTO> login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request){
        if(!vertifyCodeUtil.compareWithVertifyCode(IpUtil.getIpAddress(request), userLoginParam.getVertifyCode())){
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
    @ApiOperation(value = "注册")
    @PostMapping("register")
    @ResponseBody
    public BaseResult<String> register(@RequestBody UserRegisterParam userRegisterParam, HttpServletRequest request){
        if(!vertifyCodeUtil.compareWithVertifyCode(IpUtil.getIpAddress(request), userRegisterParam.getVertifyCode())){
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
    @ApiOperation(value = "登出")
    @PostMapping("logout")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> logout(String userId){
        return userService.logout(Long.parseLong(userId));
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @ApiOperation(value = "删除用户")
    @PostMapping("delete")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> delete(String userId){
        return userService.delete(Long.parseLong(userId));
    }

    /**
     * 忘记密码
     * @param nick
     * @param idCard
     * @return
     */
    @ApiOperation(value = "忘记密码")
    @PostMapping("forgetPassword")
    @ResponseBody
    public BaseResult<String> forgetPassword(String nick, String idCard){
        return userService.forgetPassword(nick, idCard);
    }

    /**
     * 重置密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ApiOperation(value = "重置密码")
    @PostMapping("resetPassword")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> resetPassword(String userId, String oldPassword, String newPassword){
        return userService.resetPassword(Long.parseLong(userId), oldPassword, newPassword);
    }

    /**
     * 获取验证码
     * @return
     */
    @ApiOperation(value = "获取验证码")
    @GetMapping("vertifyCodeImage")
    @ResponseBody
    public BaseResult<String> vertifyCodeImage(HttpServletRequest request){
        String newImageBase64 = vertifyCodeUtil.getNewVertifyCode(IpUtil.getIpAddress(request));
        if(newImageBase64 == null){
            return BaseResult.failResult(BaseResultMsg.ERROR_VERTIFY_CODE_TOO_QUICK);
        }else{
            return BaseResult.successResult(BaseResultMsg.SUCCESS_OTHERS, newImageBase64);
        }
    }

    /**
     * 获取联系人
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取联系人")
    @GetMapping("/contact/get")
    @ResponseBody
    @TokenAuth
    public BaseResult<List<ContactDTO>> contactGet(String userId){
        return userService.contactGet(Long.parseLong(userId));
    }

    /**
     * 添加联系人
     * @param userId
     * @param contactCreateParam
     * @return
     */
    @ApiOperation(value = "添加联系人")
    @PostMapping("/contact/add")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> contactAdd(String userId, @RequestBody ContactCreateParam contactCreateParam){
        return userService.contactAdd(Long.parseLong(userId), contactCreateParam);
    }

    /**
     * 删除联系人
     * @param userId
     * @param contactId
     * @return
     */
    @ApiOperation(value = "删除联系人")
    @PostMapping("/contact/delete")
    @ResponseBody
    @TokenAuth
    public BaseResult<String> contactDelete(String userId, String contactId){
        return userService.contactDelete(Long.parseLong(contactId));
    }
}
