package org.unknown100name.ecommercebackend.pojo.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 购物车单品 DTO
 * @since 2022/1/3
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "购物车子项")
public class InnerShoppingCarDTO implements Serializable{

    private static final long serialVersionUID = 4319520379756324346L;

    @ApiModelProperty(value = "购物车子项 ID", notes = "innerShoppingId")
    private Long id;

    /**
     * 商品
     */
    @ApiModelProperty(value = "商品子项详情")
    private InnerItemDTO innerItemDTO;

    /**
     * 个数
     */
    @ApiModelProperty(value = "购买个数")
    private Integer number;
}
