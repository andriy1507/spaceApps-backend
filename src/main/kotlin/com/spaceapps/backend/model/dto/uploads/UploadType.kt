package com.spaceapps.backend.model.dto.uploads

import com.fasterxml.jackson.annotation.JsonProperty

enum class UploadType {
    @JsonProperty("image")
    Image,

    @JsonProperty("audio")
    Audio,

    @JsonProperty("video")
    Video,

    @JsonProperty("file")
    File
}