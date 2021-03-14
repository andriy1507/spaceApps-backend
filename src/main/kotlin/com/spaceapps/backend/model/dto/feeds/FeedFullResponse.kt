package com.spaceapps.backend.model.dto.feeds

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class FeedFullResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("items")
    val items: List<FeedItemDto>,
    @JsonProperty("created")
    val created: LocalDateTime
)