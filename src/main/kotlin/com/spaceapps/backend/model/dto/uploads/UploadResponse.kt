package com.spaceapps.backend.model.dto.uploads

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty

data class UploadResponse(
    @JsonProperty("name")
    val name: String,
    @ApiModelProperty(allowableValues = "image, audio, video, file")
    @JsonProperty("type")
    val type: UploadType,
    @JsonProperty("url")
    val url: String
)