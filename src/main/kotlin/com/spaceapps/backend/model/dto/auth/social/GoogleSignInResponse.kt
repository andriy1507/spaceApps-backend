package com.spaceapps.backend.model.dto.auth.social

import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleSignInResponse(
    @JsonProperty("email")
    val email: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("sub")
    val id: String
)