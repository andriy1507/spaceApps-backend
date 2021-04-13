package com.spaceapps.backend.model.dto.tools

import com.fasterxml.jackson.annotation.JsonProperty

data class MetaDataResponse(
    @JsonProperty("title")
    val name: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("url")
    val url: String,
    @JsonProperty("image")
    val image: String
)