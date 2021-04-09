package com.lisy.config;

import com.lisy.utils.ResultCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lisy
 * @Date: 2021/4/7
 * @version: 1.0
 * @Description: ""
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket cerateRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lisy.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts()).securitySchemes(securitySchemes());
    }

    private List<? extends SecurityScheme> securitySchemes() {
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization", "Authorization","Header");
        result.add(apiKey);
        return  result;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> resutl = new ArrayList<>();
        resutl.add(getContextPath("/.*"));
        return resutl;
    }

    private SecurityContext getContextPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new  AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", authorizationScopes));
        return result;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理系统接口文档")
                .description("后台系统接口文档")
                .contact(new Contact("Lisy", "http://127.0.0.1:8080/doc.html","1320199607@qq.com"))
                .version("2021.04.08.1.0")
                .build();
    }
}
