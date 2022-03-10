package org.unknown100name.ecommerce.pojo.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 商品转订单参数
 * @since 2022/1/3
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShoppingCarTurnOrderParam {
    
    /**
     * 用户 Id
     */
    @ApiModelProperty(value = "用户 Id", required = true, allowEmptyValue = false)
    private String userId;

    /**
     * 添加为订单的列表
     */
    @ApiModelProperty(value = "添加为订单的列表(innerShoppingCarId 的列表)", required = true, allowEmptyValue = false)
    private List<String> innerShoppingCarId;

    /**
     * 联系人 Id
     */
    @ApiModelProperty(value = "用户的联系人 Id", required = true, allowEmptyValue = false)
    private String contactId;
}
