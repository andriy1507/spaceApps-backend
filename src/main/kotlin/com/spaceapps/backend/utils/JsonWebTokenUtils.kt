package com.spaceapps.backend.utils

import com.fasterxml.jackson.annotation.JsonProperty
import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dto.auth.AuthorizationTokenResponse
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.Keys
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

object JsonWebTokenUtils {

    private const val TOKEN_TYPE = "type"

    fun generateAuthTokenResponse(user: ApplicationUser): AuthorizationTokenResponse {
        val now = LocalDateTime.now()
        val keys = Keys.secretKeyFor(SignatureAlgorithm.HS256)
        val jwtBuilder = Jwts.builder().signWith(keys)
            .setSubject(user.email)
            .setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
            .serializeToJsonWith(JacksonSerializer())
        val authExp = now.plusDays(1)
        val authToken = jwtBuilder
            .setExpiration(Date.from(authExp.toInstant(ZoneOffset.UTC)))
            .setClaims(mapOf(TOKEN_TYPE to TokenType.Authorization))
            .compact()
        val refreshExp = now.plusMonths(1)
        val refreshToken = jwtBuilder
            .setExpiration(Date.from(refreshExp.toInstant(ZoneOffset.UTC)))
            .setClaims(mapOf(TOKEN_TYPE to TokenType.Refresh))
            .compact()
        return AuthorizationTokenResponse(
            authToken = authToken,
            authTokenExp = authExp,
            refreshToken = refreshToken,
            refreshTokenExp = refreshExp
        )
    }

    private enum class TokenType {
        @JsonProperty("authorization")
        Authorization,
        @JsonProperty("refresh")
        Refresh
    }

}