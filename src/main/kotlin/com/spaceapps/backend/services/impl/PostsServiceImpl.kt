package com.spaceapps.backend.services.impl

import com.spaceapps.backend.model.PaginationResponse
import com.spaceapps.backend.model.dao.LikeDao
import com.spaceapps.backend.model.dao.PostDao
import com.spaceapps.backend.model.dto.PostDtoRequest
import com.spaceapps.backend.model.dto.PostDtoResponse
import com.spaceapps.backend.repositories.CommentsRepository
import com.spaceapps.backend.repositories.LikesRepository
import com.spaceapps.backend.repositories.PostsRepository
import com.spaceapps.backend.services.PostsService
import org.joda.time.LocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsServiceImpl @Autowired constructor(
        private val likesRepository: LikesRepository,
        private val commentsRepository: CommentsRepository,
        private val postsRepository: PostsRepository
) : PostsService {

    override fun getPostsPaginatedAfterId(id: Long, pageable: Pageable): Page<PostDao> {
        return postsRepository.findAllByIdAfter(id, pageable)
    }

    override fun getPostsPaginated(userId: Long, pageable: Pageable): PaginationResponse<PostDtoResponse> {
        val page = postsRepository.findAll(pageable)
        return PaginationResponse(
                page.number,
                page.totalElements,
                page.map { dao ->
                    dao.let {
                        PostDtoResponse(
                                it.id,
                                it.title.orEmpty(),
                                it.text.orEmpty(),
                                LocalDateTime(it.created),
                                it.likes.any { like -> like.userId == userId },
                                it.likes.size.toLong(),
                                it.comments.size.toLong()
                        )
                    }
                }.toList()
        )
    }

    override fun createPost(userId: Long, postDtoRequest: PostDtoRequest): PostDtoResponse {
        val dao = postDtoRequest.toDao().apply { this.userId = userId }
        return postsRepository.save(dao).let {
            PostDtoResponse(
                    it.id,
                    it.title.orEmpty(),
                    it.text.orEmpty(),
                    LocalDateTime(it.created),
                    it.likes.any { like -> like.userId == userId },
                    it.likes.size.toLong(),
                    it.comments.size.toLong()
            )
        }
    }

    @Transactional
    override fun deletePost(userId: Long, postId: Long): PostDtoRequest {
        val post = postsRepository.findById(postId).get()
        postsRepository.delete(post)
        commentsRepository.deleteAllByPostId(postId)
        likesRepository.deleteAllByPostId(postId)
        return post.toDto()
    }

    @Transactional
    override fun likePost(postId: Long, userId: Long): PostDtoResponse {
        val post = postsRepository.findById(postId).get()
        post.likes.add(likesRepository.save(LikeDao(userId = userId, postId = postId)))
        return PostDtoResponse(
                postId,
                post.title.orEmpty(),
                post.text.orEmpty(),
                LocalDateTime(post.created),
                true,
                post.likes.size.toLong(),
                post.comments.size.toLong()
        )
    }

    @Transactional
    override fun unlikePost(postId: Long, userId: Long): PostDtoResponse {
        val post = postsRepository.findById(postId).get()
        post.likes.find { it.userId == userId }?.let { like ->
            post.likes.remove(like)
            likesRepository.delete(like)
            postsRepository.save(post)
        }
        return PostDtoResponse(
                postId,
                post.title.orEmpty(),
                post.text.orEmpty(),
                LocalDateTime(post.created),
                false,
                post.likes.size.toLong(),
                post.comments.size.toLong()
        )
    }
}