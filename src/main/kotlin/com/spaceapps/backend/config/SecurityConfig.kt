package com.spaceapps.backend.config

import com.spaceapps.backend.UNAUTHORIZED
import com.spaceapps.backend.security.AuthorizationTokenFilter
import com.spaceapps.backend.security.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig @Autowired constructor(
    private val userDetailsService: MyUserDetailsService,
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        super.configure(auth)
        auth.userDetailsService(userDetailsService)
    }

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
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(AuthorizationTokenFilter(authenticationManager()), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    fun authenticationEntryPoint() = AuthenticationEntryPoint { _, response, _ ->
        response.sendError(HttpStatus.UNAUTHORIZED.value(), UNAUTHORIZED)
    }
}