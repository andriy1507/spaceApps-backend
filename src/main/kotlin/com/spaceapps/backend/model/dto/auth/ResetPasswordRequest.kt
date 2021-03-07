package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class ResetPasswordRequest(
    @JsonProperty("resetToken")
    val resetToken: String,
    @JsonProperty("newPassword")
    val newPassword: String,
    @JsonProperty("email")
    val email: String
)