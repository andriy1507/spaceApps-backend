package com.spaceapps.backend.model.dto

import com.google.gson.annotations.SerializedName
import com.spaceapps.backend.model.dao.PostDao
import org.joda.time.LocalDateTime


class PostDto(
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