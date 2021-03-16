package com.spaceapps.backend.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import org.springframework.web.bind.annotation.*

@RestController
@Api(tags = ["Profile"], description = "Profile endpoints")
@RequestMapping("profile")
class UserProfileController {


    @GetMapping("/{userId}")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    fun getUserProfileById(@PathVariable("userId") userId: Int) {

    }

    @PutMapping("/edit")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    fun editUserProfile() {

    }
}