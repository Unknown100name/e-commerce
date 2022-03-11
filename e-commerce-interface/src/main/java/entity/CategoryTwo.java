package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "二级目录")
public class CategoryTwo implements Serializable {

    private static final long serialVersionUID = -6835386399011050200L;

    /**
     * 二级目录 ID
     */
    @TableId
    @ApiModelProperty(value = "二级目录 ID", notes = "categoryTwoId")
    private Long categoryTwoId;

    /**
     * 二级目录名称
     */
    @TableField(value = "category_two_name")
    @ApiModelProperty(value = "二级目录名称")
    private String categoryTwoName;

    /**
     * 一级目录 ID
     */
    @TableField(value = "category_one_id")
    @ApiModelProperty(value = "一级目录 ID", notes = "categoryOneId")
    private Long categoryOneId;
}
