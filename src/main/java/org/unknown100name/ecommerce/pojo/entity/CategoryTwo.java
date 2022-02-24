package org.unknown100name.ecommerce.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 二级类目
 * @since 2022.02.21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("category_two")
public class CategoryTwo implements Serializable {

    private static final long serialVersionUID = -6835386399011050200L;
    /**
     * 目录 ID
     */
    @TableField(value = "category_two_id")
    private Long categoryTwoId;

    /**
     * 目录名称
     */
    @TableField(value = "category_two_name")
    private Long categoryTwoName;

    /**
     * TODO:
     */
    @TableField(value = "category_two_record")
    private String categoryTwoRecord;
}
