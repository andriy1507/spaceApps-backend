package com.spaceapps.backend.config.security.token

import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dto.AuthTokenDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.joda.time.Hours
import org.joda.time.LocalDateTime
import org.joda.time.Months
import org.springframework.stereotype.Component

@Component
class AuthTokenProvider(
        private val properties: AuthTokenProperties
) {
    private val key = Keys.hmacShaKeyFor(properties.secretKey)
    private val authTokenExpiration = Hours.ONE
    private val refreshTokenExpiration = Months.ONE
    private val authorization = "authorization"
    private val refresh = "refresh"
    private val type = "type"
    private val issuer = "spaceapps.com"
    private val userId = "user_id"
    private val jwtParser = Jwts.parserBuilder().setSigningKey(key).build()

    fun getAuthTokenDto(user: ApplicationUser): AuthTokenDto {
        val auth = getAuthTokenAndExpirationDate(user)
        val refresh = getRefreshTokenAndExpirationDate(user)
        return AuthTokenDto(auth.first, auth.second, refresh.first, refresh.second)
    }

    private fun getAuthTokenAndExpirationDate(user: ApplicationUser): Pair<String, LocalDateTime> {
        val now = LocalDateTime.now()
        val expiration = now + authTokenExpiration
        val token = Jwts.builder().setSubject(user.userName)
                .setIssuedAt(now.toDate())
                .setIssuer(issuer)
                .setExpiration(expiration.toDate())
                .addClaims(mapOf(type to authorization, userId to user.id))
                .signWith(key).compact()
        return token to expiration
    }

    private fun getRefreshTokenAndExpirationDate(user: ApplicationUser): Pair<String, LocalDateTime> {
        val now = LocalDateTime.now()
        val expiration = now + refreshTokenExpiration
        val token = Jwts.builder().setSubject(user.userName)
                .setIssuedAt(now.toDate())
                .setIssuer(issuer)
                .addClaims(mapOf(type to refresh, userId to user.id))
                .setExpiration(expiration.toDate())
                .signWith(key).compact()
        return token to expiration
    }

    fun getUserName(token: String): String {
        val key = Keys.hmacShaKeyFor(properties.secretKey)
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body.subject
    }

    fun isAuthTokenValid(token: String): Boolean {
        return with(jwtParser.parseClaimsJws(token).body) {
            val expires = LocalDateTime.fromDateFields(expiration)
            val issued = LocalDateTime.fromDateFields(issuedAt)
            val now = LocalDateTime.now()
            val isAuthToken = get(type, String::class.java) == authorization
            val isIssuerValid = issuer == this@AuthTokenProvider.issuer
            expires.isAfter(now) && isAuthToken && issued.isBefore(now) && isIssuerValid
        }
    }

    fun isRefreshTokenValid(token: String): String? {
        return with(jwtParser.parseClaimsJws(token).body) {
            val expires = LocalDateTime.fromDateFields(expiration)
            val issued = LocalDateTime.fromDateFields(issuedAt)
            val now = LocalDateTime.now()
            val isRefreshToken = get(type, String::class.java) == refresh
            val isIssuerValid = issuer == this@AuthTokenProvider.issuer
            if (expires.isAfter(now) && isRefreshToken && issued.isBefore(now) && isIssuerValid) subject else null
        }
    }
}