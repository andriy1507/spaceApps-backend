package com.spaceapps.backend.config.gson

import com.google.gson.*
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import java.lang.reflect.Type

object DateSerializer : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    private const val DATE_PATTERN = "YYYY-MM-dd"

    override fun serialize(src: LocalDate, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.toString(DATE_PATTERN))
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDate {
        return DateTimeFormat.forPattern(DATE_PATTERN).parseLocalDate(json.asString)
    }
}