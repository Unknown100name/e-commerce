package org.unknown100name.ecommerce.pojo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 购物车单品 DTO
 * @since 2022/1/3
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InnerShoppingCarDTO implements Serializable{

    private static final long serialVersionUID = 4319520379756324346L;

    private Long id;

    /**
     * 商品
     */
    private InnerItemDTO innerItemDTO;

    /**
     * 个数
     */
    private Integer number;
}
