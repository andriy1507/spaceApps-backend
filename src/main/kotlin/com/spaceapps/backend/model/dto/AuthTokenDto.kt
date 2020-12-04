package com.spaceapps.backend.model.dto

import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDateTime

data class AuthTokenDto(
        @SerializedName("authToken")
        val authToken: String,
        @SerializedName("authTokenExp")
        val authTokenExp: LocalDateTime,
        @SerializedName("refreshToken")
        val refreshToken: String,
        @SerializedName("refreshTokenExp")
        val refreshTokenExp: LocalDateTime
)