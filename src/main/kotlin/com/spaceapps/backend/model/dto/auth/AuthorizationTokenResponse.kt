package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class AuthorizationTokenResponse(
    @JsonProperty("authToken")
    val authToken: String,
    @JsonProperty("authTokenExp")
    val authTokenExp: LocalDateTime,
    @JsonProperty("refreshToken")
    val refreshToken: String,
    @JsonProperty("refreshTokenExp")
    val refreshTokenExp: LocalDateTime
)