package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.feeds.FeedLikeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FeedLikesRepository : CrudRepository<FeedLikeEntity, Int> {

    fun countAllByFeedId(feedId: Int): Int

    fun getByFeedIdAndUserId(feedId: Int, userId: Int): FeedLikeEntity?

    fun existsByFeedIdAndUserId(feedId: Int, userId: Int): Boolean
}