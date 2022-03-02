package org.unknown100name.ecommerce.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unknown100name.ecommerce.pojo.entity.CategoryTwo;

import java.util.List;

/**
 * @author unknown100name
 * @description 获取目录结构
 * @since 2022.02.28
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

    /**
     * 一层目录 ID
     */
    private Long categoryOneId;

    /**
     *  一层目录名
     */
    private String categoryOneName;

    /**
     * 二层目录详情
     */
    private List<CategoryTwo> categoryTwoList;
}
