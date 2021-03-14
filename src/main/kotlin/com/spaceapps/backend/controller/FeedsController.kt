package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.feeds.FeedFullResponse
import com.spaceapps.backend.model.dto.feeds.FeedRequest
import com.spaceapps.backend.service.FeedsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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
        dataTypeClass = String::class,
        required = true
    )
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
        search: String
    ): PaginationResponse<*> {
        return feedsService.getFeeds(search, pageable)
    }

    @GetMapping("/{feedId}")
    @ApiOperation("Returns single feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun getSingleFeed(@PathVariable("feedId") feedId: Int): ResponseEntity<*> {
        return feedsService.getSingleFeed(feedId)
    }

    @PostMapping("/create")
    @ApiOperation("Creates new feed")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun postFeed(@RequestBody request: FeedRequest): FeedFullResponse {
        return feedsService.createFeed(request)
    }

    @PutMapping("/update/{feedId}")
    @ApiOperation("Updates feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun updateFeed(@PathVariable feedId: Int, @RequestBody request: FeedRequest): ResponseEntity<*> {
        return feedsService.updateFeed(feedId, request)
    }

    @DeleteMapping("/delete/{feedId}")
    @ApiOperation("Deletes feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun deleteFeed(@PathVariable feedId: Int) {
        feedsService.deleteFeed(feedId)
    }

    @PatchMapping("/like/{feedId}")
    @ApiOperation("Toggles like for feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun toggleFeedLike(@PathVariable("feedId") feedId: Int): ResponseEntity<*> {
        return feedsService.toggleLikeForFeed(feedId)
    }

    @PostMapping("/comments/create/{feedId}")
    @ApiOperation("Creates comment for feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun createComment(@PathVariable("feedId") feedId: Int) = Unit

    @GetMapping("/comments/{feedId}")
    @ApiOperation("Returns paginated comments for feed by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun getCommentsPaginated(
        @PageableDefault(size = 20, page = 0) pageable: Pageable,
        @PathVariable("feedId") feedId: Int
    ) = Unit

    @PutMapping("/comments/update/{commentId}")
    @ApiOperation("Updates comment by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun updateComment(@PathVariable("commentId") commentId: Int) = Unit

    @DeleteMapping("/comments/delete/{commentId}")
    @ApiOperation("Deletes comment by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun deleteComment(@PathVariable("commentId") commentId: Int) = Unit

    @PatchMapping("/comments/like/{commentId}")
    @ApiOperation("Toggles like for comment by ID")
    @ApiImplicitParam(
        name = "Authorization",
        value = "Access token",
        paramType = "header",
        dataTypeClass = String::class,
        required = true
    )
    fun toggleCommentLike(@PathVariable("commentId") commentId: Int) = Unit

}