package com.spaceapps.backend.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthorizationRequest(
        @JsonProperty("email")
        val email: String,
        @JsonProperty("password")
        val password: String,
        @JsonProperty("device")
        val device: Device
) {
    data class Device(
            @JsonProperty("token")
            val token: String,
            @JsonProperty("platform")
            val platform: Platform
    ) {
        enum class Platform {
            @JsonProperty("android")
            Android,
            @JsonProperty("ios")
            Ios
        }
    }
}