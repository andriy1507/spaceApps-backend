package com.spaceapps.backend.services.impl

import com.spaceapps.backend.model.dto.PostDto
import com.spaceapps.backend.repositories.CommentsRepository
import com.spaceapps.backend.repositories.LikesRepository
import com.spaceapps.backend.repositories.PostsRepository
import com.spaceapps.backend.services.PostsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostsServiceImpl @Autowired constructor(
        private val likesRepository: LikesRepository,
        private val commentsRepository: CommentsRepository,
        private val postsRepository: PostsRepository
) : PostsService {

    override fun createPost(userId: Long, postDto: PostDto): PostDto {
        val dao = postDto.toDao().apply { this.userId = userId }
        return postsRepository.save(dao).toDto()
    }

    override fun deletePost(userId: Long, postId: Long) {
        postsRepository.deleteById(postId)
    }
}