package org.unknown100name.ecommerce.pojo.vo;

import java.util.List;

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
    private String userId;

    /**
     * 添加为订单的列表
     */
    private List<String> innerShoppingCarId;

    /**
     * 联系人 Id
     */
    private String contactId;
}
