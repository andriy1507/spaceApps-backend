package com.spaceapps.backend.model.dto.pagination

import com.fasterxml.jackson.annotation.JsonProperty
import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.feeds.FeedShortResponse

class FeedsPaginationResponse(
    @JsonProperty("data")
    data: List<FeedShortResponse>,
    @JsonProperty("total")
    total: Long,
    @JsonProperty("page")
    page: Int,
    @JsonProperty("last")
    isLast: Boolean
) : PaginationResponse<FeedShortResponse>(data, total, page, isLast)