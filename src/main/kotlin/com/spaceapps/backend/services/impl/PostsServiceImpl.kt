package com.spaceapps.backend.services.impl

import com.spaceapps.backend.model.dao.LikeDao
import com.spaceapps.backend.model.dao.PostDao
import com.spaceapps.backend.model.dto.PostDto
import com.spaceapps.backend.repositories.CommentsRepository
import com.spaceapps.backend.repositories.LikesRepository
import com.spaceapps.backend.repositories.PostsRepository
import com.spaceapps.backend.services.PostsService
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

    override fun getPostsPaginated(pageable: Pageable): Page<PostDao> {
        return postsRepository.findAll(pageable)
    }

    override fun createPost(userId: Long, postDto: PostDto): PostDto {
        val dao = postDto.toDao().apply { this.userId = userId }
        return postsRepository.save(dao).toDto()
    }

    @Transactional
    override fun deletePost(userId: Long, postId: Long): PostDto {
        val post = postsRepository.findById(postId).get()
        postsRepository.delete(post)
        commentsRepository.deleteAllByPostId(postId)
        likesRepository.deleteAllByPostId(postId)
        return post.toDto()
    }

    @Transactional
    override fun likePost(postId: Long, userId: Long): PostDto {
        val post = postsRepository.findById(postId).get()
        post.likes.add(likesRepository.save(LikeDao(userId = userId, postId = postId)))
        return post.toDto()
    }

    @Transactional
    override fun unlikePost(postId: Long, userId: Long): PostDto {
        val post = postsRepository.findById(postId).get()
        post.likes.find { it.userId == userId }?.let { like ->
            post.likes.remove(like)
            likesRepository.delete(like)
            postsRepository.save(post)
        }
        return post.toDto()
    }
}