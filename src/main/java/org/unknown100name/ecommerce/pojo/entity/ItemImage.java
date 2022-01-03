package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 用于商品的图片显示
 * @since 2022/1/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemImage implements Serializable {

    private static final long serialVersionUID = 3090544149941738936L;

    private Long id;

    /**
     * 图片 base64
     */
    private String ImageBase64;

    /**
     * {@link Item#id} 商品图
     * {@link InnerItem#id} 细分商品图
     */
    private Long ItemId;

    /**
     * 类别
     * 0: 商品主图
     * 1: 商品图册
     * 2: InnerItem 主图
     */
    private Integer type;

    /**
     * 图册 order
     */
    private Integer order;
}
