package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author unknown100name
 * @description 订单
 * @since 2022/1/2
 */
public class Order implements Serializable {

    private static final long serialVersionUID = -3882218761828782921L;

    private Long id;

    /**
     * 订单下单时间
     */
    private Timestamp time;

    /**
     * 联系地址 {@link Contact#id}
     */
    private Long contactId;

}
