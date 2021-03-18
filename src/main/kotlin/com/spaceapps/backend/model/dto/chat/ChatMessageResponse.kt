package com.spaceapps.backend.model.dto.chat

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ChatMessageResponse(
    @JsonProperty("id")
    val messageId: String,
    @JsonProperty("conversation_id")
    val conversationId: String,
    @JsonProperty("text")
    val messageText: String,
    @JsonProperty("date_time")
    val dateTime: LocalDateTime
)