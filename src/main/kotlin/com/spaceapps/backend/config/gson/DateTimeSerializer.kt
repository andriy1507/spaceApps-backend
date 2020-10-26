package com.spaceapps.backend.config.gson

import com.google.gson.*
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import java.lang.reflect.Type

object DateTimeSerializer : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private const val DATE_TIME_PATTERN = "YYYY-MM-dd'T'HH:mm:ss.SSS'Z'"

    override fun serialize(src: LocalDateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.toString(DATE_TIME_PATTERN))
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDateTime {
        return DateTimeFormat.forPattern(DATE_TIME_PATTERN).parseLocalDateTime(json.asString)
    }
}