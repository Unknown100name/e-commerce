package org.unknown100name.ecommercebackend.pojo.dto;

import entity.CategoryTwo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author unknown100name
 * @description 获取目录结构
 * @since 2022.02.28
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("一级目录")
public class CategoryDTO {

    /**
     * 一级目录 ID
     */
    @ApiModelProperty(value = "一级目录 ID", notes = "categoryOneId")
    private Long categoryOneId;

    /**
     *  一级目录名称
     */
    @ApiModelProperty(value = "一级目录名称")
    private String categoryOneName;

    /**
     * 二级目录详情
     */
    @ApiModelProperty(value = "二级目录详情")
    private List<CategoryTwo> categoryTwoList;
}
