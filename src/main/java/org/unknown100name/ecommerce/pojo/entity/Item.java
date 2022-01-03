package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 大类商品
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item implements Serializable {

    private static final long serialVersionUID = -4206571300185568603L;

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 详情页
     */
    private String H5Base64;

    /**
     * 商品状态
     * 0: 已暂存
     * 1: 已审核
     * 2: 已上线
     * 3: 待下线(已下线, 但还有订单未完成)
     * 4: 已下线(权限等同于已审核)
     * 5: 已删除
     */
    private Integer state;

    /**
     * 商家 Id
     */
    private Long shopId;

    /**
     * 销量
     */
    private Long sellCount;

}
