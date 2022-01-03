package org.unknown100name.ecommerce.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 用户登录参数
 * @since 2022/1/3
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLoginParam {
    
    /**
     * 昵称
     */
    private String nick;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String vertifyCode;

}
