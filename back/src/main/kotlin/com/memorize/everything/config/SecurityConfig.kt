package com.memorize.everything.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.memorize.everything.common.security.filter.MemorizeAuthFilter
import com.memorize.everything.common.security.handler.AuthFailureHandler
import com.memorize.everything.common.security.handler.AuthSuccessHandler
import com.memorize.everything.common.security.provider.MemorizeAuthProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class SecurityConfig(
    private val memorizeAuthProvider: MemorizeAuthProvider,
    private val memorizeAuthFilter: MemorizeAuthFilter
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeHttpRequests { authz ->
                authz
                    .anyRequest().authenticated()
                    .and()
                    .addFilterAfter(memorizeAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
                    .authenticationProvider(memorizeAuthProvider)
                    .formLogin()
                    .loginProcessingUrl("/perform_login")
                    .successHandler(authSuccessHandler())
                    .failureHandler(authFailureHandler())
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
        return http.build()
    }

    @Bean
    fun authSuccessHandler(): AuthSuccessHandler {
        return AuthSuccessHandler(objectMapper())
    }

    @Bean
    fun authFailureHandler(): AuthFailureHandler {
        return AuthFailureHandler(objectMapper())
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
    }
}