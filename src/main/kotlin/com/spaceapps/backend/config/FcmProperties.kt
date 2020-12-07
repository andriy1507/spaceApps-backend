package com.spaceapps.backend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "fcm")
class FcmProperties(
        var databaseUrl: String,
        var serviceAccountFile: String
)