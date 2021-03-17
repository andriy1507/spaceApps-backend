package com.spaceapps.backend.model.dto.pagination

import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.chat.ChatConversationResponse

class ConversationsPaginationResponse(
    data: List<ChatConversationResponse>,
    total: Long,
    page: Int,
    isLast: Boolean
) : PaginationResponse<ChatConversationResponse>(data, total, page, isLast)