package org.unknown100name.ecommerce.util;

import org.springframework.stereotype.Component;

/**
 * TODO：验证码生成
 * @author unknown100name
 * @description 验证码工具类
 * @since 2022/1/3
 */
@Component
public class VertifyCodeUtil {

    /**
     * 根据 IP 生成验证码
     * @param ip
     * @return
     */
    public final String getNewVertifyCode(String ip){
        return null;
    }

    /**
     * 对比验证码
     * @param ip
     * @param inputResult
     * @return 返回 true 表示审核通过
     */
    public final boolean compareWithVertifyCode(String ip, String inputResult){
        return true;
    }
}
