package com.spaceapps.backend.service

import com.spaceapps.backend.INCORRECT_MESSAGE_ID
import com.spaceapps.backend.model.dao.chat.ConversationEntity
import com.spaceapps.backend.model.dao.chat.MessageEntity
import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.chat.ChatConversationRequest
import com.spaceapps.backend.model.dto.chat.ChatConversationResponse
import com.spaceapps.backend.model.dto.chat.ChatMessageRequest
import com.spaceapps.backend.model.dto.chat.ChatMessageResponse
import com.spaceapps.backend.repository.ConversationRepository
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
    private val conversationRepository: ConversationRepository
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

    fun getPaginatedConversations(
        search: String,
        pageable: Pageable
    ): ResponseEntity<PaginationResponse<ChatConversationResponse>> {
        val page = conversationRepository.findAllByNameContains(search, pageable)
        val paginationResponse = PaginationResponse(
            data = page.content.map(::mapConversationEntityToResponse),
            total = page.totalElements,
            page = page.number,
            isLast = page.isLast
        )
        return ResponseEntity.ok(paginationResponse)
    }

    fun createConversation(request: ChatConversationRequest): ResponseEntity<ChatConversationResponse> {
        val entity = ConversationEntity(
            id = UUID.randomUUID().toString(),
            name = request.name
        )
        conversationRepository.save(entity)
        return ResponseEntity.ok(mapConversationEntityToResponse(entity))
    }

    fun deleteConversationById(conversationId: String): ResponseEntity<*> {
        conversationRepository.deleteById(conversationId)
        return ResponseEntity.status(HttpStatus.OK).body(null)
    }

    private fun mapConversationEntityToResponse(entity: ConversationEntity): ChatConversationResponse {
        return ChatConversationResponse(
            conversationId = entity.id,
            name = entity.name
        )
    }

    fun updateConversationById(conversationId: String, request: ChatConversationRequest): ResponseEntity<*> {
        val entity = ConversationEntity(
            id = conversationId,
            name = request.name
        )
        return ResponseEntity.status(HttpStatus.OK)
            .body(mapConversationEntityToResponse(conversationRepository.save(entity)))
    }

    fun getPaginatedMessagesByConversationId(
        conversationId: String,
        search: String,
        pageable: Pageable
    ): ResponseEntity<*> {
        val page = messageRepository
            .findAllByConversationIdAndTextContainsOrderByDateTimeDesc(conversationId, search, pageable)
        val paginationResponse = PaginationResponse(
            data = page.content.map(::mapMessageEntityToResponse),
            total = page.totalElements,
            page = page.number,
            isLast = page.isLast
        )
        return ResponseEntity.ok(paginationResponse)
    }

    fun createMessageByConversationId(conversationId: String, request: ChatMessageRequest): ResponseEntity<*> {
        val entity = MessageEntity(
            id = UUID.randomUUID().toString(),
            text = request.messageText,
            dateTime = LocalDateTime.now(),
            conversationId = conversationId
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