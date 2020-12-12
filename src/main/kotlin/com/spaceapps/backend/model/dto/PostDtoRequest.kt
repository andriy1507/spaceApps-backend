package com.spaceapps.backend.model.dto

import com.google.gson.annotations.SerializedName
import com.spaceapps.backend.model.dao.PostDao
import org.joda.time.LocalDateTime


data class PostDtoRequest(
        @SerializedName("id")
        val id: Long,
        @SerializedName("title")
        val title: String,
        @SerializedName("text")
        val text: String
) {
    fun toDao() = PostDao(
            id = id,
            title = title,
            text = text,
            created = LocalDateTime.now().toDate().time
    )
}

data class PostDtoResponse(
        @SerializedName("id")
        val id: Long,
        @SerializedName("title")
        val title: String,
        @SerializedName("text")
        val text: String,
        @SerializedName("created")
        val created: LocalDateTime,
        @SerializedName("isLiked")
        val isLiked: Boolean,
        @SerializedName("likesCount")
        val likesCount: Long,
        @SerializedName("commentsCount")
        val commentsCount: Long
)