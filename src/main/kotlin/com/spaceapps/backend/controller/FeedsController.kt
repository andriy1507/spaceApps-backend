package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.feeds.*
import com.spaceapps.backend.model.dto.pagination.CommentsPaginationResponse
import com.spaceapps.backend.model.dto.pagination.FeedsPaginationResponse
import com.spaceapps.backend.service.FeedsService
import com.spaceapps.backend.utils.ApplicationUserDetails
import io.swagger.annotations.*
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import springfox.documentation.annotations.ApiIgnore

@RestController
@Api(tags = ["Feeds"], description = "Feeds endpoints")
@RequestMapping("feeds")
class FeedsController(
    private val feedsService: FeedsService
) {

    @GetMapping
    @ApiOperation("Returns paginated feeds")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = FeedsPaginationResponse::class))
    fun getFeeds(
        @PageableDefault(
            size = 20,
            page = 0,
            sort = ["created"],
            direction = Sort.Direction.DESC
        )
        pageable: Pageable,
        @RequestParam(
            name = "search",
            required = false,
            defaultValue = ""
        )
        search: String,
        @ApiIgnore
        auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.getFeeds(search, pageable, user)
    }

    @GetMapping("/{feedId}")
    @ApiOperation("Returns single feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = FeedFullResponse::class))
    fun getSingleFeed(
        @PathVariable("feedId") feedId: Int,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.getSingleFeed(feedId, user)
    }

    @PostMapping("/create")
    @ApiOperation("Creates new feed")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = FeedShortResponse::class))
    fun postFeed(
        @RequestBody request: FeedRequest,
        @ApiIgnore auth: Authentication
    ): FeedFullResponse {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.createFeed(request, user)
    }

    @PutMapping("/update/{feedId}")
    @ApiOperation("Updates feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = FeedShortResponse::class))
    fun updateFeed(
        @PathVariable("feedId") feedId: Int,
        @RequestBody request: FeedRequest,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.updateFeed(feedId, request, user)
    }

    @DeleteMapping("/delete/{feedId}")
    @ApiOperation("Deletes feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun deleteFeed(@PathVariable feedId: Int) {
        feedsService.deleteFeed(feedId)
    }

    @PatchMapping("/like/{feedId}")
    @ApiOperation("Toggles like for feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = FeedShortResponse::class))
    fun toggleFeedLike(
        @PathVariable("feedId") feedId: Int,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.toggleFeedLike(feedId, user)
    }

    @PostMapping("/comments/create/{feedId}")
    @ApiOperation("Creates comment for feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = CommentResponse::class))
    fun createComment(
        @PathVariable("feedId") feedId: Int,
        @RequestBody request: CommentRequest,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.createComment(feedId, request, user)
    }

    @GetMapping("/comments/{feedId}")
    @ApiOperation("Returns paginated comments for feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = CommentsPaginationResponse::class))
    fun getCommentsPaginated(
        @PageableDefault(size = 20, page = 0) pageable: Pageable,
        @PathVariable("feedId") feedId: Int,
        @RequestParam("search", required = false, defaultValue = "") search: String,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.getCommentsByFeedIdPaginated(pageable, feedId, search, user)
    }

    @PutMapping("/comments/update/{commentId}")
    @ApiOperation("Updates comment by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = CommentResponse::class))
    fun updateComment(
        @PathVariable("commentId") commentId: Int,
        @RequestBody request: CommentRequest,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.updateComment(commentId, request, user)
    }

    @DeleteMapping("/comments/delete/{commentId}")
    @ApiOperation("Deletes comment by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = Unit::class))
    fun deleteComment(
        @PathVariable("commentId") commentId: Int,
        @ApiIgnore auth: Authentication
    ) {
        val user = auth.principal as ApplicationUserDetails
        feedsService.deleteComment(commentId)
    }

    @PatchMapping("/comments/like/{commentId}")
    @ApiOperation("Toggles like for comment by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class
    )
    @ApiResponses(ApiResponse(code = 200, message = "Success", response = CommentResponse::class))
    fun toggleCommentLike(
        @PathVariable("commentId") commentId: Int,
        @ApiIgnore auth: Authentication
    ): ResponseEntity<*> {
        val user = auth.principal as ApplicationUserDetails
        return feedsService.toggleCommentLike(commentId, user)
    }
}