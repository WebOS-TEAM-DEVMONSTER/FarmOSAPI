package com.example.farm.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        // API 기본 정보 설정
        Info info = new Info()
                .version("1.0.0")
                .title("FarmOS API")
                .description("FarmOS API 문서입니다.");

        // SecurityRequirement 설정
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Authorization");

        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes("Authorization",
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER));

        return new OpenAPI()
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}