package com.spaceapps.backend.model.dto.auth.social

import com.fasterxml.jackson.annotation.JsonProperty

data class FacebookSignInResponse(
    @JsonProperty("email")
    val email: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("id")
    val id: String
)