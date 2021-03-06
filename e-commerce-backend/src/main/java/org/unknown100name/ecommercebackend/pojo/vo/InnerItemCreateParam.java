package org.unknown100name.ecommercebackend.pojo.vo;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 商品添加与更新需要参数
 * @since 2022/1/6
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiOperation(value = "细分商品参数")
public class InnerItemCreateParam {

    /**
     * InnerItemId
     */
    @ApiModelProperty(value = "细分商品id，创造的时候不需要传，更新的时候需要")
    private Long id;
    
    /**
     * 种类名称
     */
    @ApiModelProperty(value = "细分商品名称")
    private String typeName;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

    /**
     * 库存
     * -1 为无限量
     */
    @ApiModelProperty(value = "库存(-1 为无限量)")
    private Integer inventory;

    /**
     * 主图
     */
    @ApiModelProperty(value = "细分商品图(base64)")
    private String imageBase64;
}
