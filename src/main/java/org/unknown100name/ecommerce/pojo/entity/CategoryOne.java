package org.unknown100name.ecommerce.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 一级类目
 * @since 2022.02.21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("category_one")
public class CategoryOne implements Serializable {

    private static final long serialVersionUID = -2223907096459130998L;
    /**
     * 目录 ID
     */
    @TableField(value = "category_one_id")
    private Long categoryOneId;

    /**
     * 目录名称
     */
    @TableField(value = "category_one_name")
    private Long categoryOneName;

    /**
     * TODO:
     */
    @TableField(value = "category_one_record")
    private String categoryOneRecord;
}
