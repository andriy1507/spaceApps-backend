package com.spaceapps.backend.services

import com.spaceapps.backend.model.dao.PostDao
import com.spaceapps.backend.model.dto.PostDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostsService {

    fun getPostsPaginatedAfterId(id: Long, pageable: Pageable): Page<PostDao>

    fun getPostsPaginated(pageable: Pageable): Page<PostDao>

    fun createPost(userId: Long, postDto: PostDto): PostDto

    fun deletePost(userId: Long, postId: Long): PostDto

    fun likePost(postId: Long, userId: Long): PostDto

    fun unlikePost(postId: Long, userId: Long): PostDto

}