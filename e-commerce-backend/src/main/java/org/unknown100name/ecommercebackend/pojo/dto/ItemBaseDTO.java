package org.unknown100name.ecommercebackend.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 商品 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "商品基础信息")
public class ItemBaseDTO implements Serializable {

    private static final long serialVersionUID = 7025692889188132757L;

    @ApiModelProperty(value = "商品 ID", notes = "itemId")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 副标题
     */
    @ApiModelProperty(value = "副标题")
    private String subTitle;

    /**
     * 商品状态
     * 0: 已暂存
     * 1: 已审核
     * 2: 已上线
     * 3: 待下线(已下线, 但还有订单未完成)
     * 4: 已下线(权限等同于已审核)
     * 5: 已删除
     */
    @ApiModelProperty(value = "商品状态", notes = "0: 已暂存\n1: 已审核\n2: 已上线\n3: 待下线(已下线, 但还有订单未完成)\n4: 已下线(权限等同于已审核)\n5: 已删除\n")
    private Integer state;

    /**
     * 商家
     */
    @ApiModelProperty(value = "商家")
    private UserBaseDTO shop;

    /**
     * 销量
     */
    @ApiModelProperty(value = "销量")
    private Long sellCount;

    /**
     * 主图
     */
    @ApiModelProperty(value = "主图", notes = "base64")
    private String mainImageBase64;

    /**
     * 一级目录分类
     */
    @ApiModelProperty(value = "一级目录 ID", notes = "categoryOneId")
    private Long categoryOneId;

    /**
     * 二级目录分类
     */
    @ApiModelProperty(value = "二级目录 ID", notes = "categoryTwoId")
    private Long categoryTwoId;

}
