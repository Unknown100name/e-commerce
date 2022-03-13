package util;

import org.springframework.data.redis.core.script.DigestUtils;

/**
 * @author unknown100name
 * @description SHA1 转换自动生成类
 * @since 2022/1/3
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public final class SHA1Util {

    private SHA1Util(){
        throw new IllegalAccessError("Utility class");
    }
    
    /**
     * 字符串转 SHA1
     * @param preStr 待转义字符串
     * @return SHA1 字符串
     */
    public static String encodeToSha1(String preStr){
         return DigestUtils.sha1DigestAsHex(preStr);
    }
}
