package org.unknown100name.ecommerce.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author unknown100name
 * @description 细分商品 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InnerItemDTO implements Serializable {

    private static final long serialVersionUID = 8715815067829731131L;

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
     * 细分商品主图
     */
    private String ImageBase64;

    /**
     * 库存
     * -1 为无限量
     */
    private Integer inventory;

}
