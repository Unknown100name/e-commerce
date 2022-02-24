package org.unknown100name.ecommerce.recommend.pojo.entity;

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
    private Long userId;

    /**
     * 二级目录 ID
     */
    private Long categoryTwoId;

    /**
     * 点击量
     */
    private Long hits;
}
