package com.spaceapps.backend.model.dao

import com.spaceapps.backend.model.dto.PostDto
import org.joda.time.LocalDateTime
import javax.persistence.*

@Entity(name = "posts")
@Table
class PostDao(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        @Column(name = "title")
        var title: String? = null,
        @Column(name = "text")
        var text: String? = null,
        @Column(name = "userId")
        var userId: Long = 0,
        @Column(name = "created")
        var created: Long = 0
) {

    @OneToMany
    var comments: MutableList<CommentDao> = mutableListOf()

    @OneToMany
    var likes: MutableList<LikeDao> = mutableListOf()

    fun toDto() = PostDto(
            id,
            title.orEmpty(),
            text.orEmpty(),
            LocalDateTime(created)
    )
}