package org.unknown100name.ecommercebackend.pojo.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 商品添加与更新需要参数
 * @since 2022/1/6
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "创造商品参数")
public class ItemCreateParam {

    /**
     * 不需要传, 在 params 里面会复制
     */
    @ApiModelProperty(value = "用户 ID(不需要传),  在 params 里面会复制")
    private Long userId;

    /**
     * ItemId
     */
    @ApiModelProperty(value = "itemId, 创造的时候不需要传，更新的时候需要")
    private Long id;
    
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 副标题
     */
    @ApiModelProperty(value = "福标题")
    private String subTitle;

    /**
     * 详情页
     */
    @ApiModelProperty(value = "详情页图片(base64)")
    private String h5Base64;

    /**
     * 主图
     */
    @ApiModelProperty(value = "主图(base64)")
    private String mainImageBase64;

    /**
     * 商品图册
     */
    @ApiModelProperty(value = "商品图册(base64)")
    private List<String> imageListBase64;

    /**
     * 细分商品
     */
    @ApiModelProperty(value = "细分商品")
    private List<InnerItemCreateParam> innerItemCreateParam;

    /**
     * 一级目录 ID
     */
    @ApiModelProperty(value = "一级目录 ID(categoryOneId)")
    private Long categoryOneId;

    /**
     * 二级目录 ID
     */
    @ApiModelProperty(value = "二级目录 ID(categoryTwoId)")
    private Long categoryTwoId;
}
