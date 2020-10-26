package com.spaceapps.backend.config.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.springframework.beans.factory.FactoryBean
import org.springframework.context.annotation.Configuration
import springfox.documentation.spring.web.json.Json
import java.util.*

@Configuration
class GsonConfig : FactoryBean<Gson> {
    override fun getObject(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(Json::class.java, SpringfoxToJsonAdapter)
                .registerTypeAdapter(LocalDateTime::class.java, DateTimeSerializer)
                .registerTypeAdapter(LocalDate::class.java, DateSerializer)
                .registerTypeAdapter(UUID::class.java, UuidSerializer)
                .create()
    }

    override fun getObjectType() = Gson::class.java
}