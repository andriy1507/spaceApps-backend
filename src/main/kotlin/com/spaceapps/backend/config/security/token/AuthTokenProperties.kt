package com.spaceapps.backend.config.security.token

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@ConstructorBinding
@ConfigurationProperties(prefix = "auth.token")
class AuthTokenProperties(
        var secretKey: ByteArray
) {
    @Component
    @ConfigurationPropertiesBinding
    class StringToByteArrayConverter : Converter<String, ByteArray> {
        override fun convert(source: String): ByteArray {
            return source.toByteArray()
        }
    }
}