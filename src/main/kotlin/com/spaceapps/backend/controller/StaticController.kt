package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.static.StaticContentResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.core.io.ClassPathResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import java.io.BufferedInputStream

@Api(value = "Static", tags = ["Static"], description = "Static endpoints")
@RestController
@RequestMapping("static")
class StaticController {

    @GetMapping("/terms-of-use")
    @ApiOperation("Returns web-page with Terms of use", response = StaticContentResponse::class)
    fun getTermsOfUse(): ResponseEntity<StaticContentResponse> {
        val resource = ClassPathResource("/templates/terms-of-use.html")
        val content = BufferedInputStream(resource.inputStream).reader().readText()
        return ResponseEntity.ok(StaticContentResponse(content))
    }

    @GetMapping("/privacy-policy")
    @ApiOperation("Returns web-page with Privacy policy", response = StaticContentResponse::class)
    fun getStaticContent(): ResponseEntity<StaticContentResponse> {
        val resource = ClassPathResource("/templates/privacy-policy.html")
        val content = BufferedInputStream(resource.inputStream).reader().readText()
        return ResponseEntity.ok(StaticContentResponse(content))
    }
}