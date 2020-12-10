package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.dao.PostDao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface PostsRepository : CrudRepository<PostDao, Long> {

    fun findAll(pageable: Pageable): Page<PostDao>

    fun findAllByIdAfter(id: Long, pageable: Pageable): Page<PostDao>

    fun findAllByUserId(userId: Int): List<PostDao>
}