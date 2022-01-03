package org.unknown100name.ecommerce.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 字典 DTO
 * @since 2022/1/2
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DictionaryDTO implements Serializable {

    private static final long serialVersionUID = 5597645556591100300L;

    private Long id;

    private String code;

    private String parentCode;

    private String content;
}
