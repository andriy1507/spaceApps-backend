package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class ResetPasswordRequest(
        @JsonProperty("token")
        val token: String,
        @JsonProperty("newPassword")
        val newPassword: String,
        @JsonProperty("email")
        val email:String
)