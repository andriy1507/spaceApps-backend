package com.spaceapps.backend.model.dto.pagination

import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.feeds.FeedShortResponse

class FeedsPaginationResponse(
    data: List<FeedShortResponse>,
    total: Long,
    page: Int,
    isLast: Boolean
) : PaginationResponse<FeedShortResponse>(data, total, page, isLast)