package com.spaceapps.backend.model.dto.auth

import com.fasterxml.jackson.annotation.JsonProperty

enum class UserType {
    @JsonProperty("google")
    Google,

    @JsonProperty("facebook")
    Facebook,

    @JsonProperty("apple")
    Apple,

    @JsonProperty("spaceapps")
    SpaceApps
}