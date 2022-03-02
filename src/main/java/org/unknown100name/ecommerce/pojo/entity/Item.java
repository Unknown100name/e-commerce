package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("item")
public class Item implements Serializable {

    private static final long serialVersionUID = -4206571300185568603L;

    @TableField(value = "id")
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 副标题
     */
    @TableField(value = "sub_title")
    private String subTitle;

    /**
     * 详情页
     */
    @TableField(value = "h5_base64")
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
    @TableField(value = "state")
    private Integer state;

    /**
     * 商家 Id
     */
    @TableField(value = "shop_id")
    private Long shopId;

    /**
     * 销量
     */
    @TableField(value = "sell_count")
    private Long sellCount;

    /**
     * 大目录分类
     */
    @TableField(value = "category_one_id")
    private Long categoryOneId;

    /**
     * 小目录分类
     */
    @TableField(value = "category_two_id")
    private Long categoryTwoId;

    /**
     * 商品点击量
     */
    @TableField(value = "hits")
    private Long hits;

    public Item(ItemCreateParam itemCreateParam) {
        this.id = IdUtil.getId();
        this.title = itemCreateParam.getTitle();
        this.subTitle = itemCreateParam.getSubTitle();
        this.H5Base64 = itemCreateParam.getH5Base64();
        this.state = 0;
        this.sellCount = 0L;
        this.shopId = itemCreateParam.getUserId();
        this.categoryOneId = itemCreateParam.getCategoryOneId();
        this.categoryTwoId = itemCreateParam.getCategoryTwoId();
    }
}
