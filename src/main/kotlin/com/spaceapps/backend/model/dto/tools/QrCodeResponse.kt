package com.spaceapps.backend.model.dto.tools

import com.fasterxml.jackson.annotation.JsonProperty

data class QrCodeResponse(
    @JsonProperty("encoded_image")
    val encodedImage: String,
    @JsonProperty("width")
    val width: Int,
    @JsonProperty("height")
    val height: Int
)