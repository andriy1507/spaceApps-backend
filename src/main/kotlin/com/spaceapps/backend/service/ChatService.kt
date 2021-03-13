package com.spaceapps.backend.service

import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.logging.Level
import java.util.logging.Logger

@Service
class ChatService {

    private val connections = mutableListOf<WebSocketSession>()

    fun registerSession(session: WebSocketSession) {
        connections.add(session)
    }

    fun removeSession(session: WebSocketSession) {
        connections.remove(session)
    }

    fun handleSocketMessage(session: WebSocketSession, message: TextMessage) {

    }
}