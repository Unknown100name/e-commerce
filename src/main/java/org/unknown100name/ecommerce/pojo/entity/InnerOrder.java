package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.unknown100name.ecommerce.pojo.dto.InnerShoppingCarDTO;
import org.unknown100name.ecommerce.util.IdUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 子订单 用于一个大订单{@link Order}中包含每个商品的子订单
 * @since 2022/1/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("inner_order")
public class InnerOrder implements Serializable {

    public InnerOrder(Order insertOrder, InnerShoppingCarDTO existInnerShoppingCar) {
        this.id = IdUtil.getId();
        this.orderId = insertOrder.getId();
        this.state = 0;
        this.innerItemId = existInnerShoppingCar.getInnerItemDTO().getId();
        this.number = existInnerShoppingCar.getNumber();
        this.singlePrice = existInnerShoppingCar.getInnerItemDTO().getPrice();
    }

    private static final long serialVersionUID = 289732166911578852L;

    @TableField(value = "id")
    private Long id;

    /**
     * 大订单 Id {@link Order#id}
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * -1: 订单已取消
     * 0: 待付款
     * 1: 已付款 (商家待确认)
     * 2: 已发货 (买家待确认)
     * 3: 已收货 (买家已确认)
    //  * 5: 待退款 (买家申请退款)
    //  * 6: 待发货 (商家确认退款)
    //  * 7: 已发货 (商家待确认)
    //  * 8: 已确认 (商家已确认)
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 细分商品 Id {@link InnerItem#id}
     */
    @TableField(value = "inner_item_id")
    private Long innerItemId;

    /**
     * 购买数量
     */
    @TableField(value = "number")
    private Integer number;

    /**
     * 购买单价
     */
    @TableField(value = "single_price")
    private BigDecimal singlePrice;

    /**
     * 商家快递号
     */
    @TableField(value = "express_code")
    private String expressCode;

    /**
     * 退货快递号
     */
    @TableField(value = "back_express_code")
    private String backExpressCode;

}
