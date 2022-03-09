package org.unknown100name.ecommerce.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @description 购物车
 *
 * @author unknown100name
 * @since 2022.02.25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("shopping_car")
public class ShoppingCar {

    @TableId
    private Long id;

    /**
     * 所属人
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 商品 ID
     */
    @TableField(value = "inner_item_id")
    private Long innerItemId;

    /**
     * 数量
     */
    @TableField(value = "number")
    private Integer number;
}
