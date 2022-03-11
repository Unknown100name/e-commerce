package org.unknown100name.ecommercebackend.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author unknown100name
 * @description 细分商品 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("细分商品")
public class InnerItemDTO implements Serializable {

    private static final long serialVersionUID = 8715815067829731131L;

    @ApiModelProperty(value = "细分商品ID" ,notes = "innerItemId")
    private Long id;

    /**
     * 种类名称
     */
    @ApiModelProperty(value = "细分商品种类名称")
    private String typeName;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    /**
     * 细分商品主图
     */
    @ApiModelProperty(value = "细分商品主图")
    private String imageBase64;

    /**
     * 库存
     * -1 为无限量
     */
    @ApiModelProperty(value = "库存 -1为无限量")
    private Integer inventory;

    /**
     * 主商品 ID
     */
    @ApiModelProperty(value = "主商品 ID")
    private Long itemId;

}
