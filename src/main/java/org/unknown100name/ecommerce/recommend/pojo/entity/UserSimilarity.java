package org.unknown100name.ecommerce.recommend.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description 用于存储用户之间的相似度
 *
 * @author unknown100name
 * @since 2022.02.21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSimilarity implements Serializable {

    private static final long serialVersionUID = -7209905158832415144L;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 关联用户 ID
     */
    private Long userRefId;

    /**
     * 相似度
     */
    private Double similarity;

}