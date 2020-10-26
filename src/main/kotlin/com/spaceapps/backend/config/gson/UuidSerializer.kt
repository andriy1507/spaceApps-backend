package com.spaceapps.backend.config.gson

import com.google.gson.*
import java.lang.reflect.Type
import java.util.*

object UuidSerializer:JsonSerializer<UUID>,JsonDeserializer<UUID> {
    override fun serialize(src: UUID, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonPrimitive(src.toString())
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): UUID {
        return UUID.fromString(json.asString)
    }
}