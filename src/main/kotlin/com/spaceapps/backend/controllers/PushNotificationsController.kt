package com.spaceapps.backend.controllers

import com.spaceapps.backend.model.dto.PushNotificationRequest
import com.spaceapps.backend.services.PushNotificationService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("push-notification")
@Api(value = "Push-notifications Controller", tags = ["Push-notifications"], description = "Push-notifications management")
class PushNotificationsController @Autowired constructor(
        private val pushService: PushNotificationService
) {

    @PostMapping("/send/{token}")
    fun sendNotification(
            @PathVariable("token") token: String,
            @RequestParam("title", required = false, defaultValue = "") title: String? = null,
            @RequestParam("text") text: String,
            @RequestParam("imageUrl", required = false, defaultValue = "") imageUrl: String? = null
    ) {
        pushService.sendSimpleNotification(title, text, imageUrl, token)
    }

    @PostMapping("/send-to-user")
    fun sendNotificationToUser(
            @RequestParam("user") userName: String,
            @RequestBody notification: PushNotificationRequest
    ) {
        pushService.sendToUser(notification, userName)
    }
}