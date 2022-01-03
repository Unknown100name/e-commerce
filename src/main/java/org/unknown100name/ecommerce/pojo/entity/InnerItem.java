package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author unknown100name
 * @description 细分商品
 * @since 2022/1/2
 */
public class InnerItem implements Serializable {

    private static final long serialVersionUID = 9022119600667721349L;

    private Long id;

    /**
     * 种类名称
     */
    private String typeName;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 库存
     * -1 为无限量
     */
    private Integer inventory;

}
