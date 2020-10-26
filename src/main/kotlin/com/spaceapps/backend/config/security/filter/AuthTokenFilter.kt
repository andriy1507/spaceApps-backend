package com.spaceapps.backend.config.security.filter

import com.spaceapps.backend.config.security.token.AuthTokenProvider
import com.spaceapps.backend.services.ApplicationUserDetailsService
import com.spaceapps.backend.utils.LOGGER
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.stereotype.Component
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthTokenFilter(
        manager: AuthenticationManager,
        private val userDetailsService: ApplicationUserDetailsService,
        private val authTokenProvider: AuthTokenProvider
) : BasicAuthenticationFilter(manager) {

    companion object {
        private const val TOKEN_HEADER = "authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(TOKEN_HEADER)
        try {
            if (!header.isNullOrBlank()) {
                val userName = authTokenProvider.getUserName(header.substringAfter(TOKEN_PREFIX))
                userDetailsService.loadUserByUsername(userName)?.let {userDetails ->
                    SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userDetails.username, userDetails.password, null)
                }
            }
        } catch (e: Exception) {
            SecurityContextHolder.getContext().authentication = null
            LOGGER.error(e.localizedMessage)
        }
        chain.doFilter(request, response)
    }
}