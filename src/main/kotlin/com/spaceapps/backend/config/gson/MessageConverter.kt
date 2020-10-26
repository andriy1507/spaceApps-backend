package com.spaceapps.backend.config.gson

import com.google.gson.Gson
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.GsonHttpMessageConverter

@Configuration
class MessageConverter {

    @Bean
    fun getHttpMessageConverter(gson: Gson): GsonHttpMessageConverter {
        return GsonHttpMessageConverter(gson)
    }
}