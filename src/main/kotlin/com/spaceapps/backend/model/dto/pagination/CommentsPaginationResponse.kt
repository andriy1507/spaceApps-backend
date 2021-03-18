package com.spaceapps.backend.model.dto.pagination

import com.fasterxml.jackson.annotation.JsonProperty
import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.feeds.CommentResponse

class CommentsPaginationResponse(
    @JsonProperty("data")
    data: List<CommentResponse>,
    @JsonProperty("total")
    total: Long,
    @JsonProperty("page")
    page: Int,
    @JsonProperty("last")
    isLast: Boolean
) : PaginationResponse<CommentResponse>(data, total, page, isLast)