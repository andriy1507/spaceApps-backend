package com.spaceapps.backend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "firebase")
class FirebaseProperties(
    val databaseUrl: String,
    val serviceAccountFile: String
)