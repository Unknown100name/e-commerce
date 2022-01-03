package org.unknown100name.ecommerce.pojo.entity;

import java.io.Serializable;

/**
 * @author unknown100name
 * @description 联系地址 用于快递等信息
 * @since 2022/1/2
 */
public class Contact implements Serializable {

    private static final long serialVersionUID = -6501129330167468579L;

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
