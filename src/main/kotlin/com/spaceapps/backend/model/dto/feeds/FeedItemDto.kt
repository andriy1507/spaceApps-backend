package com.spaceapps.backend.model.dto.feeds

import com.fasterxml.jackson.annotation.JsonProperty

data class FeedItemDto(
    @JsonProperty("text")
    val text: String
)