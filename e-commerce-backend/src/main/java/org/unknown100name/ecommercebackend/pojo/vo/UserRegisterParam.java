package org.unknown100name.ecommercebackend.pojo.vo;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 用户注册参数
 * @since 2022/1/3
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "用户注册参数")
public class UserRegisterParam {

    /**
     * nickname
     */
    @ApiModelProperty(value = "用户昵称")
    private String nick;

    /**
     * 密码 -> 密码 SHA1
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 用户种类
     * 0 = 商家
     * 1 = 买家
     * 2 = 管理员
     */
    @ApiModelProperty(value = "用户种类", notes = "0 = 商家\n1 = 买家\n2 = 管理员")
    private Integer type;

    /**
     * 性别
     * 0 = male
     * 1 = female
     */
    @ApiModelProperty(value = "性别", notes = "0:male\n1:female")
    private Integer gender;

    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    private String realName;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String vertifyCode;
}
