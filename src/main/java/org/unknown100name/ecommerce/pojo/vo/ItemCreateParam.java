package org.unknown100name.ecommerce.pojo.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @description 商品添加与更新需要参数
 * @since 2022/1/6
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemCreateParam {

    /**
     * 不需要传, 在 params 里面会复制
     */
    private Long userId;
    
    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    private String subTitle;

    /**
     * 详情页
     */
    private String H5Base64;

    /**
     * 内部商品
     */
    private List<InnerItemCreateParam> innerItemCreateParam;

    /**
     * 大目录分类
     */
    private Long categoryOneId;

    /**
     * 小目录分类
     */
    private Long categoryTwoId;
}
