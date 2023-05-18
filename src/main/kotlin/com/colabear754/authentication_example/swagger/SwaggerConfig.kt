package com.colabear754.authentication_example.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

private const val SECURITY_SCHEME_NAME = "authorization"

@Configuration
class SwaggerConfig {
    @Bean
    fun swaggerApi(): OpenAPI = OpenAPI()
        .components(Components()
            .addSecuritySchemes(SECURITY_SCHEME_NAME, SecurityScheme()
                .name(SECURITY_SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")))
        .addSecurityItem(SecurityRequirement().addList(SECURITY_SCHEME_NAME))
        .info(Info()
            .title("스프링시큐리티 + JWT 예제")
            .description("스프링시큐리티와 JWT를 이용한 사용자 인증 예제입니다.")
            .version("1.0.0"))
}