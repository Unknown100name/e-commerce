package org.unknown100name.ecommercebackend.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unknown100name.ecommercebackend.pojo.dto.ContactDTO;
import org.unknown100name.ecommercebackend.pojo.dto.InnerOrderDTO;
import org.unknown100name.ecommercebackend.pojo.entity.InnerOrder;

import java.io.Serializable;
import java.util.List;

/**
 * @author unknown100name
 * @description 订单 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "订单")
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 2064977188194288778L;

    @ApiModelProperty(value = "订单 ID", notes = "orderId")
    private Long id;

    /**
     * 订单下单时间
     */
    @ApiModelProperty(value = "订单下单时间", notes = "时间戳(ms)")
    private Long time;

    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系方式")
    private ContactDTO contactDTO;

    /**
     * 细分商品列表
     */
    @ApiModelProperty(value = "细分商品列表")
    private List<InnerOrderDTO> innerOrderList;
}
