package org.unknown100name.ecommerce.pojo.vo;

import java.math.BigDecimal;

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
public class InnerItemCreateParam {
    
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
