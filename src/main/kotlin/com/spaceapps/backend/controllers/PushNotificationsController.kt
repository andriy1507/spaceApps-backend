package com.spaceapps.backend.controllers

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("push-notification")
@Api(value = "Push-notifications Controller", tags = ["Push-notifications"], description = "Push-notifications management")
class PushNotificationsController