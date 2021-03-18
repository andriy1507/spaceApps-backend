package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty

data class DeviceRequest(
    @JsonProperty("token")
    val token: String,
    @ApiModelProperty(allowableValues = "android, ios")
    @JsonProperty("platform")
    val platform: Platform
) {
    enum class Platform {

        @JsonProperty("android")
        Android,

        @JsonProperty("ios")
        Ios
    }
}