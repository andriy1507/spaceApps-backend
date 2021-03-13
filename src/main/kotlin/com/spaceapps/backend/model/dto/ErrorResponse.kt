package com.spaceapps.backend.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class ErrorResponse(
    @JsonProperty("message")
    val message: String,
    @JsonProperty("status")
    val status: HttpStatus = HttpStatus.BAD_REQUEST,
    @JsonProperty("errors")
    val errors: Collection<String> = emptyList(),
    @JsonProperty("dateTime")
    val dateTime: LocalDateTime = LocalDateTime.now()
)