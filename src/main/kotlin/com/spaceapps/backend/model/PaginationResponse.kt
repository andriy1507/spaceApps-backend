package com.spaceapps.backend.model

import com.google.gson.annotations.SerializedName

data class PaginationResponse<T>(
        @SerializedName("page")
        val page: Int,
        @SerializedName("total")
        val total: Int,
        @SerializedName("content")
        val content: List<T>
)