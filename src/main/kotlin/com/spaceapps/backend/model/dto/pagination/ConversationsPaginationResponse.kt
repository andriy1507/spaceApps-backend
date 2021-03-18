package com.spaceapps.backend.model.dto.pagination

import com.fasterxml.jackson.annotation.JsonProperty
import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.chat.ChatResponse

class ConversationsPaginationResponse(
    @JsonProperty("data")
    data: List<ChatResponse>,
    @JsonProperty("total")
    total: Long,
    @JsonProperty("page")
    page: Int,
    @JsonProperty("last")
    isLast: Boolean
) : PaginationResponse<ChatResponse>(data, total, page, isLast)