package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.feeds.FeedCommentEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedCommentsRepository : CrudRepository<FeedCommentEntity, Int> {

    fun countAllByFeedIdAndUserId(feedId: Int, userId: Int): Int

    fun getAllByFeedIdAndTextContains(pageable: Pageable, feedId: Int, search: String): Page<FeedCommentEntity>
}