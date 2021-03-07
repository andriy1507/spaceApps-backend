package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class DeviceRequest(
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