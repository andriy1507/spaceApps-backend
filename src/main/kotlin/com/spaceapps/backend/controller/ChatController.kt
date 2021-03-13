package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.chat.ChatConversationRequest
import com.spaceapps.backend.model.dto.chat.ChatConversationResponse
import com.spaceapps.backend.model.dto.chat.ChatMessageRequest
import com.spaceapps.backend.service.ChatService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
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
        dataTypeClass = String::class,
        required = true
    )
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
        dataTypeClass = String::class,
        required = true
    )
    fun createChatConversation(@RequestBody request: ChatConversationRequest): ResponseEntity<*> {
        return chatService.createConversation(request)
    }

    @DeleteMapping("/conversations/delete/{conversationId}")
    @ApiOperation("Deletes conversation")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun deleteChatConversation(@PathVariable("conversationId") conversationId: String): ResponseEntity<*> {
        return chatService.deleteConversationById(conversationId)
    }

    @PutMapping("/conversations/update/{conversationId}")
    @ApiOperation("Updates conversation")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun updateChatConversation(
        @PathVariable("conversationId") conversationId: String,
        @RequestBody request: ChatConversationRequest
    ): ResponseEntity<*> {
        return chatService.updateConversationById(conversationId, request)
    }

    @GetMapping("/message/{conversationId}")
    @ApiOperation("Returns paginated list of messages by conversation ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
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

    @PostMapping("/message/create/{conversationId}")
    @ApiOperation("Creates new message by conversation ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun createChatMessage(
        @PathVariable("conversationId") conversationId: String,
        @RequestBody request: ChatMessageRequest
    ): ResponseEntity<*> {
        return chatService.createMessageByConversationId(conversationId, request)
    }

    @DeleteMapping("/message/delete/{messageId}")
    @ApiOperation("Deletes message by message ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun deleteChatMessage(@PathVariable("messageId") messageId: String): ResponseEntity<*> {
        return chatService.deleteMessageById(messageId)
    }

    @PutMapping("/message/update/{messageId}")
    @ApiOperation("Updates message by message ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun updateChatMessage(
        @PathVariable("messageId") messageId: String,
        @RequestBody request: ChatMessageRequest
    ): ResponseEntity<*> {
        return chatService.updateMessageById(messageId, request)
    }

}