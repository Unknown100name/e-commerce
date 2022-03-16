package org.unknown100name.ecommercebackend.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 评价 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(value = "评论")
public class EvaluateDTO implements Serializable {

    private static final long serialVersionUID = -4514631523163089574L;

    @ApiModelProperty(value = "评论 ID(evaluateId)")
    private Long id;

    /**
     * 评论时间
     */
    @ApiModelProperty(value = "评论时间(时间戳(ms))")
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
    @ApiModelProperty(value = "评论分数(0-10整数)")
    private Integer type;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;
}
