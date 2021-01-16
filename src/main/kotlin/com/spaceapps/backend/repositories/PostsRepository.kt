package com.spaceapps.backend.repositories

import com.spaceapps.backend.model.dao.PostDao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PostsRepository : CrudRepository<PostDao, Long> {

    fun findAll(pageable: Pageable): Page<PostDao>

    @Query("SELECT p FROM posts p WHERE p.text LIKE %?1% OR p.title LIKE %?1%")
    fun searchPostsByTitleAndText(query: String, pageable: Pageable): Page<PostDao>

    fun findAllByIdAfter(id: Long, pageable: Pageable): Page<PostDao>

    fun findAllByUserId(userId: Int, pageable: Pageable): Page<PostDao>
}