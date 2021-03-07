package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthorizationRequest(
    @JsonProperty("email")
    val email: String,
    @JsonProperty("password")
    val password: String,
    @JsonProperty("device")
    val device: DeviceRequest
)
