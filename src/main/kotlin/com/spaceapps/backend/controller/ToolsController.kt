package com.spaceapps.backend.controller

import com.spaceapps.backend.service.ToolsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tools")
@Api(tags = ["Tools"], description = "Tools endpoints")
class ToolsController @Autowired constructor(
    private val toolsService: ToolsService
) {

    @ApiOperation("Returns web-site metadata")
    @GetMapping("/link-metadata")
    fun getLinkMetaData(@RequestParam(name = "url", required = true) link: String) =
        toolsService.parseLinkMetaData(link)

    @ApiOperation("Generates QR-code")
    @GetMapping("/generate-qr-code")
    fun generateQrCode(
        @RequestParam(name = "data", required = true) data: String,
        @RequestParam(name = "width", required = false, defaultValue = "200") width: Int,
        @RequestParam(name = "height", required = false, defaultValue = "200") height: Int
    ) = toolsService.generateQrCode(data, width, height)

}