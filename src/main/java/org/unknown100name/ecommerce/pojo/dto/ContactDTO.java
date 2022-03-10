package org.unknown100name.ecommerce.pojo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 联系地址 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "联系方式")
public class ContactDTO implements Serializable {

    private static final long serialVersionUID = -5408950146885023832L;

    @ApiModelProperty(value = "联系方式 ID", notes = "contactId")
    private Long id;

    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    private String address;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人名")
    private String name;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系人")
    private String phone;
}
