package com.spaceapps.backend.service

import com.spaceapps.backend.INCORRECT_FEED_ID
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

    fun getSingleFeed(feedId: Int): ResponseEntity<*> {
        val entity = feedsRepository.findById(feedId)
        return if (entity.isPresent) {
            ResponseEntity.ok(mapFeedEntityToResponse(entity.get()))
        } else {
            ResponseEntity.badRequest().body(INCORRECT_FEED_ID)
        }
    }

    fun getFeeds(search: String, pageable: Pageable): PaginationResponse<FeedResponse> {
        val page = feedsRepository.findAllByTitleContains(search, pageable)
        return PaginationResponse(
            data = page.content.map(::mapFeedEntityToResponse),
            page = page.number,
            total = page.totalElements,
            isLast = page.isLast
        )
    }

    fun createFeed(request: FeedRequest): FeedResponse {
        val entity = feedsRepository.save(
            FeedEntity(
                title = request.title,
                items = request.items.map { FeedItemEntity(text = it.text) }.toMutableList()
            )
        )
        return mapFeedEntityToResponse(entity)
    }

    fun updateFeed(feedId: Int, request: FeedRequest): ResponseEntity<*> {
        val entity = feedsRepository.findById(feedId)
        return if (entity.isPresent) {
            val feed = entity.get()
            feed.title = request.title
            feed.items = request.items.map { FeedItemEntity(text = it.text) }.toMutableList()
            ResponseEntity.ok(mapFeedEntityToResponse(feedsRepository.save(feed)))
        } else {
            ResponseEntity.badRequest().body(INCORRECT_FEED_ID)
        }
    }

    fun deleteFeed(feedId: Int) {
        feedsRepository.deleteById(feedId)
    }

    fun toggleLikeForFeed(feedId: Int): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null)
    }

    private fun mapFeedEntityToResponse(entity: FeedEntity): FeedResponse {
        return FeedResponse(
            id = entity.id,
            title = entity.title,
            items = entity.items.map(::mapFeedItemEntityToResponse),
            created = entity.created
        )
    }

    private fun mapFeedItemEntityToResponse(entity: FeedItemEntity): FeedItemDto {
        return FeedItemDto(text = entity.text)
    }

}