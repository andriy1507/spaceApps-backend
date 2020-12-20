package com.spaceapps.backend.model.dto

import com.google.gson.annotations.SerializedName

data class CommentDto(
        @SerializedName("id")
        val id: Long,
        @SerializedName("postId")
        val postId: Long,
        @SerializedName("userId")
        val userId: Long,
        @SerializedName("text")
        val text: String
)