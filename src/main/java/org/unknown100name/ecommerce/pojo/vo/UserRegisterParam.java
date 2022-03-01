package org.unknown100name.ecommerce.pojo.vo;

import com.sun.istack.internal.NotNull;
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
public class UserRegisterParam {

    /**
     * nickname
     */
    private String nick;

    /**
     * 密码 -> 密码 SHA1
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户种类
     * 0 = 商家
     * 1 = 买家
     * 2 = 管理员
     */
    private Integer type;

    /**
     * 性别
     * 0 = male
     * 1 = female
     */
    private Integer gender;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 验证码
     */
    private String vertifyCode;
}
