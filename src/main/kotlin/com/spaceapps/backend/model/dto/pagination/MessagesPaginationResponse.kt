package com.spaceapps.backend.model.dto.pagination

import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.chat.ChatMessageResponse

class MessagesPaginationResponse(
    data: List<ChatMessageResponse>,
    total: Long,
    page: Int,
    isLast: Boolean
) : PaginationResponse<ChatMessageResponse>(data, total, page, isLast)