package org.unknown100name.ecommerce.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import lombok.experimental.Accessors;
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
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 2777754074075530985L;

    @TableId
    private Long id;

    /**
     * nickname
     */
    @TableField(value = "nick")
    private String nick;

    /**
     * 密码 SHA1
     */
    @TableField(value = "password")
    private String password;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户种类
     * 0 = 商家
     * 1 = 买家
     * 2 = 管理员
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 性别
     * 0 = male
     * 1 = female
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 真实姓名
     */
    @TableField(value = "real_name")
    private String realName;

    /**
     * 身份证号
     */
    @TableField(value = "id_card")
    private String idCard;

    /**
     * 是否注销
     * false = 未删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    public User(UserRegisterParam userRegisterParam){
        this.id = IdUtil.getId();
        this.nick = userRegisterParam.getNick();
        this.password = userRegisterParam.getPassword();
        this.phone = userRegisterParam.getPhone();
        this.type = userRegisterParam.getType();
        this.gender = userRegisterParam.getGender();
        this.realName = userRegisterParam.getRealName();
        this.idCard = userRegisterParam.getIdCard();
        this.deleted = false;
    }
}
