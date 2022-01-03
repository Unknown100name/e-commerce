package org.unknown100name.ecommerce.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 字典
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 4487752385312578927L;

    private Long id;

    private String code;

    private String parentCode;

    private String content;
}
