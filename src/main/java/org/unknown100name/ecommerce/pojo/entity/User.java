package org.unknown100name.ecommerce.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import org.unknown100name.ecommerce.pojo.vo.UserRegisterParam;
import org.unknown100name.ecommerce.util.IdUtil;

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
     * 密码 SHA1
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

    public User(UserRegisterParam userRegisterParam){
        this.id = IdUtil.getId();
        this.nick = userRegisterParam.getNick();
        this.password = userRegisterParam.getPassword();
        this.phone = userRegisterParam.getPhone();
        this.type = userRegisterParam.getType();
        this.gender = userRegisterParam.getGender();
        this.realName = userRegisterParam.getRealName();
        this.idCard = userRegisterParam.getIdCard();
        this.delete = false;
    }
}
