package com.spaceapps.backend.service

import com.spaceapps.backend.INCORRECT_MESSAGE_ID
import com.spaceapps.backend.model.dao.chat.ChatEntity
import com.spaceapps.backend.model.dao.chat.MessageEntity
import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.chat.ChatRequest
import com.spaceapps.backend.model.dto.chat.ChatResponse
import com.spaceapps.backend.model.dto.chat.ChatMessageRequest
import com.spaceapps.backend.model.dto.chat.ChatMessageResponse
import com.spaceapps.backend.repository.ChatRepository
import com.spaceapps.backend.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.time.LocalDateTime
import java.util.*

@Service
class ChatService @Autowired constructor(
    private val messageRepository: MessageRepository,
    private val chatRepository: ChatRepository
) {

    private val connections = mutableListOf<WebSocketSession>()

    fun registerSession(session: WebSocketSession) {
        connections.add(session)
    }

    fun removeSession(session: WebSocketSession) {
        connections.remove(session)
    }

    fun handleSocketMessage(session: WebSocketSession, message: TextMessage) {

    }

    fun getPaginatedChats(
        search: String,
        pageable: Pageable
    ): ResponseEntity<PaginationResponse<ChatResponse>> {
        val page = chatRepository.findAllByNameContains(search, pageable)
        val paginationResponse = PaginationResponse(
            data = page.content.map(::mapChatEntityToResponse),
            total = page.totalElements,
            page = page.number,
            isLast = page.isLast
        )
        return ResponseEntity.ok(paginationResponse)
    }

    fun createChat(request: ChatRequest): ResponseEntity<ChatResponse> {
        val entity = ChatEntity(
            id = UUID.randomUUID().toString(),
            name = request.name
        )
        chatRepository.save(entity)
        return ResponseEntity.ok(mapChatEntityToResponse(entity))
    }

    fun deleteChatById(chatId: String): ResponseEntity<*> {
        chatRepository.deleteById(chatId)
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

    private fun mapChatEntityToResponse(entity: ChatEntity): ChatResponse {
        return ChatResponse(
            conversationId = entity.id,
            name = entity.name
        )
    }

    fun updateChatById(chatId: String, request: ChatRequest): ResponseEntity<*> {
        val entity = ChatEntity(
            id = chatId,
            name = request.name
        )
        return ResponseEntity.status(HttpStatus.OK)
            .body(mapChatEntityToResponse(chatRepository.save(entity)))
    }

    fun getPaginatedMessagesByChatId(
        chatId: String,
        search: String,
        pageable: Pageable
    ): ResponseEntity<*> {
        val page = messageRepository
            .findAllByConversationIdAndTextContainsOrderByDateTimeDesc(chatId, search, pageable)
        val paginationResponse = PaginationResponse(
            data = page.content.map(::mapMessageEntityToResponse),
            total = page.totalElements,
            page = page.number,
            isLast = page.isLast
        )
        return ResponseEntity.ok(paginationResponse)
    }

    fun createMessageByChatId(chatId: String, request: ChatMessageRequest): ResponseEntity<*> {
        val entity = MessageEntity(
            id = UUID.randomUUID().toString(),
            text = request.messageText,
            dateTime = LocalDateTime.now(),
            conversationId = chatId
        )
        return ResponseEntity.ok(mapMessageEntityToResponse(messageRepository.save(entity)))
    }

    fun deleteMessageById(messageId: String): ResponseEntity<*> {
        messageRepository.deleteById(messageId)
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

    fun updateMessageById(messageId: String, request: ChatMessageRequest): ResponseEntity<*> {
        val entity = messageRepository.findById(messageId)
        return if (entity.isPresent) {
            val newEntity = messageRepository.save(entity.get().copy(text = request.messageText))
            ResponseEntity.ok(mapMessageEntityToResponse(newEntity))
        } else {
            ResponseEntity.badRequest().body(INCORRECT_MESSAGE_ID)
        }
    }

    private fun mapMessageEntityToResponse(entity: MessageEntity): ChatMessageResponse {
        return ChatMessageResponse(
            messageId = entity.id,
            conversationId = entity.conversationId,
            messageText = entity.text,
            dateTime = entity.dateTime
        )
    }

}