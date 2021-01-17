package com.spaceapps.backend.model.dto

import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable

class PushNotificationRequest @JvmOverloads constructor(
        @SerializedName("title")
        val title: String,
        @SerializedName("text")
        val text: String,
        @Nullable
        @SerializedName("imageUrl")
        val imageUrl: String? = null
)