package com.spaceapps.backend.model.dto.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatResponse(
    @JsonProperty("id")
    val conversationId: String,
    @JsonProperty("name")
    val name: String
)