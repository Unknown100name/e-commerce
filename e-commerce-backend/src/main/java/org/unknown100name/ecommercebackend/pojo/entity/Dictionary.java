package org.unknown100name.ecommercebackend.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 4487752385312578927L;

    @TableId
    private Long id;

    @TableField(value = "code")
    private String code;

    @TableField(value = "parent_code")
    private String parentCode;

    @TableField(value = "content")
    private String content;
}
