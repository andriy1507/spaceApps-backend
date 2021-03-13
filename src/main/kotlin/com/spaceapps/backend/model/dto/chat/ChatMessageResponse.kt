package com.spaceapps.backend.model.dto.chat

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ChatMessageResponse(
    @JsonProperty("messageId")
    val messageId: String,
    @JsonProperty("conversationId")
    val conversationId: String,
    @JsonProperty("messageText")
    val messageText: String,
    @JsonProperty("dateTime")
    val dateTime: LocalDateTime
)