package org.unknown100name.ecommercebackend.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("item_image")
public class ItemImage implements Serializable {

    private static final long serialVersionUID = 3090544149941738936L;

    @TableId
    private Long id;

    /**
     * 图片 base64
     */
    @TableField(value = "image_base64")
    private String imageBase64;

    /**
     * {@link Item#id} 商品图
     * {@link InnerItem#id} 细分商品图
     */
    @TableField(value = "item_id")
    private Long itemId;

    /**
     * 类别
     * 0: 商品主图
     * 1: 商品图册
     * 2: InnerItem 主图
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 图册 order
     */
    @TableField(value = "order")
    private Integer order;
}
