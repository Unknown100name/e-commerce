package org.unknown100name.ecommerce.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @since 2022.03.04
 *
 * @description 创建联系人参数
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactCreateParam implements Serializable {

    private static final long serialVersionUID = -7098383705555876400L;
    /**
     * 联系地址
     */
    private String address;

    /**
     * 联系名
     */
    private String name;

    /**
     * 
     */
    private String phone;
}
