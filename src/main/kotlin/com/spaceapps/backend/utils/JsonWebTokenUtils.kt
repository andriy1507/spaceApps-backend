package com.spaceapps.backend.utils

import com.spaceapps.backend.model.dao.auth.UserEntity
import com.spaceapps.backend.model.dto.auth.AuthorizationTokenResponse
import com.spaceapps.backend.model.dto.auth.DeviceRequest
import io.jsonwebtoken.IncorrectClaimException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MissingClaimException
import io.jsonwebtoken.jackson.io.JacksonDeserializer
import io.jsonwebtoken.jackson.io.JacksonSerializer
import io.jsonwebtoken.security.Keys
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

object JsonWebTokenUtils {

    private const val TOKEN_TYPE = "type"
    private const val TOKEN_TYPE_AUTH = "authorization"
    private const val TOKEN_TYPE_REFRESH = "refresh"
    private const val DEVICE_TOKEN = "device_token"
    private const val PLATFORM = "platform"
    private const val ISSUER = "spaceapps.com"
    private const val USER_NAME = "user_name"
    private const val USER_ID = "user_id"
    private const val USER_TYPE = "user_type"
    private val SIGN_SECRET = "15ab0c7de1f9gh9815ab0c7de1f9gh9815ab0c7de1f9gh98".toByteArray()

    @Throws(MissingClaimException::class, IncorrectClaimException::class)
    fun validateAuthorizationToken(token: String): String? {
        val parser = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SIGN_SECRET))
            .deserializeJsonWith(JacksonDeserializer())
            .build()
        val claims = parser.parseClaimsJws(token)
        if (claims.body.expiration.before(Date())) return null
        if (claims.header[TOKEN_TYPE] != TOKEN_TYPE_AUTH) return null
        return claims.body.get(USER_NAME, String::class.java)
    }

    @Throws(MissingClaimException::class, IncorrectClaimException::class)
    fun validateRefreshToken(token: String):String? {
        val parser = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SIGN_SECRET))
            .deserializeJsonWith(JacksonDeserializer())
            .build()
        val claims = parser.parseClaimsJws(token)
        if (claims.body.expiration.before(Date())) return null
        if (claims.header[TOKEN_TYPE] != TOKEN_TYPE_REFRESH) return null
        return claims.body.get(USER_NAME, String::class.java)
    }

    fun generateAuthTokenResponse(user: UserEntity, device: DeviceRequest): AuthorizationTokenResponse {
        val now = LocalDateTime.now()
        val jwtBuilder = Jwts.builder()
            .signWith(Keys.hmacShaKeyFor(SIGN_SECRET))
            .setSubject(user.email)
            .setIssuer(ISSUER)
            .setClaims(
                mapOf(
                    DEVICE_TOKEN to device.token,
                    PLATFORM to device.platform,
                    USER_NAME to user.email,
                    USER_ID to user.id,
                    USER_TYPE to user.type
                )
            )
            .setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
            .serializeToJsonWith(JacksonSerializer())
        val authExp = now.plusDays(1)
        val authToken = jwtBuilder
            .setExpiration(Date.from(authExp.toInstant(ZoneOffset.UTC)))
            .setHeader(mapOf(TOKEN_TYPE to TOKEN_TYPE_AUTH))
            .compact()
        val refreshExp = now.plusMonths(1)
        val refreshToken = jwtBuilder
            .setExpiration(Date.from(refreshExp.toInstant(ZoneOffset.UTC)))
            .setHeader(mapOf(TOKEN_TYPE to TOKEN_TYPE_REFRESH))
            .compact()
        return AuthorizationTokenResponse(
            authToken = authToken,
            authTokenExp = authExp,
            refreshToken = refreshToken,
            refreshTokenExp = refreshExp
        )
    }
}