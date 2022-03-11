package org.unknown100name.ecommercebackend.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.unknown100name.ecommercebackend.pojo.vo.InnerItemCreateParam;
import util.IdUtil;

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
@TableName("inner_item")
public class InnerItem implements Serializable {

    public InnerItem(Item insertItem, InnerItemCreateParam innerItemCreateParam) {
        this.id = IdUtil.getId();
        this.typeName = innerItemCreateParam.getTypeName();
        this.price = innerItemCreateParam.getPrice();
        this.inventory = innerItemCreateParam.getInventory();
        this.itemId = insertItem.getId();
    }

    private static final long serialVersionUID = 9022119600667721349L;

    @TableId
    private Long id;

    /**
     * 商品 id
     */
    @TableField(value = "item_id")
    private Long itemId;

    /**
     * 种类名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 单价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 库存
     * -1 为无限量
     */
    @TableField(value = "inventory")
    private Integer inventory;
}
