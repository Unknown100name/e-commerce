package org.unknown100name.ecommerce.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "用户")
public class UserBaseDTO implements Serializable {

    private static final long serialVersionUID = 6091986662827621269L;

    @ApiModelProperty(value = "用户 ID", notes = "userId")
    private Long id;

     /**
     * nickname
     */
     @ApiModelProperty(value = "用户昵称")
    private String nick;

    /**
     * 用户种类
     * 0 = 商家
     * 1 = 买家
     * 2 = 管理员
     */
    @ApiModelProperty(value = "用户类型", notes = "0 = 商家\n1 = 买家\n2 = 管理员")
    private Integer type;

    /**
     * 性别
     * 0 = male
     * 1 = female
     */
    @ApiModelProperty(value = "用户性别", notes = "0 = male\n1 = female")
    private Integer gender;
    
}
