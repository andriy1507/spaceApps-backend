package com.spaceapps.backend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.mail")
class MailServiceProperties(
    val username: String,
    val password: String,
    val port: Int,
    val host: String,
    val protocol: String,
    val starttls: Boolean,
    val auth: Boolean
)