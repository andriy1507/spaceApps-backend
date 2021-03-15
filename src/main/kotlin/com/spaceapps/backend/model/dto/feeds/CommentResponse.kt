package com.spaceapps.backend.model.dto.feeds

import com.fasterxml.jackson.annotation.JsonProperty

data class CommentResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("text")
    val text: String,
    @JsonProperty("likesCount")
    val likesCount: Int,
    @JsonProperty("isLiked")
    val isLiked: Boolean
)