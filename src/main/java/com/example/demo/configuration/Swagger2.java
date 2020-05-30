package com.example.demo.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable", havingValue = "true")
public class Swagger2 {

    //refer https://www.cnblogs.com/zs-notes/p/10845741.html
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 设置为目录
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.web.ctrl"))
                .paths(PathSelectors.any())
                .build();
    }

    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("Spring Boot 使用 Swagger2 构建RESTful API")
                // 创建人信息
                .contact(new Contact("xc", "https://blog.csdn.net/m0_37561165", "672009739@qq.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("API 描述")
                .build();
    }
}
