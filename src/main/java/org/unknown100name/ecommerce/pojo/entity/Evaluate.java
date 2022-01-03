package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author unknown100name
 * @description 评价
 * @since 2022/1/2
 */
public class Evaluate implements Serializable {

    private static final long serialVersionUID = -4514631523163089574L;

    private Long id;

    /**
     * 评论时间
     */
    private Long time;

    /**
     * 评论分数
     * 0-10
     * 0-1:非常差
     * 2-3:差
     * 4-6:可以
     * 7-8:优秀
     * 9-10:完美
     *
     * 0-3: 差评
     * 4-6: 中评
     * 7-10: 好评
     */
    private Integer type;

    /**
     * 评论内容
     */
    private String content;
}
