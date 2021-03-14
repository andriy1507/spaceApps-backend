package com.spaceapps.backend.model.dto.uploads

import com.fasterxml.jackson.annotation.JsonProperty

data class UploadResponse(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("type")
    val type: UploadType,
    @JsonProperty("url")
    val url: String
)