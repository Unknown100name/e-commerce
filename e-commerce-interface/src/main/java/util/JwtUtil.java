package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author unknown100name
 * @since 2022.03.06
 *
 * @description JWT 工具类
 */
public final class JwtUtil {

    /**
     * 过期时间 15分钟
     */

    public static final long EXPIRE_TIME = 15* 60 * 1000;
    /**
     * 私钥
     */
    private static final String TOKEN_SECRET = "17337b82cf0b3bbabedcdafa011e2aa4";

    /**
     * 生成签名，15分钟过期
     * 根据内部改造，支持6中类型，Integer,Long,Boolean,Double,String,Date
     * @param map
     * @return
     */
    public static String sign(String userId, Map<String,Object> map) {
        try{
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("typ", "jwt");
            // 返回token字符串
            JWTCreator.Builder builder =  JWT.create()
                    .withHeader(header)
                    // 颁发证书时间
                    .withIssuedAt(new Date())
                    // 过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME));
            if (map != null && !map.isEmpty()){
                map.forEach((key, value) -> {
                    if (value instanceof Integer) {
                        builder.withClaim(key, (Integer) value);
                    } else if (value instanceof Long) {
                        builder.withClaim(key, (Long) value);
                    } else if (value instanceof Boolean) {
                        builder.withClaim(key, (Boolean) value);
                    } else if (value instanceof String) {
                        builder.withClaim(key, String.valueOf(value));
                    } else if (value instanceof Double) {
                        builder.withClaim(key, (Double) value);
                    } else if (value instanceof Date) {
                        builder.withClaim(key, (Date) value);
                    }
                });
            }
            builder.withClaim("userId", userId);
            return builder.sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 检验token是否正确
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     *获取用户自定义Claim集合
     * @param token
     * @return
     */
    public static Map<String, Claim> getClaims(String token){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token).getClaims();
    }

    /**
     * 获取过期时间
     * @param token
     * @return
     */
    public static Date getExpiresAt(String token){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        return  JWT.require(algorithm).build().verify(token).getExpiresAt();
    }

    /**
     * 获取jwt发布时间
     */
    public static Date getIssuedAt(String token){
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
        return  JWT.require(algorithm).build().verify(token).getIssuedAt();
    }

    /**
     * 验证token是否失效
     *
     * @param token
     * @return true:过期   false:没过期
     */
    public static boolean isExpired(String token) {
        try {
            final Date expiration = getExpiresAt(token);
            return expiration.before(new Date());
        }catch (TokenExpiredException e) {
            return true;
        }

    }

    /**
     * 直接Base64解密获取header内容
     * @param token
     * @return
     */
    public static String getHeaderByBase64(String token){
        if (StringUtils.isEmpty(token)){
            return null;
        }else {
            byte[] header_byte = Base64.getDecoder().decode(token.split("\\.")[0]);
            String header = new String(header_byte);
            return header;
        }

    }

    /**
     * 直接Base64解密获取payload内容
     * @param token
     * @return
     */
    public static String getPayloadByBase64(String token) {

        if (StringUtils.isEmpty(token)) {
            return null;
        } else {
            byte[] payload_byte = Base64.getDecoder().decode(token.split("\\.")[1]);
            return new String(payload_byte);
        }
    }

    /**
     * 根据 Token 获取 userId
     *
     * @param token
     * @return userId
     */
    public static Long getUserId(String token) {
        Map<String, Claim> claims = getClaims(token);
        Claim userIdClaim = claims.get("userId");
        return Long.valueOf(userIdClaim.asString());
    }
}
