package com.spaceapps.backend.model.dto.feeds

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class FeedShortResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("created")
    val created: LocalDateTime
)