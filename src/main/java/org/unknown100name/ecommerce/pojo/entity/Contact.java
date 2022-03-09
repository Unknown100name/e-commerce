package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unknown100name.ecommerce.pojo.vo.ContactCreateParam;
import org.unknown100name.ecommerce.util.IdUtil;

/**
 * @author unknown100name
 * @description 联系地址 用于快递等信息
 * @since 2022/1/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = -6501129330167468579L;

    @TableId
    private Long id;

    /**
     * 所属人 ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 联系地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 联系人
     */
    @TableField(value = "name")
    private String name;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    public Contact(Long userId, ContactCreateParam contactCreateParam) {
        this.id = IdUtil.getId();
        this.userId = userId;
        this.address = contactCreateParam.getAddress();
        this.phone = contactCreateParam.getPhone();
        this.name = contactCreateParam.getName();
    }
}
