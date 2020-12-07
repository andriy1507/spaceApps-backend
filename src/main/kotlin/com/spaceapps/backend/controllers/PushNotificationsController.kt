package com.spaceapps.backend.controllers

import com.spaceapps.backend.services.PushNotificationService
import io.swagger.annotations.Api
import org.jetbrains.annotations.Nullable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("push-notification")
@Api(value = "Push-notifications Controller", tags = ["Push-notifications"], description = "Push-notifications management")
class PushNotificationsController @Autowired constructor(
        private val pushService: PushNotificationService
) {

    @PostMapping("/send-token/{token}")
    fun sendNotification(
            @PathVariable("token") token: String,
            @RequestParam("title", required = false, defaultValue = "") title: String? = null,
            @RequestParam("text") text: String,
            @RequestParam("imageUrl", required = false, defaultValue = "") imageUrl: String? = null
    ) {
        pushService.sendSimpleNotification(title, text, imageUrl, token)
    }
}