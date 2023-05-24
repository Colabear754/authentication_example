package com.colabear754.authentication_example.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@EnableMethodSecurity
@Configuration
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val entryPoint: AuthenticationEntryPoint,
    @Value("\${security.allowed-uris}")
    private val allowedUris: Array<String>
) {
    @Bean
    fun filterChain(http: HttpSecurity) = http
        .csrf().disable()
        .headers { it.frameOptions().sameOrigin() }
        .authorizeHttpRequests {
            it.requestMatchers(*allowedUris).permitAll()
                .anyRequest().authenticated()
        }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter::class.java)
        .exceptionHandling { it.authenticationEntryPoint(entryPoint) }
        .build()!!

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}