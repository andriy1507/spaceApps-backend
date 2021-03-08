package com.spaceapps.backend.model.dto.feeds

import com.fasterxml.jackson.annotation.JsonProperty

data class FeedRequest(
    @JsonProperty("title")
    val title: String,
    @JsonProperty("items")
    val items: List<FeedItemDto>
)