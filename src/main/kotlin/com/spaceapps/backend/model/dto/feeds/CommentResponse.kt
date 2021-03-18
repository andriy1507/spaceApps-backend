package com.spaceapps.backend.model.dto.feeds

import com.fasterxml.jackson.annotation.JsonProperty

data class CommentResponse(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("text")
    val text: String,
    @JsonProperty("likes_count")
    val likesCount: Int,
    @JsonProperty("liked")
    val isLiked: Boolean
)