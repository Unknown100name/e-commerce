package org.unknown100name.ecommerce.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 联系地址 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContactDTO implements Serializable {

    private static final long serialVersionUID = -5408950146885023832L;

    private Long id;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 联系人
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;
}
