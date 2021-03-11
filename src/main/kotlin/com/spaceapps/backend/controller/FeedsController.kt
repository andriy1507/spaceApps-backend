package com.spaceapps.backend.controller

import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.feeds.FeedRequest
import com.spaceapps.backend.model.dto.feeds.FeedResponse
import com.spaceapps.backend.service.FeedsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@Api(tags = ["Feeds"], description = "Feeds endpoints")
@RequestMapping("feeds")
class FeedsController(
    private val feedsService: FeedsService
) {

    @GetMapping("")
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
    ): PaginationResponse<FeedResponse> {
        return feedsService.getFeeds(search, pageable)
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
    fun postFeed(@RequestBody request: FeedRequest): FeedResponse {
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
    fun updateFeed(@PathVariable feedId: Int, @RequestBody request: FeedRequest) {
        feedsService.updateFeed(feedId, request)
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

}