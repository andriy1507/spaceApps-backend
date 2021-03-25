package com.spaceapps.backend.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Api(value = "Test", tags = ["Test"], description = "Test endpoints")
@RestController
@RequestMapping("test")
class TestController {

    @GetMapping
    @ApiOperation("Returns static content of given type", response = IntArray::class)
    fun testListQueryParam(@RequestParam("id") list: IntArray): IntArray {
        return list
    }

}