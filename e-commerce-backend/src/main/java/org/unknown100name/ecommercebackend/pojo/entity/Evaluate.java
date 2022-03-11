package org.unknown100name.ecommercebackend.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.unknown100name.ecommercebackend.pojo.vo.EvaluateCreateParam;
import util.IdUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 评价
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("evaluate")
public class Evaluate implements Serializable {

    public Evaluate(EvaluateCreateParam evaluateGiveParam) {
        this.id = IdUtil.getId();
        this.content = evaluateGiveParam.getContent();
        this.type = evaluateGiveParam.getType();
        this.time = System.currentTimeMillis();
    }

    private static final long serialVersionUID = -4514631523163089574L;

    @TableId
    private Long id;

    /**
     * 评价商品 ID
     */
    @TableField(value = "inner_item_id")
    private Long innerItemId;

    /**
     * 评论时间
     */
    @TableField(value = "time")
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
    @TableField(value = "type")
    private Integer type;

    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;
}
