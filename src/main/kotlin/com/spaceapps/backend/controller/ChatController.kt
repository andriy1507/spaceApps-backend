package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.chat.ChatMessageRequest
import com.spaceapps.backend.model.dto.chat.ChatMessageResponse
import com.spaceapps.backend.model.dto.chat.ChatRequest
import com.spaceapps.backend.model.dto.chat.ChatResponse
import com.spaceapps.backend.model.dto.pagination.ConversationsPaginationResponse
import com.spaceapps.backend.model.dto.pagination.MessagesPaginationResponse
import com.spaceapps.backend.service.ChatService
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(tags = ["Chats"], description = "Chats endpoints")
@RequestMapping("chats")
class ChatController @Autowired constructor(
    private val chatService: ChatService
) {

    @GetMapping
    @ApiOperation("Returns paginated chats")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ConversationsPaginationResponse::class))
    fun getChatPaginated(
        @PageableDefault(size = 20, page = 0) pageable: Pageable,
        @RequestParam(
            name = "search",
            required = false,
            defaultValue = ""
        ) search: String
    ): ResponseEntity<*> {
        return chatService.getPaginatedChats(search, pageable)
    }

    @PostMapping
    @ApiOperation("Creates chat", response = ChatResponse::class)
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ChatResponse::class))
    fun createChat(@RequestBody request: ChatRequest): ResponseEntity<*> {
        return chatService.createChat(request)
    }

    @DeleteMapping("/{chatId}")
    @ApiOperation("Deletes chat by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun deleteChat(@PathVariable("chatId") chatId: String): ResponseEntity<*> {
        return chatService.deleteChatById(chatId)
    }

    @PutMapping("/{chatId}")
    @ApiOperation("Updates chat")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ChatResponse::class))
    fun updateChat(
        @PathVariable("chatId") chatId: String,
        @RequestBody request: ChatRequest
    ): ResponseEntity<*> {
        return chatService.updateChatById(chatId, request)
    }

    @GetMapping("/{chatId}/messages")
    @ApiOperation("Returns paginated list of messages by chat ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = MessagesPaginationResponse::class))
    fun getChatMessagesPaginated(
        @PageableDefault(size = 20, page = 0) pageable: Pageable,
        @PathVariable("chatId") chatId: String,
        @RequestParam(
            name = "search",
            required = false,
            defaultValue = ""
        ) search: String
    ): ResponseEntity<*> {
        return chatService.getPaginatedMessagesByChatId(chatId, search, pageable)
    }

    @PostMapping("/{chatId}/messages")
    @ApiOperation("Creates new message by chat ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ChatMessageResponse::class))
    fun createChatMessage(
        @PathVariable("chatId") chatId: String,
        @RequestBody request: ChatMessageRequest
    ): ResponseEntity<*> {
        return chatService.createMessageByChatId(chatId, request)
    }

    @DeleteMapping("/{chatId}/messages/{messageId}")
    @ApiOperation("Deletes message by message ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun deleteChatMessage(
        @PathVariable("messageId") messageId: String,
        @PathVariable("chatId") chatId: String
    ): ResponseEntity<*> {
        return chatService.deleteMessageById(messageId)
    }

    @PutMapping("/{chatId}/messages/{messageId}")
    @ApiOperation("Updates message by message ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ChatMessageResponse::class))
    fun updateChatMessage(
        @PathVariable("messageId") messageId: String,
        @RequestBody request: ChatMessageRequest,
        @PathVariable("chatId") chatId: String
    ): ResponseEntity<*> {
        return chatService.updateMessageById(messageId, request)
    }
}