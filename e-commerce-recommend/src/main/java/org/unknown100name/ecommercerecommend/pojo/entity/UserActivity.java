package org.unknown100name.ecommercerecommend.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 用户点击行为日志
 *
 * @author unknown100name
 * @since 2022.02.21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity implements Serializable {

    private static final long serialVersionUID = 8367093023551003377L;

    /**
     * 用户 ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 二级目录 ID
     */
    @TableField(value = "category_two_id")
    private Long categoryTwoId;

    /**
     * 点击量
     */
    @TableField(value = "hits")
    private Long hits;
}
