package org.unknown100name.ecommercebackend.aspect.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author unknown100name
 * @since 2022.02.28
 * @description Token 验证， 严格要求有 userId 在请求里面
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TokenAuth {
    
}
