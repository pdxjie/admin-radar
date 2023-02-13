package com.pdx.radar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: 派大星
 * @Date: 2023/02/14 2023/2/14
 * @Description: Swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createCustomApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pdx.radar.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("雷达权限管理系统接口文档")
                .version("v1.0")
                .description("雷达权限管理系统接口文档")
                .contact(new Contact("派大星","http://localhost:8088/doc.html","pdx_jie@163.com"))
                .build();
    }
}
