package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.profile.ProfileRequest
import com.spaceapps.backend.model.dto.profile.ProfileResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

@RestController
@Api(tags = ["Profiles"], description = "Profiles endpoints")
@RequestMapping("profiles")
class UserProfileController @Autowired constructor() {


    @GetMapping("/{userId}")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ProfileResponse::class))
    fun getUserProfileById(@PathVariable("userId") userId: Int) = Unit

    @PutMapping
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ProfileResponse::class))
    fun editUserProfile(
        @RequestBody request: ProfileRequest,
        @ApiIgnore auth: Authentication
    ) = Unit
}