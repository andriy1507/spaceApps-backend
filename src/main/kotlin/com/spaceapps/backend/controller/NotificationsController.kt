package com.spaceapps.backend.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("notifications")
@Api(tags = ["Notifications"], description = "Notifications endpoints")
class NotificationsController {

    @GetMapping
    @ApiOperation("Returns paginated notifications")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    fun getNotifications(
        @PageableDefault(size = 20, page = 0)
        pageable: Pageable,
        auth: Authentication
    ) {

    }

    @PatchMapping("/viewed/{notificationId}")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    fun markNotificationViewed(
        @PathVariable("notificationId") notificationId: Int,
        auth: Authentication
    ) {

    }

    @DeleteMapping("/delete/{notificationId}")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    fun deleteNotification(
        @PathVariable("notificationId") notificationId: Int,
        auth: Authentication
    ) {

    }
}