package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;

import org.unknown100name.ecommerce.pojo.vo.ItemCreateParam;
import org.unknown100name.ecommerce.util.IdUtil;

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

    public Item(ItemCreateParam itemCreateParam) {
        this.id = IdUtil.getId();
        this.title = itemCreateParam.getTitle();
        this.subTitle = itemCreateParam.getSubTitle();
        this.H5Base64 = itemCreateParam.getH5Base64();
        this.shopId = Long.parseLong(itemCreateParam.getUserId());
        this.state = 0;
        this.sellCount = 0L;
    }

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
     * 1: 待审核
     * 2: 已审核
     * 3: 已上线
     * 4: 待下线(已下线, 但还有订单未完成)
     * 5: 已下线(权限等同于已审核)
     * 6: 已删除
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
