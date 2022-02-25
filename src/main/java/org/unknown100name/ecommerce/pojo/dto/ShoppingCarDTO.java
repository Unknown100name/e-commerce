package org.unknown100name.ecommerce.pojo.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 商品 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingCarDTO implements Serializable{

    private static final long serialVersionUID = -4975714059880996359L;

    private Long userId;

    private List<InnerShoppingCarDTO> innerShoppingCarList;
    
}
