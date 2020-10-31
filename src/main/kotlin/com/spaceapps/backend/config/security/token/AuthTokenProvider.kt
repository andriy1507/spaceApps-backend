package com.spaceapps.backend.config.security.token

import com.spaceapps.backend.model.dao.ApplicationUser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.joda.time.LocalDateTime
import org.springframework.stereotype.Component

@Component
class AuthTokenProvider(
        private val properties: AuthTokenProperties
) {

    fun getAuthToken(user: ApplicationUser): String {
        val key = Keys.hmacShaKeyFor(properties.secretKey)
        return Jwts.builder().setSubject(user.userName).setIssuedAt(LocalDateTime.now().toDate()).signWith(key).compact()
    }

    fun getUserName(token: String): String {
        val key = Keys.hmacShaKeyFor(properties.secretKey)
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    }

}