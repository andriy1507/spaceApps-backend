package com.spaceapps.backend.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

open class PaginationResponse<T>(
    @JsonProperty("data")
    val data: List<T>,
    @JsonProperty("total")
    val total: Long,
    @JsonProperty("page")
    val page: Int,
    @JsonProperty("last")
    val isLast: Boolean
)