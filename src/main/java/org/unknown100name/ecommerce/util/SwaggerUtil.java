package org.unknown100name.ecommerce.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author unknown100name
 * @since 2022.03.09
 * @description http://127.0.0.1:8081/e-commerce/swagger-ui/index.html
 */
@Configuration
public class SwaggerUtil {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("unknown100name")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.unknown100name.ecommerce"))
                .paths(PathSelectors.any())
                .build();
    }


    @SuppressWarnings("all")
    public ApiInfo apiInfo(){
        return new ApiInfo(
                "e-commerce",
                "A simple e-commerce system for my graduation design",
                "v1.0",
                "unknown100name@outlook.com",
                "zouhaojie",
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0"
        );
    }
}
