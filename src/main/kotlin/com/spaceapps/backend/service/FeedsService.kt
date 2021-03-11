package com.spaceapps.backend.service

import com.spaceapps.backend.model.dao.feeds.FeedEntity
import com.spaceapps.backend.model.dao.feeds.FeedItemEntity
import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.feeds.FeedItemDto
import com.spaceapps.backend.model.dto.feeds.FeedRequest
import com.spaceapps.backend.model.dto.feeds.FeedResponse
import com.spaceapps.backend.repository.FeedsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FeedsService @Autowired constructor(
    private val feedsRepository: FeedsRepository
) {

    fun getFeeds(search: String, pageable: Pageable): PaginationResponse<FeedResponse> {
        val page = feedsRepository.findAllByTitleLike(search, pageable)
        return PaginationResponse(
            data = page.content.map { entity ->
                FeedResponse(
                    id = entity.id,
                    title = entity.title,
                    items = entity.items.map { FeedItemDto(text = it.text) })
            },
            page = page.number,
            total = page.totalElements,
            isLast = page.isLast
        )
    }

    fun createFeed(request: FeedRequest): FeedResponse {
        val feed = feedsRepository.save(
            FeedEntity(
                title = request.title,
                items = request.items.map { FeedItemEntity(text = it.text) }.toMutableList()
            )
        )
        return FeedResponse(
            id = feed.id,
            title = feed.title,
            items = feed.items.map { FeedItemDto(text = it.text) }
        )
    }

    fun updateFeed(feedId: Int, request: FeedRequest) {
        feedsRepository.findById(feedId).ifPresent { feed ->
            feed.title = request.title
            feed.items = request.items.map { FeedItemEntity(text = it.text) }.toMutableList()
            feedsRepository.save(feed)
        }
    }

    fun deleteFeed(feedId: Int) {
        feedsRepository.deleteById(feedId)
    }

    fun toggleLikeForFeed(feedId: Int): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null)
    }

}