package com.spaceapps.backend.model

import com.google.gson.annotations.SerializedName

data class PaginationResponse<T>(
        @SerializedName("page")
        val page: Int,
        @SerializedName("total")
        val total: Long,
        @SerializedName("content")
        val content: List<T>
)