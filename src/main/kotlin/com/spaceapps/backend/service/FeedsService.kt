package com.spaceapps.backend.service

import com.spaceapps.backend.INCORRECT_COMMENT_ID
import com.spaceapps.backend.INCORRECT_FEED_ID
import com.spaceapps.backend.model.dao.feeds.*
import com.spaceapps.backend.model.dto.PaginationResponse
import com.spaceapps.backend.model.dto.feeds.*
import com.spaceapps.backend.repository.CommentLikesRepository
import com.spaceapps.backend.repository.FeedCommentsRepository
import com.spaceapps.backend.repository.FeedLikesRepository
import com.spaceapps.backend.repository.FeedsRepository
import com.spaceapps.backend.utils.ApplicationUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FeedsService @Autowired constructor(
    private val feedsRepository: FeedsRepository,
    private val commentLikesRepository: CommentLikesRepository,
    private val feedLikesRepository: FeedLikesRepository,
    private val feedCommentsRepository: FeedCommentsRepository
) {

    fun getSingleFeed(feedId: Int, user: ApplicationUserDetails): ResponseEntity<*> {
        val entity = feedsRepository.findById(feedId)
        return if (entity.isPresent) {
            ResponseEntity.ok(mapFeedEntityToFullResponse(entity.get(), user.userId))
        } else {
            ResponseEntity.badRequest().body(INCORRECT_FEED_ID)
        }
    }

    fun getFeeds(search: String, pageable: Pageable, user: ApplicationUserDetails): ResponseEntity<*> {
        val page = feedsRepository.findAllByTitleContains(search, pageable)
        return ResponseEntity.ok(
            PaginationResponse(
                data = page.content.map { mapFeedEntityToShortResponse(it, user.userId) },
                page = page.number,
                total = page.totalElements,
                isLast = page.isLast
            )
        )
    }

    fun createFeed(request: FeedRequest, user: ApplicationUserDetails): FeedFullResponse {
        val entity = feedsRepository.save(
            FeedEntity(
                title = request.title,
                items = request.items.map { FeedItemEntity(text = it.text) }.toMutableList()
            )
        )
        return mapFeedEntityToFullResponse(entity, user.userId)
    }

    fun updateFeed(feedId: Int, request: FeedRequest, user: ApplicationUserDetails): ResponseEntity<*> {
        val entity = feedsRepository.findById(feedId)
        return if (entity.isPresent) {
            val feed = entity.get()
            feed.title = request.title
            feed.items = request.items.map { FeedItemEntity(text = it.text) }.toMutableList()
            ResponseEntity.ok(mapFeedEntityToFullResponse(feedsRepository.save(feed), user.userId))
        } else {
            ResponseEntity.badRequest().body(INCORRECT_FEED_ID)
        }
    }

    fun deleteFeed(feedId: Int) {
        feedsRepository.deleteById(feedId)
    }

    fun toggleFeedLike(feedId: Int, user: ApplicationUserDetails): ResponseEntity<*> {
        return if (feedsRepository.existsById(feedId)) {
            val like = feedLikesRepository.getByFeedIdAndUserId(feedId, user.userId)
            if (like == null) {
                feedLikesRepository.save(FeedLikeEntity(feedId = feedId, userId = user.userId))
            } else {
                feedLikesRepository.delete(like)
            }
            val feed = mapFeedEntityToShortResponse(feedsRepository.findById(feedId).get(), user.userId)
            ResponseEntity.ok(feed)
        } else {
            ResponseEntity.badRequest().body(INCORRECT_FEED_ID)
        }
    }

    private fun mapFeedEntityToShortResponse(entity: FeedEntity, userId: Int): FeedShortResponse {
        return FeedShortResponse(
            id = entity.id,
            title = entity.title,
            created = entity.created,
            likesCount = feedLikesRepository.countAllByFeedId(entity.id),
            commentsCount = feedCommentsRepository.countAllByFeedIdAndUserId(entity.id, userId),
            isLiked = feedLikesRepository.existsByFeedIdAndUserId(entity.id, userId)
        )
    }

    private fun mapFeedEntityToFullResponse(entity: FeedEntity, userId: Int): FeedFullResponse {
        return FeedFullResponse(
            id = entity.id,
            title = entity.title,
            items = entity.items.map(::mapFeedItemEntityToResponse),
            created = entity.created,
            likesCount = feedLikesRepository.countAllByFeedId(entity.id),
            commentsCount = feedCommentsRepository.countAllByFeedIdAndUserId(entity.id, userId),
            isLiked = feedLikesRepository.existsByFeedIdAndUserId(entity.id, userId)
        )
    }

    private fun mapFeedItemEntityToResponse(entity: FeedItemEntity): FeedItemDto {
        return FeedItemDto(text = entity.text)
    }

    fun getCommentsByFeedIdPaginated(
        pageable: Pageable,
        feedId: Int,
        search: String,
        user: ApplicationUserDetails
    ): ResponseEntity<*> {
        val page = feedCommentsRepository.getAllByFeedIdAndTextContains(pageable, feedId, search)
        return ResponseEntity.ok(
            PaginationResponse(
                data = page.content.map { mapCommentEntityToResponse(it, user.userId) },
                total = page.totalElements,
                page = page.number,
                isLast = page.isLast
            )
        )
    }

    private fun mapCommentEntityToResponse(entity: FeedCommentEntity, userId: Int): CommentResponse {
        return CommentResponse(
            id = entity.id,
            text = entity.text,
            likesCount = commentLikesRepository.countAllByCommentId(entity.id),
            isLiked = commentLikesRepository.existsByCommentIdAndUserId(entity.id, userId)
        )
    }

    fun createComment(feedId: Int, request: CommentRequest, user: ApplicationUserDetails): ResponseEntity<*> {
        return if (feedsRepository.existsById(feedId)) {
            val entity = feedCommentsRepository.save(
                FeedCommentEntity(
                    feedId = feedId,
                    userId = user.userId,
                    text = request.text
                )
            )
            ResponseEntity.ok(mapCommentEntityToResponse(entity, user.userId))
        } else {
            ResponseEntity.badRequest().body(INCORRECT_FEED_ID)
        }
    }

    fun updateComment(commentId: Int, request: CommentRequest, user: ApplicationUserDetails): ResponseEntity<*> {
        val oldEntity = feedCommentsRepository.findByIdOrNull(commentId)
        return if (oldEntity != null) {
            ResponseEntity.ok(
                mapCommentEntityToResponse(
                    feedCommentsRepository.save(oldEntity.copy(text = request.text)),
                    user.userId
                )
            )
        } else {
            ResponseEntity.badRequest().body(INCORRECT_COMMENT_ID)
        }
    }

    fun deleteComment(commentId: Int) {
        feedCommentsRepository.deleteById(commentId)
    }

    fun toggleCommentLike(commentId: Int, user: ApplicationUserDetails): ResponseEntity<*> {
        return if (feedCommentsRepository.existsById(commentId)) {
            val like = commentLikesRepository.getByCommentIdAndUserId(commentId, user.userId)
            if (like == null) {
                commentLikesRepository.save(CommentLikeEntity(commentId = commentId, userId = user.userId))
            } else {
                commentLikesRepository.delete(like)
            }
            val feed = mapCommentEntityToResponse(feedCommentsRepository.findById(commentId).get(), user.userId)
            ResponseEntity.ok(feed)
        } else {
            ResponseEntity.badRequest().body(INCORRECT_FEED_ID)
        }
    }
}