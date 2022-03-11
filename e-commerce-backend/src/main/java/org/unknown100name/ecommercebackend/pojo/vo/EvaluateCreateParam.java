package org.unknown100name.ecommercebackend.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 添加评论参数
 * @since 2022/1/3
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "添加评论参数")
public class EvaluateCreateParam {
    
    /**
     * 子订单 ID
     */
    @ApiModelProperty(value = "子订单 ID", required = true)
    private String innerOrderId;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容", required = true)
    private String content;

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
    @ApiModelProperty(value = "评论分数", required = true, notes = "从0-10的整数类型")
    private Integer type;

}
