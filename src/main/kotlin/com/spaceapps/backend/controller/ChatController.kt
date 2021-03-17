package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.chat.ChatConversationRequest
import com.spaceapps.backend.model.dto.chat.ChatConversationResponse
import com.spaceapps.backend.model.dto.chat.ChatMessageRequest
import com.spaceapps.backend.model.dto.chat.ChatMessageResponse
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
@Api(tags = ["Chat"], description = "Chat endpoints")
@RequestMapping("chat")
class ChatController @Autowired constructor(
    private val chatService: ChatService
) {

    @GetMapping("/conversations")
    @ApiOperation("Returns paginated conversations")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ConversationsPaginationResponse::class))
    fun getChatConversationsPaginated(
        @PageableDefault(size = 20, page = 0) pageable: Pageable,
        @RequestParam(
            name = "search",
            required = false,
            defaultValue = ""
        ) search: String
    ): ResponseEntity<*> {
        return chatService.getPaginatedConversations(search, pageable)
    }

    @PostMapping("/conversations/create")
    @ApiOperation("Creates conversation", response = ChatConversationResponse::class)
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ChatConversationResponse::class))
    fun createChatConversation(@RequestBody request: ChatConversationRequest): ResponseEntity<*> {
        return chatService.createConversation(request)
    }

    @DeleteMapping("/conversations/delete/{conversationId}")
    @ApiOperation("Deletes conversation")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun deleteChatConversation(@PathVariable("conversationId") conversationId: String): ResponseEntity<*> {
        return chatService.deleteConversationById(conversationId)
    }

    @PutMapping("/conversations/update/{conversationId}")
    @ApiOperation("Updates conversation")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ChatConversationResponse::class))
    fun updateChatConversation(
        @PathVariable("conversationId") conversationId: String,
        @RequestBody request: ChatConversationRequest
    ): ResponseEntity<*> {
        return chatService.updateConversationById(conversationId, request)
    }

    @GetMapping("/messages/{conversationId}")
    @ApiOperation("Returns paginated list of messages by conversation ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = MessagesPaginationResponse::class))
    fun getChatMessagesPaginated(
        @PageableDefault(size = 20, page = 0) pageable: Pageable,
        @PathVariable("conversationId") conversationId: String,
        @RequestParam(
            name = "search",
            required = false,
            defaultValue = ""
        ) search: String
    ): ResponseEntity<*> {
        return chatService.getPaginatedMessagesByConversationId(conversationId, search, pageable)
    }

    @PostMapping("/messages/create/{conversationId}")
    @ApiOperation("Creates new message by conversation ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = ChatMessageResponse::class))
    fun createChatMessage(
        @PathVariable("conversationId") conversationId: String,
        @RequestBody request: ChatMessageRequest
    ): ResponseEntity<*> {
        return chatService.createMessageByConversationId(conversationId, request)
    }

    @DeleteMapping("/messages/delete/{messageId}")
    @ApiOperation("Deletes message by message ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun deleteChatMessage(@PathVariable("messageId") messageId: String): ResponseEntity<*> {
        return chatService.deleteMessageById(messageId)
    }

    @PutMapping("/messages/update/{messageId}")
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
        @RequestBody request: ChatMessageRequest
    ): ResponseEntity<*> {
        return chatService.updateMessageById(messageId, request)
    }

}