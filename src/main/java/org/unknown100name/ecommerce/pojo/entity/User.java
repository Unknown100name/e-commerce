package org.unknown100name.ecommerce.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 用户
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 2777754074075530985L;

    private Long id;

    /**
     * nickname
     */
    private String nick;

    /**
     * 密码 MD5
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
     * 是否注销
     */
    private Boolean delete;
}
