package com.spaceapps.backend.model.dto.feeds

import com.fasterxml.jackson.annotation.JsonProperty

data class CommentRequest(
    @JsonProperty("text")
    val text: String
)