package org.unknown100name.ecommerce.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author unknown100name
 * @description 商品 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDetailDTO implements Serializable {

    private static final long serialVersionUID = 7025692889188132757L;

    private Long id;

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
     * 商品状态
     * 0: 已暂存
     * 1: 已审核
     * 2: 已上线
     * 3: 待下线(已下线, 但还有订单未完成)
     * 4: 已下线(权限等同于已审核)
     * 5: 已删除
     */
    private String state;

    /**
     * 商家
     */
    private UserDTO shop;

    /**
     * 销量
     */
    private Long sellCount;

    /**
     * 主图
     */
    private String mainImageBase64;

    /**
     * 商品图册
     */
    private List<String> ImageListBase64;

    /**
     * 细分商品列表
     */
    private List<InnerItemDTO> innerItemList;

    /**
     * 评价列表
     */
    private List<EvaluateDTO> evaluateList;

}
