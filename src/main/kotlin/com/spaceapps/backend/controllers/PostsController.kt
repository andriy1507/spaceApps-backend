package com.spaceapps.backend.controllers

import com.spaceapps.backend.model.PaginationResponse
import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dto.PostDto
import com.spaceapps.backend.services.PostsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.joda.time.LocalDateTime
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional

@RestController
@RequestMapping("posts")
@Api(value = "Posts Controller", tags = ["Posts"], description = "Posts, likes and comments management")
class PostsController @Autowired constructor(
        private val postsService: PostsService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns pagination for posts")
    fun getPosts(
            @PageableDefault(
                    size = 20,
                    page = 1,
                    sort = ["created"],
                    direction = Sort.Direction.DESC
            )
            pageable: Pageable
    ): PaginationResponse<PostDto> {
        return PaginationResponse(0, 0, emptyList())
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns specific post with id")
    fun getPost(@PathVariable postId: Long): PostDto {
        return PostDto(0, "", "", LocalDateTime.now())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates new post")
    fun createPost(@RequestBody post: PostDto): ResponseEntity<*> {
        return (SecurityContextHolder.getContext().authentication.principal as? ApplicationUser)?.let {
            ResponseEntity.ok(postsService.createPost(it.id, post))
        } ?: ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Deletes post")
    fun deletePosts(@PathVariable postId: Int): PostDto {
        return PostDto(0, "", "", LocalDateTime.now())
    }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Editing post")
    fun updatePost(@PathVariable postId: Int): PostDto {
        return PostDto(0, "", "", LocalDateTime.now())
    }

    @PutMapping("/like/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Adds like to post")
    fun likePost(@PathVariable postId: Long): PostDto {
        return PostDto(0, "", "", LocalDateTime.now())
    }

    @DeleteMapping("/like/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Removes like from post")
    fun unlikePost(@PathVariable postId: Int): PostDto {
        return PostDto(0, "", "", LocalDateTime.now())
    }
}