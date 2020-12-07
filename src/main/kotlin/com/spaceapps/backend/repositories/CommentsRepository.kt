package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.dao.CommentDao
import org.springframework.data.repository.CrudRepository

interface CommentsRepository : CrudRepository<CommentDao, Long> {

    fun findAllByPostId(postId: Long): List<CommentDao>

    fun countAllByPostId(postId: Long): Long

    fun deleteAllByPostId(postId: Long)
}