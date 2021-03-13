package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

data class SendResetTokenRequest(
    @JsonProperty("email")
    val email: String
)