package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class AuthorizationTokenResponse(
    @JsonProperty("auth_token")
    val authToken: String,
    @JsonProperty("auth_token_exp")
    val authTokenExp: LocalDateTime,
    @JsonProperty("refresh_token")
    val refreshToken: String,
    @JsonProperty("refresh_token_exp")
    val refreshTokenExp: LocalDateTime
)