package com.spaceapps.backend.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ForgotPasswordRequest(
        @JsonProperty("token")
        val token: String,
        @JsonProperty("newPassword")
        val newPassword: String,
        @JsonProperty("email")
        val email:String
)