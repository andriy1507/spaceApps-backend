package com.spaceapps.backend.config.security.filter

import com.spaceapps.backend.config.security.token.AuthTokenProvider
import com.spaceapps.backend.services.ApplicationUserDetailsService
import com.spaceapps.backend.utils.LOGGER
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthTokenFilter @Autowired constructor(
        private val userDetailsService: ApplicationUserDetailsService,
        private val authTokenProvider: AuthTokenProvider
) : OncePerRequestFilter() {

    companion object {
        private const val TOKEN_HEADER = "authorization"
        private const val TOKEN_PREFIX = "Bearer "
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(TOKEN_HEADER)
        try {
            if (!header.isNullOrBlank()) {
                val userName = authTokenProvider.getUserName(header.substringAfter(TOKEN_PREFIX))
                userDetailsService.loadUserByUsername(userName)?.let { userDetails ->
                    SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(userDetails, userDetails.password, null)
                }
            }
        } catch (e: Exception) {
            SecurityContextHolder.getContext().authentication?.isAuthenticated = false
            LOGGER.error(e.localizedMessage)
        } finally {
            chain.doFilter(request, response)
        }
    }
}