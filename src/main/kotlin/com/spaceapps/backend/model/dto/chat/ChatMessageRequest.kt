package com.spaceapps.backend.model.dto.chat

import com.fasterxml.jackson.annotation.JsonProperty

data class ChatMessageRequest(
    @JsonProperty("text")
    val messageText: String
)