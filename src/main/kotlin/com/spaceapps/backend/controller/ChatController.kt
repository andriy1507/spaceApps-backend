package com.spaceapps.backend.controller

import com.spaceapps.backend.service.ChatService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
    fun getMessagingConversationsPaginated(
        @PageableDefault(size = 20, page = 0) pageable: Pageable
    ) = Unit

    @PostMapping("/conversations/create")
    @ApiOperation("Creates conversation")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun createMessagingConversation() = Unit

    @DeleteMapping("/conversations/delete/{conversationId}")
    @ApiOperation("Deletes conversation")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun deleteMessagingConversation(@PathVariable("conversationId") conversationId: String) = Unit

    @PutMapping("/conversations/update/{conversationId}")
    @ApiOperation("Updates conversation")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun updateMessagingConversation(@PathVariable("conversationId") conversationId: String) = Unit

}