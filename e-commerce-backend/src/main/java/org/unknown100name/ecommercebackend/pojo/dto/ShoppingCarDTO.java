package org.unknown100name.ecommercebackend.pojo.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unknown100name.ecommercebackend.pojo.dto.InnerShoppingCarDTO;

/**
 * @author unknown100name
 * @description 商品 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "购物车")
public class ShoppingCarDTO implements Serializable{

    private static final long serialVersionUID = -4975714059880996359L;

    @ApiModelProperty(value = "用户 ID", notes = "userId")
    private Long userId;

    @ApiModelProperty(value = "购物车子项")
    private List<InnerShoppingCarDTO> innerShoppingCarList;
    
}
