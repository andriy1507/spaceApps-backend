package com.spaceapps.backend.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationTokenFilter(authenticationManager: AuthenticationManager) : BasicAuthenticationFilter(authenticationManager) {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        filterChain.doFilter(request, response)
    }
}