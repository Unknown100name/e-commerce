package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import org.unknown100name.ecommerce.pojo.vo.ShoppingCarTurnOrderParam;
import org.unknown100name.ecommerce.util.IdUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 订单
 * @since 2022/1/2
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("order")
public class Order implements Serializable {

    public Order(ShoppingCarTurnOrderParam shoppingCarTurnOrderParam) {
        this.id = IdUtil.getId();
        this.time = System.currentTimeMillis();
        this.contactId = Long.parseLong(shoppingCarTurnOrderParam.getContactId());
    }

    private static final long serialVersionUID = -3882218761828782921L;

    private Long id;

    /**
     * 订单下单时间
     */
    @TableField(value = "time")
    private Long time;

    /**
     * 联系地址 {@link Contact#id}
     */
    @TableField(value = "contact_id")
    private Long contactId;

}
