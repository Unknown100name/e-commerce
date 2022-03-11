package entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId
    private Long categoryOneId;

    /**
     * 目录名称
     */
    @TableField(value = "category_one_name")
    private Long categoryOneName;
}
