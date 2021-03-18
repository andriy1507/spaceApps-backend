package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class ResetPasswordRequest(
    @JsonProperty("reset_token")
    val resetToken: String,
    @JsonProperty("new_password")
    val newPassword: String,
    @JsonProperty("email")
    val email: String
)