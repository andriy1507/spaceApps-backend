package com.spaceapps.backend.services

import com.spaceapps.backend.model.PaginationResponse
import com.spaceapps.backend.model.dao.PostDao
import com.spaceapps.backend.model.dto.CommentDto
import com.spaceapps.backend.model.dto.PostDtoRequest
import com.spaceapps.backend.model.dto.PostDtoResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PostsService {

    fun getPostsPaginatedAfterId(id: Long, pageable: Pageable): Page<PostDao>

    fun getPostsPaginated(userId: Long, pageable: Pageable): PaginationResponse<PostDtoResponse>

    fun createPost(userId: Long, postDtoRequest: PostDtoRequest): PostDtoResponse

    fun deletePost(userId: Long, postId: Long): PostDtoRequest

    fun likePost(postId: Long, userId: Long): PostDtoResponse

    fun unlikePost(postId: Long, userId: Long): PostDtoResponse

    fun createComment(userId: Long, postId: Long, text: String)

    fun getCommentsForPost(postId: Long): List<CommentDto>?
}