package com.spaceapps.backend.config.security

import com.spaceapps.backend.config.security.filter.AuthTokenFilter
import com.spaceapps.backend.services.ApplicationUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig @Autowired constructor(
        private val userDetailsService: ApplicationUserDetailsService,
        private val passwordEncoder: PasswordEncoder,
        private val authTokenFilter: AuthTokenFilter
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        with(http) {
            cors().and().csrf().disable()
                    .authorizeRequests()
                    .antMatchers(
                            "/authorization/**",
                            "/swagger-ui/**",
                            "/swagger-resources/**",
                            "/swagger-ui.html",
                            "/v2/api-docs",
                            "/webjars/**"
                    )
                    .permitAll()
                    .anyRequest()
                    .permitAll()
                    .and()
                    .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder)
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration().apply {
            allowedOrigins = listOf("*")
            allowCredentials = true
            allowedMethods = listOf("*")
            allowedHeaders = listOf("*")
        }
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", config)
        }
    }
}