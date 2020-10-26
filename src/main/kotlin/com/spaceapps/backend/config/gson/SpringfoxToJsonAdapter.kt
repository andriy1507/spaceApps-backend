package com.spaceapps.backend.config.gson

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import springfox.documentation.spring.web.json.Json
import java.lang.reflect.Type

object SpringfoxToJsonAdapter : JsonSerializer<Json> {
    override fun serialize(src: Json, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return JsonParser.parseString(src.value())
    }
}