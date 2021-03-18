package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class VerifyResetTokenRequest(
    @JsonProperty("email")
    val email: String,
    @JsonProperty("reset_token")
    val resetToken: String
)