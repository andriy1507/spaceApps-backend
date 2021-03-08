package com.spaceapps.backend.model.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PaginationResponse<T>(
    @JsonProperty("data")
    val data: List<T>,
    @JsonProperty("total")
    val total: Long,
    @JsonProperty("page")
    val page: Int,
    @JsonProperty("isLast")
    val isLast:Boolean
)