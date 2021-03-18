package com.spaceapps.backend.model.dto.pagination

import com.fasterxml.jackson.annotation.JsonProperty
import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.chat.ChatMessageResponse

class MessagesPaginationResponse(
    @JsonProperty("data")
    data: List<ChatMessageResponse>,
    @JsonProperty("total")
    total: Long,
    @JsonProperty("page")
    page: Int,
    @JsonProperty("last")
    isLast: Boolean
) : PaginationResponse<ChatMessageResponse>(data, total, page, isLast)