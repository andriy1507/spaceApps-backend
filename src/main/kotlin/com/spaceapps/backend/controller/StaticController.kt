package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.static.StaticContentResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedInputStream

@Api(value = "Static Contents", tags = ["Static Contents"], description = "Static contents endpoints")
@RestController
@RequestMapping("static-contents")
class StaticController {

    @GetMapping("/{type}")
    @ApiOperation("Returns static content of given type", response = StaticContentResponse::class)
    @ApiImplicitParam(
        name = "type",
        paramType = "path",
        allowableValues = "terms-of-use, privacy-policy",
        allowEmptyValue = false,
        required = true
    )
    fun getStaticContent(
        @PathVariable("type") type: String
    ): ResponseEntity<*> {
        val resource = ClassPathResource(type)
        return if (resource.exists()) {
            val content = BufferedInputStream(resource.inputStream).reader().readText()
            ResponseEntity.ok(StaticContentResponse(content))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }
}