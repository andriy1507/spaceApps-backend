package com.spaceapps.backend.socket

import org.slf4j.LoggerFactory
import org.springframework.web.socket.PongMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

class ChatWebSocketHandler: TextWebSocketHandler() {

    override fun handlePongMessage(session: WebSocketSession, message: PongMessage) {
        session.sendMessage(message)
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        LoggerFactory.getLogger(this::class.java).info(session.principal?.name)
    }
}