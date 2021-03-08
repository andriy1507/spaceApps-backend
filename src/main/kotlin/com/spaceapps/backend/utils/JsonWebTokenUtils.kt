package com.spaceapps.backend.utils

import com.fasterxml.jackson.annotation.JsonProperty
import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dto.auth.AuthorizationRequest
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
    private const val DEVICE_TOKEN = "device_token"
    private const val PLATFORM = "platform"
    private const val ISSUER = "spaceapps.com"
    private const val USER_NAME = "user_name"
    private const val USER_ID = "user_id"

    fun generateAuthTokenResponse(user: ApplicationUser, request: AuthorizationRequest): AuthorizationTokenResponse {
        val now = LocalDateTime.now()
        val keys = Keys.secretKeyFor(SignatureAlgorithm.HS256)
        val jwtBuilder = Jwts.builder().signWith(keys)
            .setSubject(user.email)
            .setIssuer(ISSUER)
            .setClaims(mapOf<String, Any>(
                DEVICE_TOKEN to request.device.token,
                PLATFORM to request.device.platform,
                USER_NAME to user.email,
                USER_ID to user.id
            ))
            .setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
            .serializeToJsonWith(JacksonSerializer())
        val authExp = now.plusDays(1)
        val authToken = jwtBuilder
            .setExpiration(Date.from(authExp.toInstant(ZoneOffset.UTC)))
            .setHeader(mapOf(TOKEN_TYPE to TokenType.Authorization))
            .compact()
        val refreshExp = now.plusMonths(1)
        val refreshToken = jwtBuilder
            .setExpiration(Date.from(refreshExp.toInstant(ZoneOffset.UTC)))
            .setHeader(mapOf(TOKEN_TYPE to TokenType.Refresh))
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