package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.unknown100name.ecommerce.pojo.vo.InnerItemCreateParam;
import org.unknown100name.ecommerce.util.IdUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 细分商品
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InnerItem implements Serializable {

    public InnerItem(Item insertItem, InnerItemCreateParam innerItemCreateParam) {
        this.id = IdUtil.getId();
        this.typeName = innerItemCreateParam.getTypeName();
        this.price = innerItemCreateParam.getPrice();
        this.inventory = innerItemCreateParam.getInventory();
        this.itemId = insertItem.getId();
    }

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

    /**
     * 商品 id
     */
    private Long itemId;
}
