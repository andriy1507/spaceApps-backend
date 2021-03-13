package com.spaceapps.backend.model.dto.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatConversationResponse(
    @JsonProperty("conversationId")
    val conversationId: String,
    @JsonProperty("name")
    val name: String
)