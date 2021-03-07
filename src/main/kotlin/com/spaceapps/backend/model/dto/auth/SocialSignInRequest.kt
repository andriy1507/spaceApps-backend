package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class SocialSignInRequest(
    @JsonProperty("accessToken")
    val accessToken: String,
    @JsonProperty("device")
    val device: DeviceRequest
)