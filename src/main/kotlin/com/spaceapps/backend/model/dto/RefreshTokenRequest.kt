package com.spaceapps.backend.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RefreshTokenRequest(
    @JsonProperty("refreshToken")
    val refreshToken: String
)