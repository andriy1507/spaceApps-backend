package com.spaceapps.backend.model.dto.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatConversationResponse(
    @JsonProperty("id")
    val conversationId: String,
    @JsonProperty("name")
    val name: String
)