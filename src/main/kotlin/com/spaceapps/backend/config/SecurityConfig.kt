package com.spaceapps.backend.config

import com.spaceapps.backend.UNAUTHORIZED
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/echo-socket").permitAll()
            .antMatchers(
                "/swagger-ui/**",
                "/swagger-resources/configuration/ui",
                "/swagger-resources/configuration/security",
                "/v2/api-docs",
                "/swagger-resources"
            ).permitAll()
            .antMatchers("/actuator/**").permitAll()
            .anyRequest().authenticated()
    }

    fun authenticationEntryPoint() = AuthenticationEntryPoint { _, response, _ ->
        response.sendError(HttpStatus.UNAUTHORIZED.value(), UNAUTHORIZED)
    }
}