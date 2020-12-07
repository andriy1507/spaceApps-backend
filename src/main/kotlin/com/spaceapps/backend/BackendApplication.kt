package com.spaceapps.backend

import com.spaceapps.backend.config.FcmProperties
import com.spaceapps.backend.config.security.token.AuthTokenProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableConfigurationProperties(
        AuthTokenProperties::class,
        FcmProperties::class
)
@EnableFeignClients
class BackendApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(BackendApplication::class.java, *args)
        }
    }
}
