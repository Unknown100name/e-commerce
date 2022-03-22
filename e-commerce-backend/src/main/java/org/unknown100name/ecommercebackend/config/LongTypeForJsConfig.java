package org.unknown100name.ecommercebackend.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author unknown100name
 * @since 2022.03.17
 *
 * @description 由于 Long 与 Js 的 Number 之间存在问题， 在这里统一进行转换
 */
@Configuration
public class LongTypeForJsConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return jacksonObjectMapperBuilder -> {
            jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
            // Jackson全局转换long类型为String，解决jackson序列化时long类型缺失精度问题
            // 自定义Long类型转换 超过12个数字用String格式返回，由于js的number只能表示15个数字
            jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance);
//            jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
//            jacksonObjectMapperBuilder.failOnUnknownProperties(false);
//            jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        };
    }
}
