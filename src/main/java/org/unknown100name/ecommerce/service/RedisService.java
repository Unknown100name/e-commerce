package org.unknown100name.ecommerce.service;

import org.springframework.stereotype.Service;

/**
 * @author unknown100name
 * @description redis操作Service, 对象和数组都以json形式进行存储
 * @since 2022/1/2
 */
@Service
public interface RedisService {

    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

    /**
     * 检测是否超时
     */
    boolean valid(String key);

    /**
     * 检测是否有目标key
     */
    boolean hasKey(String key);
}