package com.spaceapps.backend.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore

@RestController
@RequestMapping("settings")
@Api(tags = ["Settings"], description = "Settings endpoints")
class SettingsController {

    @ApiOperation("Returns paginated notifications")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    @PutMapping("/set-language/{language}")
    fun setUserLanguage(
        @PathVariable("language") language: String,
        @ApiIgnore auth: Authentication
    ) = Unit
}