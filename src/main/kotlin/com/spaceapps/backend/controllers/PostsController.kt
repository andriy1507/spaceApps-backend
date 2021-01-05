package com.spaceapps.backend.controllers

import com.spaceapps.backend.model.PaginationResponse
import com.spaceapps.backend.model.dao.ApplicationUser
import com.spaceapps.backend.model.dao.PostDao
import com.spaceapps.backend.model.dto.PostDtoRequest
import com.spaceapps.backend.model.dto.PostDtoResponse
import com.spaceapps.backend.model.dto.SearchPostDtoResponse
import com.spaceapps.backend.services.PostsService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("posts")
@Api(value = "Posts Controller", tags = ["Posts"], description = "Posts, likes and comments management")
class PostsController @Autowired constructor(
        private val postsService: PostsService
) {

    @GetMapping("/search")
    fun searchPosts(
            @RequestParam("query") query: String,
            @PageableDefault(size = 20) pageable: Pageable
    ): PaginationResponse<SearchPostDtoResponse> {
        return postsService.searchPosts(query, pageable)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Returns pagination for posts")
    fun getPosts(
            @PageableDefault(
                    size = 20,
                    page = 0,
                    sort = ["id"],
                    direction = Sort.Direction.DESC
            )
            pageable: Pageable
    ): PaginationResponse<PostDtoResponse> {
        val user = SecurityContextHolder.getContext().authentication.principal as ApplicationUser
        return postsService.getPostsPaginated(user.id, pageable)
    }

//    @GetMapping("/{postId}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation("Returns specific post with id")
//    fun getPost(@PathVariable("postId") postId: Long): PostDtoRequest {
//        return PostDtoRequest(0, "", "")
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Creates new post")
    fun createPost(@RequestBody postRequest: PostDtoRequest): ResponseEntity<*> {
        return (SecurityContextHolder.getContext().authentication.principal as? ApplicationUser)?.let {
            ResponseEntity.ok(postsService.createPost(it.id, postRequest))
        } ?: ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
    }

//    @DeleteMapping("/{postId}")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation("Deletes post")
//    fun deletePosts(@PathVariable("postId") postId: Long): PostDtoRequest? {
//        return (SecurityContextHolder.getContext().authentication.principal as? ApplicationUser)?.let {
//            postsService.deletePost(it.id, postId)
//        }
//    }
//
//    @PutMapping("/{postId}")
//    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
//    @ApiOperation("Editing post")
//    fun updatePost(@PathVariable postId: Int): PostDtoRequest {
//        return PostDtoRequest(0, "", "")
//    }

    @PutMapping("/like/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Adds like to post")
    fun likePost(@PathVariable postId: Long): PostDtoResponse? {
        return (SecurityContextHolder.getContext().authentication.principal as? ApplicationUser)?.let {
            postsService.likePost(postId, it.id)
        }
    }

    @DeleteMapping("/like/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Removes like from post")
    fun unlikePost(@PathVariable postId: Long): PostDtoResponse? {
        return (SecurityContextHolder.getContext().authentication.principal as? ApplicationUser)?.let {
            postsService.unlikePost(postId, it.id)
        }
    }

    @PostMapping("/comment/{postId}")
    fun commentPost(@PathVariable("postId") postId: Long, @RequestParam("text") text: String) {
        (SecurityContextHolder.getContext().authentication.principal as? ApplicationUser)?.let {
            postsService.createComment(it.id, postId, text)
        } ?: ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
    }

    @GetMapping("/comments/{postId}")
    fun getPostComments(@PathVariable("postId") postId: Long): ResponseEntity<*> {
        return postsService.getCommentsForPost(postId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post with id $postId not found")
    }
//
//    @DeleteMapping("/comment/{commentId}")
//    fun deleteComment(@PathVariable commentId: Long) {
//
//    }
}