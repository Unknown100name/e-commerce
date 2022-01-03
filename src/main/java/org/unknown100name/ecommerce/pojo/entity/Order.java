package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;

import org.unknown100name.ecommerce.pojo.vo.ShoppingCarTurnOrderParam;
import org.unknown100name.ecommerce.util.IdUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 订单
 * @since 2022/1/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order implements Serializable {

    public Order(ShoppingCarTurnOrderParam shoppingCarTurnOrderParam) {
        this.id = IdUtil.getId();
        this.time = System.currentTimeMillis();
        this.contactId = Long.parseLong(shoppingCarTurnOrderParam.getContactId());
    }

    private static final long serialVersionUID = -3882218761828782921L;

    private Long id;

    /**
     * 订单下单时间
     */
    private Long time;

    /**
     * 联系地址 {@link Contact#id}
     */
    private Long contactId;

}
