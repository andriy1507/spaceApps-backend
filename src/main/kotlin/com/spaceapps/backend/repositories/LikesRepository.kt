package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.dao.LikeDao
import org.springframework.data.repository.CrudRepository

interface LikesRepository : CrudRepository<LikeDao, Long> {

    fun findAllByUserId(userId: Long): List<LikeDao>

    fun countAllByPostId(postId: Long): Long

}