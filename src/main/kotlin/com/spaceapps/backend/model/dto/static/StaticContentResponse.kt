package com.spaceapps.backend.model.dto.static

import com.fasterxml.jackson.annotation.JsonProperty

data class StaticContentResponse(
    @JsonProperty("content")
    val content: String
)