package org.feather.config;

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
 * @program: foodie-dev
 * @description:
 * @author: 杜雪松(feather)
 * @create: 2020-04-24 09:15
 **/
@Configuration
@EnableSwagger2
public class Swagger2 {
    //配置swagger2核心配置 docket
    @Bean
    public Docket creatRestApi(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.feather.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return  new ApiInfoBuilder()
                .title("天天吃货电商平台接口")
                .contact(new Contact("杜雪松","http://localhost:8088/swagger-ui.html","Brian608@163.com"))
                .description("天天吃货电商平台文档")
                .version("1.0.1")
                .termsOfServiceUrl("http://localhost:8088/doc.html")
                .build();
    }
}
