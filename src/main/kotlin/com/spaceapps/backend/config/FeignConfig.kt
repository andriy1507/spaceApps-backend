package com.spaceapps.backend.config

import feign.Logger
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class FeignConfig {

    @Bean
    fun loggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }

    @Bean
    fun errorDecoder(): ErrorDecoder {
        return ErrorDecoder { methodKey, response ->
            when (response.status()) {
                400 -> Exception("$methodKey: BAD REQUEST")
                else -> Exception("$methodKey: UNKNOWN")
            }
        }
    }
}