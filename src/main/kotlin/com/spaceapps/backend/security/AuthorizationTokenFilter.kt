package com.spaceapps.backend.security

import com.spaceapps.backend.model.dao.auth.UserEntity
import com.spaceapps.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
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
//        if (!request.headerNames.toList().any { it.equals(AUTH_TOKEN_HEADER, true) }) return SecurityContextHolder.clearContext()
//        val header = request.getHeader(AUTH_TOKEN_HEADER)
//        val token = header.substringAfter(AUTH_TOKEN_PREFIX, "")
//        if (token.isBlank()) return SecurityContextHolder.clearContext()
//        val userName =
//            JsonWebTokenUtils.validateAuthorizationToken(token) ?: return SecurityContextHolder.clearContext()
//        val user = userRepository.getByEmail(userName) ?: return SecurityContextHolder.clearContext()
//        authenticate(user, token)
        filterChain.doFilter(request, response)
    }

    private val securityContext: SecurityContext
        get() = SecurityContextHolder.getContext()

    private fun authenticate(user: UserEntity, token: String) {
        securityContext.authentication = UsernamePasswordAuthenticationToken(user, token, null)
    }
}