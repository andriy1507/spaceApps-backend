package com.spaceapps.backend.config

import com.spaceapps.backend.security.AuthorizationTokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig @Autowired constructor(
    private val authorizationTokenFilter: AuthorizationTokenFilter
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
            .addFilterBefore(authorizationTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/chat-socket").permitAll()
            .antMatchers("/test").permitAll()
            .antMatchers("/static/**").permitAll()
            .antMatchers("/calc/**").permitAll()
            .antMatchers("/calc").permitAll()
            .antMatchers(
                "/swagger-ui/**",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/webjars/**"
            ).permitAll()
            .antMatchers("/actuator/**").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}