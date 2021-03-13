package com.spaceapps.backend.model.dto.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatConversationRequest(
    @JsonProperty("name")
    val name: String
)