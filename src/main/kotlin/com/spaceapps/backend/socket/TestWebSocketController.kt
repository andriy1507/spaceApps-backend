package com.spaceapps.backend.socket

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class TestWebSocketController {

    @MessageMapping("/test")
    @SendTo("/topic/test")
    fun test(msg: String): String {
        return msg
    }
}