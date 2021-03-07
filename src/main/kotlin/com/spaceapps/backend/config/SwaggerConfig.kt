package com.spaceapps.backend.config

import com.spaceapps.backend.model.dto.auth.AuthorizationTokenResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun swaggerConfiguration(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.spaceapps.backend.controller"))
            .paths(PathSelectors.any())
            .build()
            .directModelSubstitute(ResponseEntity::class.java, AuthorizationTokenResponse::class.java)
    }
}