package org.unknown100name.ecommerce.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 用户 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 6091986662827621269L;

    private Long id;

    /**
     * 昵称 / 店名
     */
    private String nick;

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
     * null = 商家
     */
    private Integer gender;
}
