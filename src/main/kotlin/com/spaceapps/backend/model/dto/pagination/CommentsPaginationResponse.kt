package com.spaceapps.backend.model.dto.pagination

import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.feeds.CommentResponse

class CommentsPaginationResponse(
    data: List<CommentResponse>,
    total: Long,
    page: Int,
    isLast: Boolean
) : PaginationResponse<CommentResponse>(data, total, page, isLast)