package com.colabear754.authentication_example.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
class SecurityConfig {
    private val allowedUrls = arrayOf("/", "/swagger-ui/**", "/v3/**", "/sign-up", "/sign-in")

    @Bean
    fun filterChain(http: HttpSecurity) = http
        .csrf().disable()
        .authorizeHttpRequests {
            it.requestMatchers(*allowedUrls).permitAll()
                .anyRequest().authenticated()
        }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .build()!!
}