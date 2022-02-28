package org.unknown100name.ecommerce.service;

import org.springframework.stereotype.Service;

/**
 * @author unknown100name
 * @description Redis 映射
 * @since 2022/1/9
 */
@Service
public interface RedisService {

    /**
     * 确认 Token 真实性
     * @param userId
     * @param token
     * @return false 表示不匹配, true 表示匹配
     */
    boolean checkToken(Long userId, String token);
}
