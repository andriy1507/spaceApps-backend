package com.spaceapps.backend.socket

import com.spaceapps.backend.service.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.PongMessage
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.AbstractWebSocketHandler

@Component
class ChatWebSocketHandler @Autowired constructor(
    private val chatService: ChatService
) : AbstractWebSocketHandler() {
    override fun handlePongMessage(session: WebSocketSession, message: PongMessage) {
        session.sendMessage(PongMessage())
        super.handlePongMessage(session, message)
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        session.sendMessage(TextMessage("CONNECTED"))
//        chatService.registerSession(session)
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        session.sendMessage(TextMessage("ECHO: $message"))
//        chatService.handleSocketMessage(session, message)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
//        chatService.removeSession(session)
    }
}