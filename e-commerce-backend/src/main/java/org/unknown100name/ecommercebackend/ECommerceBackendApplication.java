package org.unknown100name.ecommercebackend;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;


/**
 * 后台启动类
 * @author unknown100name
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
@SpringBootApplication
@EnableOpenApi
@EnableKnife4j
public class ECommerceBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceBackendApplication.class, args);
    }

}
