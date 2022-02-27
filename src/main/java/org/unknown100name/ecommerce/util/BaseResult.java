package org.unknown100name.ecommerce.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author unknown100name
 * @param <T> 数据类型
 * @description 基础结果类
 * @since 2022/1/3
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseResult<T> {
    
    private String msg;

    private Boolean success;

    private T data;

    public static <T> BaseResult<T> failResult(String msg){
        return new BaseResult<>(msg, false, null);
    }

    public static <T> BaseResult<T> successResult(String msg, T data){
        return new BaseResult<>(msg, true, data);
    }
}
