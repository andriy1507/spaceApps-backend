package com.spaceapps.backend.repository

import com.spaceapps.backend.model.dao.feeds.CommentLikeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentLikesRepository : CrudRepository<CommentLikeEntity, Int> {

    fun getByCommentIdAndUserId(commentId: Int, userId: Int): CommentLikeEntity?

    fun countAllByCommentId(commentId: Int): Int

    fun existsByCommentIdAndUserId(commentId: Int, userId: Int): Boolean

}