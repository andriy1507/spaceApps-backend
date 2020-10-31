package com.spaceapps.backend.model.dto

import com.google.gson.annotations.SerializedName

data class UserTaskDto(
        @SerializedName("userId")
        val userId: Int,
        @SerializedName("id")
        val id: Int,
        @SerializedName("text")
        val text: String,
        @SerializedName("title")
        val title: String
)