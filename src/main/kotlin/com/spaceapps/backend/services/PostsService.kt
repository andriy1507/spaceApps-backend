package com.spaceapps.backend.services

import com.spaceapps.backend.model.dto.PostDto

interface PostsService {

    fun createPost(userId: Long, postDto: PostDto): PostDto

    fun deletePost(userId: Long, postId: Long)

}