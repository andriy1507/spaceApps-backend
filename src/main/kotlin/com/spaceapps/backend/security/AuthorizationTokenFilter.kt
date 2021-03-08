package com.spaceapps.backend.security

import com.spaceapps.backend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
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
//        if (!request.headerNames.toList().any { it.equals(AUTH_TOKEN_HEADER, true) }) return clearSecurityContext()
//        val header = request.getHeader(AUTH_TOKEN_HEADER)
//        val token = header.substringAfter(AUTH_TOKEN_PREFIX, "")
//        if (token.isBlank()) return clearSecurityContext()
//        val userName = JsonWebTokenUtils.validateAuthorizationToken(token) ?: return clearSecurityContext()
//        val user = userRepository.getByEmail(userName) ?: return clearSecurityContext()
//        authenticate(user, token)
        filterChain.doFilter(request, response)
    }

//    private fun authenticate(user: UserEntity, token: String) {
//        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(user, token, null)
//    }
//
//    private fun clearSecurityContext() {
//        SecurityContextHolder.getContext().authentication?.isAuthenticated = false
//    }
}