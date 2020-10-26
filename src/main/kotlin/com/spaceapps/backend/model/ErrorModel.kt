package com.spaceapps.backend.model

import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus

data class ErrorModel(
        @SerializedName("error")
        val error: HttpStatus,
        @SerializedName("status")
        val status: Int,
        @SerializedName("message")
        val message: String,
        @SerializedName("time")
        val time: LocalDateTime
)