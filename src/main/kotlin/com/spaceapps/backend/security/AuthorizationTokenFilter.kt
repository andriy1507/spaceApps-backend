package com.spaceapps.backend.security

import com.spaceapps.backend.AUTH_TOKEN_HEADER
import com.spaceapps.backend.AUTH_TOKEN_PREFIX
import com.spaceapps.backend.model.dao.auth.UserEntity
import com.spaceapps.backend.repository.UserRepository
import com.spaceapps.backend.utils.JsonWebTokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthorizationTokenFilter @Autowired constructor(
    private val userRepository: UserRepository
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (!request.headerNames.toList().any { it.equals(AUTH_TOKEN_HEADER, true) }) {
            return clearContext(request, response, filterChain)
        }
        val header = request.getHeader(AUTH_TOKEN_HEADER)
        val token = header.substringAfter(AUTH_TOKEN_PREFIX, "")
        if (token.isBlank()) return clearContext(request, response, filterChain)
        val userName =
            JsonWebTokenUtils.validateAuthorizationToken(token) ?: return clearContext(request, response, filterChain)
        val user = userRepository.getByEmail(userName) ?: return clearContext(request, response, filterChain)
        authenticate(user)
        filterChain.doFilter(request, response)
    }

    private val securityContext: SecurityContext
        get() = SecurityContextHolder.getContext()

    private fun clearContext(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        SecurityContextHolder.clearContext()
        filterChain.doFilter(request, response)
    }

    private fun authenticate(user: UserEntity) {
        securityContext.authentication =
            UsernamePasswordAuthenticationToken(User(user.email, user.password, emptyList()), null, emptyList())
    }
}