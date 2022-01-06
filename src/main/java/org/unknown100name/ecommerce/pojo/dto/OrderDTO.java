package org.unknown100name.ecommerce.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unknown100name.ecommerce.pojo.entity.InnerOrder;

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
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 2064977188194288778L;

    private Long id;

    /**
     * 订单下单时间
     */
    private Long time;

    /**
     * 联系地址
     */
    private ContactDTO contactDTO;

    /**
     * 细分商品列表
     */
    private List<InnerOrder> innerOrderList;
}
