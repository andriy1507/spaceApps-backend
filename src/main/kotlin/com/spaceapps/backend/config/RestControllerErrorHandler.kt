package com.spaceapps.backend.config

import com.spaceapps.backend.model.ErrorModel
import org.joda.time.LocalDateTime
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestControllerErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception::class)
    fun handleBadRequest(error: Exception): ErrorModel {
        return ErrorModel(
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST.value(),
                error.localizedMessage,
                LocalDateTime.now()
        )
    }
}