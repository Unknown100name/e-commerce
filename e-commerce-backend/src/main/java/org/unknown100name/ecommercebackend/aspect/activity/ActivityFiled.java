package org.unknown100name.ecommercebackend.aspect.activity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author unknown100name
 * @since 2022.02.28
 * @description 行为日志字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ActivityFiled {

    ActivitySource source() default ActivitySource.NONE;
}
