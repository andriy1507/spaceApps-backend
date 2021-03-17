package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.static.StaticContentResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import org.springframework.core.io.ClassPathResource
import org.springframework.util.ResourceUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedInputStream

@Api(value = "Static", tags = ["Static"], description = "Static content endpoints")
@RestController
@RequestMapping("static")
class StaticController {

    @GetMapping("/{type}")
    @ApiImplicitParam(
        name = "type",
        paramType = "path",
        allowableValues = "terms-of-use, privacy-policy",
        allowEmptyValue = false,
        required = true
    )
    fun getStaticContent(
        @PathVariable("type") type: String
    ): StaticContentResponse {
        val content = BufferedInputStream(ClassPathResource(type).inputStream).reader().readText()
        return StaticContentResponse(content)
    }
}