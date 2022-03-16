package org.unknown100name.ecommercebackend.pojo.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 子订单 用于一个大订单{@link org.unknown100name.ecommercebackend.pojo.entity.Order}中包含每个商品的子订单
 * @since 2022/1/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "子订单")
public class InnerOrderDTO {

    @ApiModelProperty(value = "子订单 ID(innerOrderId)")
    private Long id;

    /**
     * 大订单 Id {@link org.unknown100name.ecommercebackend.pojo.entity.Order#id}
     */
    @ApiModelProperty(value = "大订单 ID(orderId)")
    private Long orderId;

    /**
     * -1: 订单已取消
     * 0: 待付款
     * 1: 已付款 (商家待确认)
     * 2: 已发货 (买家待确认)
     * 3: 已收货 (买家已确认)
    //  * 5: 待退款 (买家申请退款)
    //  * 6: 待发货 (商家确认退款)
    //  * 7: 已发货 (商家待确认)
    //  * 8: 已确认 (商家已确认)
     */
    @ApiModelProperty(value = "订单状态(-1: 订单已取消\n0: 待付款\n1: 已付款 (商家待确认)\n2: 已发货 (买家待确认)\n3: 已收货 (买家已确认)\n)")
    private Integer state;

    /**
     * 细分商品 Id {@link org.unknown100name.ecommercebackend.pojo.entity.InnerItem#id}
     */
    @ApiModelProperty(value = "细分商品 Id(innerItemId)")
    private Long innerItemId;

    /**
     * 细分商品图片
     */
    @ApiModelProperty(value = "细分商品图片 Base64")
    private String innerItemBase64;

    /**
     * 商品名
     */
    @ApiModelProperty(value = "细分商品名")
    private String title;

    /**
     * 购买数量
     */
    @ApiModelProperty(value = "购买数量")
    private Integer number;

    /**
     * 购买单价
     */
    @ApiModelProperty(value = "购买单价")
    private BigDecimal singlePrice;

    /**
     * 商家快递号
     */
    @ApiModelProperty(value = "商家快递号")
    private String expressCode;

    /**
     * 退货快递号
     */
    @ApiModelProperty(value = "退货快递号")
    private String backExpressCode;
}
