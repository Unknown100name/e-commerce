package org.unknown100name.ecommercebackend.pojo.dto;

import entity.CategoryTwo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author unknown100name
 * @since 2022.04.28
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel("目录菜单")
public class CategoryArrayDTO implements Serializable {

    private static final long serialVersionUID = -2267429861200505967L;

    @ApiModelProperty("一级目录")
    private List<String> categoryOneArray;

    @ApiModelProperty("一级目录 id")
    private List<Integer> categoryOneIdArray;

    @ApiModelProperty("二级目录")
    private List<List<String>> categoryTwoArray;

    @ApiModelProperty("二级目录 id")
    private List<List<Integer>> categoryTwoIdArray;

    public CategoryArrayDTO(List<CategoryDTO> category){
        categoryOneArray = new ArrayList<>();
        categoryOneIdArray = new ArrayList<>();
        categoryTwoArray = new ArrayList<>();
        categoryTwoIdArray = new ArrayList<>();

        for (CategoryDTO categoryDTO : category) {
            categoryOneArray.add(categoryDTO.getCategoryOneName());
            categoryOneIdArray.add(categoryDTO.getCategoryOneId().intValue());
            List<String> temp = new ArrayList<>();
            List<Integer> tempId = new ArrayList<>();
            for (CategoryTwo categoryTwo : categoryDTO.getCategoryTwoList()) {
                temp.add(categoryTwo.getCategoryTwoName());
                tempId.add(categoryTwo.getCategoryTwoId().intValue());
            }
            categoryTwoArray.add(temp);
            categoryTwoIdArray.add(tempId);
        }
    }

}
