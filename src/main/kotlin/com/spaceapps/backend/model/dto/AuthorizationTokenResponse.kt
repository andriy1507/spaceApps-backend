package com.spaceapps.backend.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class AuthorizationTokenResponse(
        @JsonProperty("authToken")
        val authToken: String,
        @JsonProperty("authTokenExp")
        val authTokenExp: LocalDateTime,
        @JsonProperty("refreshToken")
        val refreshToken: String,
        @JsonProperty("refreshTokenExp")
        val refreshTokenExp: LocalDateTime
)